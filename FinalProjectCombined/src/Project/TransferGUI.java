package Project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Project.Backend.Account;

public class TransferGUI extends JFrame {
	
	private JLabel transferLabel;
	private JTextField amountField;
	private JTextField accountField;

	public TransferGUI(String username, String accNum, String filename) {
		setTitle("Transfer Money");
	    setSize(600, 400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null); // Center the window
		
	    JPanel panel = new JPanel(new GridLayout(0, 1));
	    JPanel inputPanel = new JPanel(new GridLayout(0, 2));
	    
	    transferLabel = new JLabel("Transfer Money");
	    transferLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    panel.add(transferLabel);
	    
	    JLabel accountLabel = (JLabel) inputPanel.add(new JLabel("Amount: $"));
        accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountField = new JTextField();
        inputPanel.add(amountField);
	    
	    JLabel amountLabel = (JLabel) inputPanel.add(new JLabel("Receiving Account #: "));
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountField = new JTextField();
        inputPanel.add(accountField);
        
        panel.add(inputPanel);
        
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		double amount = Double.parseDouble(amountField.getText());
        		String receivingAcc = accountField.getText();
        		
        		try {
					Account a1 = ((Account) AccountGUI.enterAccount(accNum, filename, username));
					Account a2 = ((Account) AccountGUI.enterAccount(receivingAcc, filename, username));
					a1.transfer(amount, a2);
					AccountGUI.editAccount(Integer.parseInt(accNum), a1, filename);
					AccountGUI.editAccount(Integer.parseInt(receivingAcc), a2, filename);
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
        	    new AccountGUI(username, filename, accNum);
        	}
        });
        
        panel.add(submitButton);
        panel.add(backButton);
        
        add(panel, BorderLayout.CENTER);
        

        setVisible(true); 
	    
	    
	}
	
}
