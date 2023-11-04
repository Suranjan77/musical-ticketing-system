package org.musical.ticketing.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public record Ticket(
    Long id,
    Long customerId,
    Long musicalId,
    Long showTimeId,
    Integer seatNumber,
    Long ticketTypeId)
    implements Domain<Ticket> {

  public static Ticket empty() {
    return new Ticket(null, null, null, null, null, null);
  }

  public String getColumns() {
    return String.join(
        ",",
        List.of(
            "id", "customer_id", "musical_id", "show_time_id", "seat_number", "ticket_type_id"));
  }

  public Ticket map(ResultSet rs) throws SQLException {
    return new Ticket(
        rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getLong(4), rs.getInt(5), rs.getLong(6));
  }

  @Override
  public String tableName() {
    return "tickets";
  }

  @Override
  public void setPreparedStatement(PreparedStatement ps) throws SQLException {
    ps.setLong(1, customerId);
    ps.setLong(2, musicalId);
    ps.setLong(3, showTimeId);
    ps.setInt(4, seatNumber);
    ps.setLong(5, ticketTypeId);
  }

  @Override
  public String getInsertQueryPlaceHolders() {
    return "?,?,?,?,?";
  }
}
