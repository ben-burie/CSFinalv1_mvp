package Project;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Project.Backend.Profile;

class CreateAccountGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public CreateAccountGUI() {
        // Set up the JFrame for creating an account
        setTitle("Create Account");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create and add components for creating an account
        JPanel createPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        JLabel createLabel = new JLabel("Create Account");
        createLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createPanel.add(createLabel);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        inputPanel.add(confirmPasswordField);
        createPanel.add(inputPanel);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the input values
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                char[] confirmPasswordChars = confirmPasswordField.getPassword();
                String password = new String(passwordChars);
                String confirmPassword = new String(confirmPasswordChars);

                // Validate the input
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                } else {
                    // Successful account creation, go to login page
                    JOptionPane.showMessageDialog(null, "Account created successfully!");
                    dispose(); // Close the current window
                    new LoginGUI(); // Open the login interface
                }
                
               Profile p1 = new Profile(username, password);
               writeAccount(p1);
                
            }
        });
        createPanel.add(createButton);

        add(createPanel, BorderLayout.CENTER);

        // Display the JFrame
        setVisible(true);
        
       
    }
    
    public static void writeAccount(Profile p1) {
    	try {
			FileWriter writer = new FileWriter("accountDatabase.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(p1.toString() + "\n");
			bufferedWriter.close();
		} catch (IOException e) {
			System.out.println("Error!");
			e.printStackTrace();
		}
    }
    
}