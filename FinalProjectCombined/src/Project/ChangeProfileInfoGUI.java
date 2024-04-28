package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class ChangeProfileInfoGUI extends JFrame {
    private JTextField newUsernameField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;

    public ChangeProfileInfoGUI(String username) {
        // Set up the JFrame for changing profile info
        setTitle("Edit Profile");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create and add components for changing profile info
        JPanel changeProfilePanel = new JPanel(new GridLayout(4, 1, 5, 5));

        JLabel changeProfileLabel = new JLabel("Edit Profile");
        changeProfileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        changeProfilePanel.add(changeProfileLabel);

        JPanel inputPanel1 = new JPanel(new GridLayout(1, 2, 5, 5));
        inputPanel1.add(new JLabel("New Username:"));
        newUsernameField = new JTextField();
        inputPanel1.add(newUsernameField);
        changeProfilePanel.add(inputPanel1);
       

        JPanel inputPanel2 = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel2.add(new JLabel("New Password:"));
        newPasswordField = new JPasswordField();
        inputPanel2.add(newPasswordField);
        inputPanel2.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        inputPanel2.add(confirmPasswordField);
        changeProfilePanel.add(inputPanel2);
        
        
        

        JButton saveChangesButton = new JButton("Save Changes");
        saveChangesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String newUsername = newUsernameField.getText();
            	char[] newPasswordChars = newPasswordField.getPassword();
            	String newPassword = new String(newPasswordChars);
            	char[] confirmPasswordChars = confirmPasswordField.getPassword();
            	String confirmPassword = new String(confirmPasswordChars);

            	
            	if (newPassword.equals(confirmPassword)) {
                	try {
    					changeInfo(username, newUsername, newPassword);
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
               		JOptionPane.showMessageDialog(null, "Changes saved successfully!");
               	}
               	else {
               		JOptionPane.showMessageDialog(null, "Passwords do not match!");
               	}
              
                dispose();
                try {
					new ProfilePageGUI(username);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            }
        });
        changeProfilePanel.add(saveChangesButton);

        add(changeProfilePanel, BorderLayout.CENTER);

        // Display the JFrame
        setVisible(true);
    }
    
    
    public static void changeInfo(String oldUsername, String newUsername, String newPassword) throws IOException {
    	
    	File file = new File("accountDatabase.txt");
		Scanner reader = new Scanner(file);
		StringBuilder content = new StringBuilder();
		
		
		boolean isFound = false;
		
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			String[] items = line.split(",");
			
			if (items[0].equals(oldUsername)) {
				isFound = true;
			}
			else {
	            content.append(line).append("\n");
	        }
		}
		
		if (isFound == true) {
			
			content.append(newUsername + ", " + newPassword).append("\n");
			
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content.toString());
            writer.close();
            System.out.println("Line cleared successfully.");
        } 
		else {
            System.out.println("Target number not found in the file.");
        }
    }
    
}