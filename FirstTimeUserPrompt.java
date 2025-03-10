//TODO: find another UI package that looks better/less boring??

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstTimeUserPrompt
{
    private JFrame frame;
    private JCheckBox yesCheckBox;
    private JCheckBox noCheckBox;
    private JButton enterButton;

    public FirstTimeUserPrompt()
    {
        frame = new JFrame("User Prompt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 1));

        JLabel questionLabel = new JLabel("Are you a first-time user?", SwingConstants.CENTER);
        frame.add(questionLabel);

        JPanel checkBoxPanel = new JPanel();
        yesCheckBox = new JCheckBox("Yes");
        noCheckBox = new JCheckBox("No");

        yesCheckBox.addActionListener(e -> noCheckBox.setSelected(!yesCheckBox.isSelected()));
        noCheckBox.addActionListener(e -> yesCheckBox.setSelected(!noCheckBox.isSelected()));

        checkBoxPanel.add(yesCheckBox);
        checkBoxPanel.add(noCheckBox);
        frame.add(checkBoxPanel);

        enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!yesCheckBox.isSelected() && !noCheckBox.isSelected())
                {
                    JOptionPane.showMessageDialog(frame, "Are you a first time user?");
                }
                else if (yesCheckBox.isSelected())
                {
                    frame.dispose();

                    LoginAccounts loginAccounts = new LoginAccounts();

                    new NewUserRegistration(loginAccounts);
                }
                else
                {
                    frame.dispose();
                    new LoginPageView();
                }
            }
        });

        frame.add(enterButton);
        frame.setVisible(true);
    }
}
