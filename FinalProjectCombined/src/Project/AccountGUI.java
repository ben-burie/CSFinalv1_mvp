package Project;

import javax.swing.*;

import Project.Backend.CheckingAccount;
import Project.Backend.SavingsAccount;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AccountGUI extends JFrame {
    private JTextField accountNumberField;

    public AccountGUI(String filename, String accNum, String username) {
       
        setTitle("Account # " + accNum);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				try {
					new accountBalanceGUI(username, filename, accNum);
				} catch (IOException e1) {
						e1.printStackTrace();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
				dispose();
            }
        });
        panel.add(checkBalanceButton);
        
        JButton withdrawMoneyButton = new JButton("Withdraw Money");
        withdrawMoneyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               	new withdrawGUI(username, filename, accNum);
            	dispose();
            }
        });
        panel.add(withdrawMoneyButton);     
        
        JButton depositMoneyButton = new JButton("Deposit Money");
        depositMoneyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               	new depositGUI(username, filename, accNum);
            	dispose();
            }
        });
        panel.add(depositMoneyButton);        
        
        
        JButton transferMoneyButton = new JButton("Transfer Money");
        transferMoneyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               	new TransferGUI(username, accNum, filename);
            	dispose();
            }
        });
        panel.add(transferMoneyButton);
        
        
        
        JButton closeAccountButton = new JButton("Close account");
        
        JButton goBackButton = new JButton("Go back");
        goBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open the ProfilePageGUI
            	dispose();
            }
        });
        panel.add(goBackButton);
        add(panel, BorderLayout.CENTER);

        
        
        
        // Display the JFrame
        setVisible(true);
    }
    
	public static Object enterAccount(String accNum, String filename, String username) throws IOException { //////////////////////
		
		File file = new File(filename);
		Scanner reader = new Scanner(file);

		String accountOpenType = null;
		
		SavingsAccount openSavings = null;
		CheckingAccount openChecking = null;
		
		
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			String[] items = line.split(",");
			if (items[1].equals(accNum)) {
				if (items[0].equals("Savings")) {
					accountOpenType = "Savings";
					openSavings = new SavingsAccount(items[0], Integer.parseInt(items[1]), Double.parseDouble(items[2]));
				}
				else {
					accountOpenType = "Checking";
					openChecking = new CheckingAccount(items[0], Integer.parseInt(items[1]), Double.parseDouble(items[2]));
				}
			}
		}
		
		if (accountOpenType.equals("Savings")) {
			return openSavings;
		}
		else {
			return openChecking;
		}
		
	}
	
	public static void editAccount(int accNum, Object account, String filename) throws IOException {
			
			File file = new File(filename);
			Scanner reader = new Scanner(file);
			StringBuilder content = new StringBuilder();
			
			
			boolean isFound = false;
			
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] items = line.split(",");
				
				if (Integer.parseInt(items[1]) == accNum) {
					isFound = true;
				}
				else {
		            content.append(line).append("\n");
		        }
			}
			
			if (isFound == true) {
				
				content.append(account.toString()).append("\n");
				
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