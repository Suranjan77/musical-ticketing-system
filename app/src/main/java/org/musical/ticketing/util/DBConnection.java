package org.musical.ticketing.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

  private static volatile DBConnection instance;
  private final HikariDataSource dataSource;

  private DBConnection() {
    HikariConfig config = new HikariConfig();
    String jdbcUrl = "jdbc:derby:" + PathUtils.ROOT_PATH + "/ticketing_db;create=true";
    config.setJdbcUrl(jdbcUrl);
    config.setUsername("");
    config.setPassword("");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    config.setDriverClassName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
    this.dataSource = new HikariDataSource(config);
  }

  public static DBConnection instance() {
    if (instance == null) {
      synchronized (DBConnection.class) {
        if (instance == null) {
          instance = new DBConnection();
        }
      }
    }
    return instance;
  }

  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

  public void intiDb() throws IOException, SQLException {
    var inputStream = this.getClass().getClassLoader().getResourceAsStream("initDb.sql");
    String input = readFromInputStream(inputStream);
    input = input.replace("\\s+", " ");
    String[] queries = input.split(";");
    try (var connection = getConnection();
        var stmt = connection.createStatement()) {
      for (String query : queries) {
        if (!query.trim().isEmpty()) {
          try {
            stmt.execute(query.trim());
          } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32")) {
              throw e;
            }
          }
        }
      }
    }
  }

  private String readFromInputStream(InputStream inputStream) throws IOException {
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    }
    return resultStringBuilder.toString();
  }
}
