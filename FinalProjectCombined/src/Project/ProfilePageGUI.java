package Project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class ProfilePageGUI extends JFrame {
    public ProfilePageGUI(String username) throws FileNotFoundException {
    	
    	String filename = createUserFile(username);
    	
        // Set up the JFrame for the profile page
        setTitle(username + "'s Profile");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create and add profile components
        JPanel profilePanel = new JPanel(new GridLayout(4, 1, 5, 5));

        JLabel titleLabel = new JLabel(username + "'s Profile");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profilePanel.add(titleLabel);

        JButton openAccountButton = new JButton("Open Account");
        openAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open the OpenAccountPage
            	dispose();
            	new OpenAccountPageGUI(username, filename); //open the open account page
            }
        });
        profilePanel.add(openAccountButton);

        JButton viewAccountsButton = new JButton("View Accounts");
        viewAccountsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				try {
					new ViewAccountsPageGUI(username, filename);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        profilePanel.add(viewAccountsButton);

        JButton changeProfileInfoButton = new JButton("Change Profile Info");
        changeProfileInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ChangeProfileInfoGUI(username);
            }
        });
        profilePanel.add(changeProfileInfoButton);

        add(profilePanel, BorderLayout.CENTER);

        // Display the JFrame
        setVisible(true);
    }
    
    
    public static String createUserFile(String username) {
    	String filename = username + "ProfileInfo.csv";
		
		//create text file for user profile to hold their account data
		try {
			File accountInfo = new File(filename);
		    if (accountInfo.createNewFile()) {
		    	System.out.println("File created: " + accountInfo.getName());
		    } 
		    else {
		    	System.out.println("File already exists.");
		    }
		} catch (IOException e) {
			System.out.println("An error occurred.");
		}
		
		return filename;
    }
    
    
}