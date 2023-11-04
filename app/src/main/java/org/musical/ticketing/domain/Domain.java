package org.musical.ticketing.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Domain<T> {
  String getColumns();

  T map(ResultSet rs) throws SQLException;

  String tableName();

  void setPreparedStatement(PreparedStatement ps) throws SQLException;

  String getInsertQueryPlaceHolders();
}
