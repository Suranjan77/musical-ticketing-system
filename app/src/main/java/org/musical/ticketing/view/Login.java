package org.musical.ticketing.view;

import java.awt.*;
import java.io.Serial;
import javax.swing.*;
import org.musical.ticketing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login extends JFrame {
  private final Logger log = LoggerFactory.getLogger(getClass());
  @Serial private static final long serialVersionUID = 1L;

  private final UserService userService = UserService.instance();
  public static final int H_FRAME = 360;
  public static final int W_FRAME = 540;
  private JButton buttonLogin;
  private JLabel labelErrorText;
  private JTextField textFieldEmail;
  private JPasswordField passwordFieldPassword;
  private final Insets insets;

  public Login() {
    super("Login");
    setLayout(null);
    setSize(W_FRAME, H_FRAME);
    setLocationRelativeTo(null);
    setLocation(getX() - 80, getY() - 80);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

    insets = this.getInsets();

    showUI();
  }

  private void showUI() {
    JPanel contentPane = new JPanel();
    contentPane.setLayout(null);
    contentPane.setBounds(
        insets.left,
        insets.top,
        W_FRAME - insets.left - insets.right,
        H_FRAME - insets.bottom - insets.top);

    JLabel labelUsername = new JLabel("Email Address");
    labelUsername.setBounds(120, 140, 90, 20);
    contentPane.add(labelUsername);

    JLabel labelPassword = new JLabel("Password");
    labelPassword.setBounds(
        labelUsername.getX(),
        labelUsername.getY() + 40,
        labelUsername.getWidth(),
        labelUsername.getHeight());
    contentPane.add(labelPassword);

    textFieldEmail = new JTextField();
    textFieldEmail.setBounds(
        labelUsername.getX() + labelUsername.getWidth() + 30,
        labelUsername.getY(),
        120,
        labelUsername.getHeight());
    textFieldEmail.addActionListener(e -> passwordFieldPassword.requestFocus());
    contentPane.add(textFieldEmail);

    passwordFieldPassword = new JPasswordField();
    passwordFieldPassword.setBounds(
        textFieldEmail.getX(), labelPassword.getY(), 120, labelPassword.getHeight());
    passwordFieldPassword.addActionListener(e -> buttonLogin.doClick());
    contentPane.add(passwordFieldPassword);

    buttonLogin = new JButton("Login");
    buttonLogin.setBounds(textFieldEmail.getX() + 20, labelUsername.getY() + 80, 80, 22);
    buttonLogin.setFocusPainted(false);
    buttonLogin.addActionListener(e -> onLoginButtonPressed(contentPane));
    contentPane.add(buttonLogin);

    labelErrorText = new JLabel();
    labelErrorText.setForeground(Color.RED);
    labelErrorText.setBounds(buttonLogin.getX() - 45, buttonLogin.getY() + 30, 170, 30);
    contentPane.add(labelErrorText);

    setContentPane(contentPane);
  }

  private void onLoginButtonPressed(JPanel contentPane) {
    if (textFieldEmail.getText().isEmpty()) {
      labelErrorText.setText("Email cannot be empty");
    } else if(String.valueOf(passwordFieldPassword.getPassword()).isEmpty()) {
      labelErrorText.setText("Password cannot be empty");
    }else {
      labelErrorText.setText("");
      try {
        userService.authenticate(
            textFieldEmail.getText(), String.valueOf(passwordFieldPassword.getPassword()));
        JOptionPane.showMessageDialog(
            contentPane, "Login successful Welcome", "Login", JOptionPane.INFORMATION_MESSAGE);
        EventQueue.invokeLater(
            () -> {
              Login.this.dispose();
              new MainFrame();
            });
      } catch (Exception ex) {
        log.error("Exception authenticating", ex);
        labelErrorText.setText("Failed to authenticate");
      }
    }
  }
}
