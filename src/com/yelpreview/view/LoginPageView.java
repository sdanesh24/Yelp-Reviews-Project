package com.yelpreview.view;

import com.yelpreview.logic.LoginAccounts;
import com.yelpreview.model.JpaUtility;
import com.yelpreview.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginPageView {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPageView()
    {
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
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // validate user
                // old method before DB implemented = pull from the loginInfo HashMap
                    // creating an instance of the com.yelpreview.logic.LoginAccounts class to call the getter method on
                    // LoginAccounts loginAccounts = new LoginAccounts();
                    // HashMap <String, String> loginInfo = loginAccounts.getLoginInfo();

                // it's okay to be creating new instances within this actonPerformed method?

                EntityManager entityManager = JpaUtility.getEntityManager();
                TypedQuery<User> query = entityManager.createQuery(
                        "SELECT person FROM User person WHERE person.username = :username AND person.password = :password", User.class);
                query.setParameter("username", username);
                query.setParameter("password", password);
                List<User> matchingUsers = query.getResultList();

                if (!matchingUsers.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    frame.dispose();
                    new MainPageView();
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials, try again.");
                }
            }
        });

        frame.add(new JLabel()); // Spacer
        frame.add(loginButton);

        frame.setVisible(true);
    }
}

