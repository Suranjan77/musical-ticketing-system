package org.musical.ticketing.service.mapper.impl;

import org.musical.ticketing.domain.User;
import org.musical.ticketing.service.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
  @Override
  public User mapRows(ResultSet rs) throws SQLException {
    var user = new User();
    user.setId(rs.getLong(0));
    user.setEmail(rs.getString(1));
    user.setPassword(rs.getString(2));
    return user;
  }
}
