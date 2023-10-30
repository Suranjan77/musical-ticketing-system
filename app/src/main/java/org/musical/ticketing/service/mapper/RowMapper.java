package org.musical.ticketing.service.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
  T mapRows(ResultSet rs) throws SQLException;
}
