package org.musical.ticketing.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public record TicketType(Long id, String name, Long musicalId, double price) implements Domain<TicketType> {
  public static TicketType empty() {
    return new TicketType(null, null, null, 0.0);
  }

  @Override
  public String getColumns() {
    return String.join(",", List.of("id", "name", "musical_id", "price"));
  }

  @Override
  public TicketType map(ResultSet rs) throws SQLException {
    return new TicketType(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getDouble(4));
  }

  @Override
  public String tableName() {
    return "ticket_types";
  }

  @Override
  public void setPreparedStatement(PreparedStatement ps) throws SQLException {
    ps.setString(1, name);
    ps.setLong(2, musicalId);
    ps.setDouble(3, price);
  }

  @Override
  public String getInsertQueryPlaceHolders() {
    return "?,?,?";
  }
}
