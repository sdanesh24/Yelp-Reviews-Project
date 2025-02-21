import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPageView {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPageView() {
        frame = new JFrame("Login Page");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        frame.add(new JLabel("Username:"));
        usernameField = new JTextField();
        frame.add(usernameField);

        frame.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        frame.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // validate user
                // pull from the loginInfo HashMap
                // creating an instance of the LoginAccounts class to call the getter method on

                LoginAccounts loginAccounts = new LoginAccounts();
                HashMap <String, String> loginInfo = loginAccounts.getLoginInfo();

                if (loginInfo.containsKey(username) && loginInfo.containsValue(password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    // open up main screen
                    // new MainPageView.java
                    // same way for new LoginPageView() in FirstTimeUserPrompt:46
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials, try again.");
                }
            }
        });

        frame.add(new JLabel()); // Spacer
        frame.add(loginButton);

        frame.setVisible(true);
    }
}

