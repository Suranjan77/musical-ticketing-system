package org.musical.ticketing;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

import org.musical.ticketing.util.DBConnection;
import org.musical.ticketing.util.MainFrameContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    try {
      var connection = DBConnection.instance();
      connection.intiDb();
      connection.seedData();
    } catch (IOException | SQLException e) {
      log.error("Failed to initialized database", e);
      throw new RuntimeException(e);
    }
    EventQueue.invokeLater(MainFrameContext::instance);
  }
}
