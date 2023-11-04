package org.musical.ticketing.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.musical.ticketing.domain.Musical;
import org.musical.ticketing.util.DBConnection;

public class MusicalsRepository implements DomainRepository<Musical> {
  public List<Musical> search(String q) {
    var data = new ArrayList<Musical>();
    var emptyInstance = Musical.empty();
    String query =
        "SELECT "
            + emptyInstance.getColumns()
            + " FROM "
            + emptyInstance.tableName()
            + " WHERE title LIKE '%"
            + q
            + "%'";

    try (var con = DBConnection.instance().getConnection();
        var stmt = con.createStatement()) {
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        data.add(emptyInstance.map(rs));
      }
    } catch (SQLException e) {
      log.error("Failed to search musicals", e);
    }
    return data;
  }
}
