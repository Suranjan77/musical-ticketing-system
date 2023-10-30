package org.musical.ticketing.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

  public static String hash(String plainPassword) {
    return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
  }

  public static boolean check(String plainPassword, String hashedPassword) {
    return BCrypt.checkpw(plainPassword, hashedPassword);
  }
}
