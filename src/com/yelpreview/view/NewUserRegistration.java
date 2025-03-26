package com.yelpreview.view;

import com.yelpreview.logic.LoginAccounts;
import com.yelpreview.model.*;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewUserRegistration {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private String username;
    private String password;
    private LoginAccounts loginAccounts;

    // added an instance of loginAccounts in the constructor
    public NewUserRegistration(LoginAccounts loginAccounts)
    {
        this.loginAccounts = loginAccounts;
        frame = new JFrame("Registration Page");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        frame.add(new JLabel("Username:"));
        usernameField = new JTextField();
        frame.add(usernameField);

        frame.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        frame.add(passwordField);

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled.");
                } else {
                    loginAccounts.storeUser(username, password);
                    loginAccounts.printLoginInfo();

                    User user = new User(username, password);
                    EntityManager entityManager = JpaUtility.getEntityManager();
                    entityManager.getTransaction().begin();
                    entityManager.persist(user);
                    entityManager.getTransaction().commit();
                    entityManager.close();

                    JOptionPane.showMessageDialog(frame, "Registration Successful!");
;
                    frame.dispose();
                    new LoginPageView();
                }
            }
        });

        frame.add(new JLabel()); // Spacer
        frame.add(registerButton);

        frame.setVisible(true);
    }
    // creating getter methods to pull username and password into com.yelpreview.logic.LoginAccounts
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
}


