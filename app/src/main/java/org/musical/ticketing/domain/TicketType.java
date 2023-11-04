package org.musical.ticketing.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public record TicketType(Long id, String name, Double price) implements Domain<TicketType> {
  public static TicketType empty() {
    return new TicketType(null, null, null);
  }

  public String getColumns() {
    return String.join(",", List.of("id", "name", "price"));
  }

  public TicketType map(ResultSet rs) throws SQLException {
    return new TicketType(rs.getLong(1), rs.getString(2), rs.getDouble(3));
  }

  @Override
  public String tableName() {
    return "ticket_types";
  }

  @Override
  public void setPreparedStatement(PreparedStatement ps) throws SQLException {
    ps.setString(1, name);
    ps.setDouble(2, price);
  }

  @Override
  public String getInsertQueryPlaceHolders() {
    return "?,?";
  }
}
