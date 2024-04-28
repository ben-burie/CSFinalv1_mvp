package Project;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class BankPortalGUI extends JFrame {
    private JLabel titleLabel;
    private JButton loginButton;
    private JButton createAccountButton;

    public BankPortalGUI() {

        // Set up the JFrame
        setTitle("Bank Portal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Create and add the title label
        titleLabel = new JLabel("<html>Bank Portal<br><br></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        add(titleLabel, BorderLayout.NORTH);

        // Create and add the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        loginButton = new JButton("Login");
        createAccountButton = new JButton("Create Account");
        buttonPanel.add(loginButton);
        buttonPanel.add(createAccountButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners to the buttons
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current BankPortalGUI instance
                dispose();
                // Open the login interface
                new LoginGUI();
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current BankPortalGUI instance
                dispose();
                // Open the create account interface
                new CreateAccountGUI();
            }
        });
        
        // Display the JFrame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankPortalGUI();
            }
        });
    }
}