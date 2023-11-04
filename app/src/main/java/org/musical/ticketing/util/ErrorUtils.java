package org.musical.ticketing.util;

import javax.swing.JOptionPane;

/**
 *
 * @author suranjanpoudel
 */
public class ErrorUtils {
    public static void showErrorPane(String message) {
        JOptionPane.showMessageDialog(
          null,
          message,
          "Error",
          JOptionPane.ERROR_MESSAGE);
    }
}
