package org.musical.ticketing.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.musical.ticketing.domain.Musical;
import org.musical.ticketing.domain.ShowTime;
import org.musical.ticketing.repositories.MusicalsRepository;
import org.musical.ticketing.repositories.ShowTimesRepository;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    try (var connection = getConnection(); var stmt = connection.createStatement()) {
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

  public void seedData() throws IOException, SQLException {
    MusicalsRepository musicalsRepository = new MusicalsRepository();
    var musicalsCount = seedMusicalsData(musicalsRepository);
    if (musicalsCount > 0) {
      seedAll();
      seedShowTimes(musicalsCount, musicalsRepository);
    }
  }

  private void seedShowTimes(int musicalCount, MusicalsRepository musicalsRepository) {
    ShowTimesRepository showTimesRepository = new ShowTimesRepository();

    int calculateForMonths = 6;

    LocalDate[][] localDatesPerMonth = new LocalDate[6][30];
    var ym = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
    for (int i = 0; i < calculateForMonths; i++) {
      localDatesPerMonth[i] = DateTimeUtils.getDaysOfMonth(ym);
      ym = ym.plusMonths(1);
    }

    var random = new Random();

    for (int musicalId = 1; musicalId <= musicalCount; musicalId++) {
      List<ShowTime> showTimes = new ArrayList<>();

      var musicalWrapper = musicalsRepository.findById((long) musicalId, Musical.empty());
      if (musicalWrapper.isPresent()) {
        Musical musical = musicalWrapper.get();

        for (int j = 1; j < calculateForMonths; j++) {
          var dates = localDatesPerMonth[j];
          for (var date : dates) {
            LocalTime startTime = LocalTime.of(9, 0, 0); // 9:00 AM
            var showTimeCount = random.nextInt(4) + 1;
            var pad = random.nextInt(3);
            for (int i = 0; i < showTimeCount; i++) {
              if (LocalTime.MAX.minusHours(1).isAfter(startTime.plusHours(pad).plusSeconds(musical.durationInSeconds()))) {
                startTime = startTime.plusHours(pad);
                var showTime
                    = new ShowTime(
                    null,
                    (long) musicalId,
                    date,
                    startTime,
                    startTime.plusSeconds(musical.durationInSeconds()),
                    30);
                showTimes.add(showTime);
                startTime = startTime.plusSeconds(musical.durationInSeconds());
              }
            }
          }
        }
      }

      showTimes.forEach(showTimesRepository::save);
    }
  }

  private void seedAll() throws IOException, SQLException {
    var inputStream = this.getClass().getClassLoader().getResourceAsStream("seed_all.sql");
    String input = readFromInputStream(inputStream);
    input = input.replace("\\s+", " ");
    String[] queries = input.split(";");
    try (var connection = getConnection(); var stmt = connection.createStatement()) {
      for (String query : queries) {
        if (!query.trim().isEmpty()) {
          try {
            stmt.executeUpdate(query.trim());
          } catch (SQLIntegrityConstraintViolationException ignore) {
          }
        }
      }
    }
  }

  private int seedMusicalsData(MusicalsRepository musicalsRepository)
      throws IOException, SQLException {
    var inputStream = this.getClass().getClassLoader().getResourceAsStream("musicals_data.csv");

    var existingCount = 0;
    var countQuery = "SELECT COUNT(*) FROM musicals";
    try (var connection = getConnection(); var stmt = connection.createStatement()) {
      var countRs = stmt.executeQuery(countQuery);
      if (countRs.next()) {
        existingCount = countRs.getInt(1);
      }
    }

    int count = 0;
    if (existingCount == 0 && inputStream != null) {
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        String line;
        boolean headerSkipped = false;

        while ((line = reader.readLine()) != null) {
          if (!headerSkipped) {
            headerSkipped = true;
            continue;
          }

          line = line.trim().replace("\\s+", " ");

          var data = parseCSVLine(line);

          var query = "insert into musicals (id, title, description, theater_name, thumbnail_image_url, duration_in_seconds) "
              + "values (?, ?, ?, ?, ?, ?)";

          try (var connection = getConnection(); var stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, Long.parseLong(data[5]));
            stmt.setString(2, data[1]);
            stmt.setString(3, data[3]);
            stmt.setString(4, data[2]);
            stmt.setString(5, data[0]);
            stmt.setLong(6, Long.parseLong(data[4]));
            stmt.executeUpdate();
            count++;
          }

        }
      }

    }

    return count;
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

  private static String[] parseCSVLine(String line) {
    String[] columns = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    for (int i = 0; i < columns.length; i++) {
      columns[i] = columns[i].replaceAll("^\"|\"$", ""); // Remove surrounding double quotes
    }
    return columns;
  }
}
