package org.musical.ticketing.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

public record Musical(
    Long id,
    String title,
    String description,
    String theaterName,
    Long durationInSeconds,
    String thumbnailImageUrl)
    implements Domain<Musical> {

  public static Musical empty() {
    return new Musical(null, null, null, null, null, null);
  }

  public String durationToString() {
    Duration duration = Duration.ofSeconds(durationInSeconds);
    long hours = duration.toHours();
    long minutes = duration.toMinutesPart();
    long remainingSeconds = duration.minusHours(hours).minusMinutes(minutes).toSeconds();

    return hours + " hrs, " + minutes + " min, and " + remainingSeconds + " sec";
  }

  @Override
  public String getColumns() {
    return String.join(
        ",",
        List.of(
            "id",
            "title",
            "description",
            "theater_name",
            "duration_in_seconds",
            "thumbnail_image_url"));
  }

  @Override
  public Musical map(ResultSet rs) throws SQLException {
    return new Musical(
        rs.getLong(1),
        rs.getString(2),
        rs.getString(3),
        rs.getString(4),
        rs.getLong(5),
        rs.getString(6));
  }

  @Override
  public String tableName() {
    return "musicals";
  }

  @Override
  public void setPreparedStatement(PreparedStatement ps) throws SQLException {
    ps.setString(1, title);
    ps.setString(2, description);
    ps.setString(3, theaterName);
    ps.setLong(4, durationInSeconds);
    ps.setString(5, thumbnailImageUrl);
  }

  @Override
  public String getInsertQueryPlaceHolders() {
    return "?,?,?,?,?";
  }
}
