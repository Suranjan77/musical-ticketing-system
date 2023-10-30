package org.musical.ticketing.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.prefs.Preferences;
import org.musical.ticketing.domain.Page;
import org.musical.ticketing.domain.User;
import org.musical.ticketing.service.mapper.RowMapper;
import org.musical.ticketing.service.mapper.impl.UserRowMapper;
import org.musical.ticketing.util.DBConnection;
import org.musical.ticketing.util.PasswordUtil;

public class UserService {

  private static final Preferences PREFS = Preferences.userRoot().node("org/musical/ticketing");

  private final RowMapper<User> mapper;

  private static class Helper {
    private static final UserService instance = new UserService();
  }

  private UserService() {
    this.mapper = new UserRowMapper();
  }

  public static UserService instance() {
    return UserService.Helper.instance;
  }

  public User create(User data) throws SQLException {
    String query = "INSERT INTO users (email, password) VALUES (?, ?)";

    try (var con = DBConnection.instance().getConnection();
        var stmt = con.prepareStatement(query); ) {
      stmt.setString(1, data.getEmail());
      stmt.setString(2, PasswordUtil.hash(data.getPassword()));

      stmt.executeQuery();
      var keys = stmt.getGeneratedKeys();
      long id = keys.getLong(0);

      return getById(id);
    }
  }

  public User authenticate(String email, String password) throws Exception {
    var errorMsg = "Authentication exception: email or password invalid";
    var user = findByEmail(email).orElseThrow(() -> new Exception(errorMsg));
    var valid = PasswordUtil.check(password, user.getPassword());
    if (!valid) {
      throw new Exception(errorMsg);
    }
    PREFS.put("email", email);
    return user;
  }

  public Optional<User> findByEmail(String email) {
    String query = "SELECT * FROM users WHERE email=?";
    try (var con = DBConnection.instance().getConnection();
        var stmt = con.prepareStatement(query); ) {
      stmt.setString(1, email);
      var user = mapper.mapRows(stmt.executeQuery(query));
      return Optional.of(user);
    } catch (SQLException e) {
      return Optional.empty();
    }
  }

  public Optional<User> getUserFromSession() {
    var email = PREFS.get("email", null);
    if (email != null) {
      return findByEmail(email);
    }
    return Optional.empty();
  }

  public User changePassword(Long id, String password) throws SQLException {
    String query = "UPDATE users SET password=? WHERE id=?";
    try (var con = DBConnection.instance().getConnection();
        var stmt = con.prepareStatement(query); ) {
      stmt.setString(1, password);
      stmt.setLong(2, id);
      stmt.executeUpdate();
      return getById(id);
    }
  }

  public void delete(Long id) throws SQLException {
    String query = "DELETE FROM users WHERE id=?";
    try (var con = DBConnection.instance().getConnection();
        var stmt = con.prepareStatement(query); ) {
      stmt.setLong(1, id);
      stmt.execute();
    }
  }

  public User getById(Long id) throws SQLException {
    String query = "SELECT * FROM users WHERE id=?";
    try (var con = DBConnection.instance().getConnection();
        var stmt = con.prepareStatement(query); ) {
      stmt.setLong(1, id);
      return mapper.mapRows(stmt.executeQuery());
    }
  }

  public Optional<User> findById(Long id) {
    String query = "SELECT * FROM users WHERE id=?";
    try (var con = DBConnection.instance().getConnection();
        var stmt = con.prepareStatement(query); ) {
      stmt.setLong(1, id);
      return Optional.of(mapper.mapRows(stmt.executeQuery()));
    } catch (SQLException e) {
      return Optional.empty();
    }
  }

  public Page<User> getAll(int page, int size) throws SQLException {

    int totalSize = 0;
    try (var con = DBConnection.instance().getConnection();
        var countStmt = con.prepareStatement("SELECT COUNT(*) FROM users")) {
      // Set any placeholders for your conditions here, if necessary
      try (var rs = countStmt.executeQuery()) {
        if (rs.next()) {
          totalSize = rs.getInt(1);
        }
      }
    }

    String query = "SELECT * FROM users ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    try (var con = DBConnection.instance().getConnection();
        var stmt = con.prepareStatement(query); ) {
      stmt.setInt(1, page * size);
      stmt.setInt(2, size);

      List<User> users = new ArrayList<>();
      try (var rs = stmt.executeQuery(query)) {
        while (rs.next()) {
          users.add(mapper.mapRows(rs));
        }
      }
      return new Page<>(users, totalSize, page, users.size());
    }
  }
}
