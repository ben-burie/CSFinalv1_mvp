package Project;

import javax.swing.*;

import Project.Backend.Account;
import Project.Backend.CheckingAccount;
import Project.Backend.SavingsAccount;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

class OpenAccountPageGUI extends JFrame {
    private JRadioButton checkingRadioButton;
    private JRadioButton savingsRadioButton;

    public OpenAccountPageGUI(String username, String filename) {
        // Set up the JFrame for the open account page
        setTitle("Open Account");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create and add components for opening an account
        JPanel openAccountPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        JLabel openAccountLabel = new JLabel("Open Account");
        openAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        openAccountPanel.add(openAccountLabel);

        JPanel radioPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        checkingRadioButton = new JRadioButton("Checking Account");
        savingsRadioButton = new JRadioButton("Savings Account");
        ButtonGroup group = new ButtonGroup();
        group.add(checkingRadioButton);
        group.add(savingsRadioButton);
        radioPanel.add(checkingRadioButton);
        radioPanel.add(savingsRadioButton);
        openAccountPanel.add(radioPanel);

        JButton openAccountButton = new JButton("Open Account");
        openAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your logic to open the selected account type
                String accountType = "";
                if (checkingRadioButton.isSelected()) {
                    accountType = "Checking Account";
                    Account s = new SavingsAccount();
    				openAccount(s, filename);
                } else if (savingsRadioButton.isSelected()) {
                    accountType = "Savings Account";
                    Account c = new CheckingAccount();
    				openAccount(c, filename);
                }
                JOptionPane.showMessageDialog(null, "Opening " + accountType + " for " + username);
                dispose();
            	try {
					new ViewAccountsPageGUI(username, filename);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
            }   
        });
        openAccountPanel.add(openAccountButton);

        add(openAccountPanel, BorderLayout.CENTER);

        // Display the JFrame
        setVisible(true);
    }
    
    public static void openAccount(Account acc, String filename) {
		//write account into to user file
		try {
			FileWriter writer = new FileWriter(filename, true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(acc.toString() + "\n");
			bufferedWriter.close();
		} catch (IOException e) {
			System.out.println("Error!");
			e.printStackTrace();
		}
		System.out.println(acc.getAccType() + " Account #" + acc.getAccNum() + " has been created."); //doesn't need to be added on GUI
	}
    
}