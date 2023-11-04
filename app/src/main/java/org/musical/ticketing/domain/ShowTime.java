package org.musical.ticketing.domain;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ShowTime(
    Long id,
    Long musicalId,
    LocalDate showDate,
    LocalTime startTime,
    LocalTime endTime,
    Integer availableSeatsCount)
    implements Domain<ShowTime> {
  public static ShowTime empty() {
    return new ShowTime(null, null, null, null, null, null);
  }

  public String getColumns() {
    return String.join(
        ",",
        List.of(
            "id", "musical_id", "show_date", "start_time", "end_time", "available_seats_count"));
  }

  public ShowTime map(ResultSet rs) throws SQLException {
    return new ShowTime(
        rs.getLong(1),
        rs.getLong(2),
        rs.getDate(3).toLocalDate(),
        rs.getTime(4).toLocalTime(),
        rs.getTime(5).toLocalTime(),
        rs.getInt(6));
  }

  @Override
  public String tableName() {
    return "show_times";
  }

  @Override
  public void setPreparedStatement(PreparedStatement ps) throws SQLException {
    ps.setLong(1, musicalId);
    ps.setDate(2, Date.valueOf(showDate));
    ps.setTime(3, Time.valueOf(startTime));
    ps.setTime(4, Time.valueOf(endTime));
    ps.setInt(5, availableSeatsCount);
  }

  @Override
  public String getInsertQueryPlaceHolders() {
    return "?,?,?,?,?";
  }
}
