package Project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI() {
        // Set up the JFrame for the login interface
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create and add login components
        JPanel loginPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        JLabel loginLabel = new JLabel("Login Interface");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(loginLabel);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        loginPanel.add(inputPanel);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your login logic here
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                // Just an example, you should implement your own login logic here
                try {
					if (login(username, password) == true) {
					    JOptionPane.showMessageDialog(null, "Login successful!");
					    dispose();
					    new ProfilePageGUI(username);
					} else {
					    JOptionPane.showMessageDialog(null, "Invalid username or password!");
					}
				} catch (HeadlessException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.CENTER);

        // Display the JFrame
        setVisible(true);
    }
    
    public static boolean login(String username, String password) throws FileNotFoundException {
    	File file = new File("accountDatabase.txt");
		Scanner reader = new Scanner(file);
		
		Scanner input = new Scanner(System.in);
		
		boolean isFound = false;
		
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			String[] items = line.split(", ");

			if (items[0].equals(username) && items[1].equals(password)) {
				isFound = true;
			}
		}
		
		return isFound;
    }
}