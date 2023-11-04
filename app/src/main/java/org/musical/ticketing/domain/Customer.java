package org.musical.ticketing.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public record Customer(Long id, String phoneNumber) implements Domain<Customer> {
  public static Customer empty() {
    return new Customer(null, null);
  }

  public String getColumns() {
    return String.join(",", List.of("id", "phone_number"));
  }

  public Customer map(ResultSet rs) throws SQLException {
    return new Customer(rs.getLong(1), rs.getString(2));
  }

  @Override
  public String tableName() {
    return "customers";
  }

  @Override
  public void setPreparedStatement(PreparedStatement ps) throws SQLException {
    ps.setString(1, phoneNumber);
  }

  @Override
  public String getInsertQueryPlaceHolders() {
    return "?";
  }
}
