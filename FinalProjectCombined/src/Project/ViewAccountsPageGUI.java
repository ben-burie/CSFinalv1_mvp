package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ViewAccountsPageGUI extends JFrame {
    public ViewAccountsPageGUI(String username, String filename) throws FileNotFoundException {
    	
    	JTextField enterAccountField;
    	
        // Set up the JFrame for the view accounts page
        setTitle("View Accounts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create and add components for viewing accounts
        JPanel viewAccountsPanel = new JPanel(new GridLayout(20, 1));

        JLabel titleLabel = new JLabel("View Accounts");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        viewAccountsPanel.add(titleLabel, BorderLayout.NORTH);

        
        JLabel breakLabel = new JLabel("<html><br><br></html>");
        breakLabel.setHorizontalAlignment(SwingConstants.CENTER);
        viewAccountsPanel.add(breakLabel, BorderLayout.CENTER);
        
        //view accounts
        File file = new File(filename);
		Scanner reader = new Scanner(file);
		
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			String[] items = line.split(",");
			System.out.println(items[0] + " Account #" + items[1] + " -> Balance: " + items[2]); 
			
			JLabel accountLabel = new JLabel(items[0] + " Account #" + items[1] + " -> Balance: " + items[2]);
	        accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        viewAccountsPanel.add(accountLabel, BorderLayout.CENTER);
		}
		
		reader.close();
		
		
		//user wants to enter an account?
		viewAccountsPanel.add(new JLabel("Enter Account #:"));
        enterAccountField = new JTextField();
        viewAccountsPanel.add(enterAccountField);
        
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String accNum = enterAccountField.getText();
            	int accNumInt = Integer.parseInt(accNum);
            	try {
					if (checkAccountExists(filename, accNumInt) == true) {
						dispose();
						new AccountGUI(filename, accNum, username);
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid account number!");
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            }
        });
        
        viewAccountsPanel.add(enterButton);
        
        JLabel breakLabel2 = new JLabel("<html><br><br></html");
        viewAccountsPanel.add(breakLabel2);
		
		//ben code ends

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
					new ProfilePageGUI(username);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            }
        });
        viewAccountsPanel.add(backButton);

        add(viewAccountsPanel, BorderLayout.CENTER);

        // Display the JFrame
        setVisible(true);
    }
    
    public static boolean checkAccountExists(String filename, int accChoice) throws FileNotFoundException {
		File file = new File(filename);
		Scanner reader = new Scanner(file);


		boolean isFound = false;
		
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			String[] items = line.split(",");
			if (Integer.parseInt(items[1]) == accChoice) {
				isFound = true;
			}
		}
		reader.close();
		
		return isFound;
	}
    
    
}