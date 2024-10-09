import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame
{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginAccount loginAccount; // To hold the passed LoginAccount

    // Constructor
    public LoginFrame(LoginAccount loginAccount)
    {
        this.loginAccount = loginAccount;

        setTitle("Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel to hold all components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add image above login fields
        ImageIcon loginIcon = new ImageIcon("login.png"); // Ensure the image is in the project folder
        JLabel imageLabel = new JLabel(loginIcon);
        panel.add(imageLabel, BorderLayout.NORTH);

        // Create the panel for the login form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));

        // Add Username and Password fields
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();
        formPanel.add(userLabel);
        formPanel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        formPanel.add(passLabel);
        formPanel.add(passwordField);

        panel.add(formPanel, BorderLayout.CENTER);

        // Create Login button
        JButton loginButton = new JButton("Login");
        panel.add(loginButton, BorderLayout.SOUTH);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Use LoginAccount to validate credentials
                if (loginAccount.validate(username, password))
                {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // Close login window and execute main application logic
                    dispose();
                    System.out.println("Hello world!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
    }
}

