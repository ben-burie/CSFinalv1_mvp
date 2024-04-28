//package Project;
package Project;

import javax.swing.*;

import Project.Backend.Account;

import Project.ProfilePageGUI;
import Project.AccountGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class accountBalanceGUI extends JFrame{
	private JLabel balanceLabel;
	
	public  accountBalanceGUI(String username, String filename, String accNum) throws IOException {
		
		// Set up the JFrame for Account Balance
        setTitle("Account Balance");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        
        // Create and add components for entering account number
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        balanceLabel = new JLabel("Account Balance: " + ((Account) AccountGUI.enterAccount(accNum, filename, username)).checkBalance());
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(balanceLabel);
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
				new AccountGUI(filename, accNum, username);
        	}
        });
        
        panel.add(backButton);

        add(panel, BorderLayout.CENTER);;
          
     // Display the JFrame
        setVisible(true); 
	}

}
