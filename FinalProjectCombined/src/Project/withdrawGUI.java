package Project;
import javax.swing.*;

import Project.Backend.Account;

//import Project.ProfilePageGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class withdrawGUI extends JFrame{
	private JLabel balanceLabel;
	private JTextField amountField;
	
	public  withdrawGUI(String username, String filename, String accNum) {
		
		// Set up the JFrame for Account Balance
      setTitle("Withdraw");
      setSize(600, 400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null); // Center the window
		
      // Create and add components for entering account number
      JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
      JPanel inputPanel = new JPanel(new GridLayout(1, 2));
      
      balanceLabel = new JLabel("Withdraw");
      balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(balanceLabel);
      
      JLabel amountLabel = (JLabel) inputPanel.add(new JLabel("Amount: $"));
      amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
      amountField = new JTextField();
      inputPanel.add(amountField);
      
      panel.add(inputPanel);
      
      JButton submitButton = new JButton("Submit");
      submitButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
      		double amount = Double.parseDouble(amountField.getText());
      		try {
					Account a1 = ((Account) AccountGUI.enterAccount(accNum, filename, username));
					a1.withdraw(amount);
					AccountGUI.editAccount(Integer.parseInt(accNum), a1, filename);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
      		dispose();
      	}
      });
      
      JButton backButton = new JButton("Back");
      backButton.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		dispose();
      	    new AccountGUI(filename, accNum, username);
      		
      	}
      });
      
      
      panel.add(submitButton);
      panel.add(backButton);

      add(panel, BorderLayout.CENTER);
        
   // Display the JFrame
      setVisible(true); 
	}
}
