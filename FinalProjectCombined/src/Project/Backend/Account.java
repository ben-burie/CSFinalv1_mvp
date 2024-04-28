package Project.Backend;
import java.util.Random;

import javax.swing.JOptionPane;

public class Account {

	Random rand = new Random();
	
	protected String accType;
	protected int accNum;
	protected double accBalance;
	private double overdraftFee = 20.00;
	
	public Account(String accType, int accNum, double accBalance) {
		this.accType = accType;
		this.accNum = accNum;
		this.accBalance = accBalance;
	}
	
	public Account () {
		accType = null;
		accNum = rand.nextInt(11111111, 99999999);
		accBalance = 0.00;
	}
	
	public String getAccType() {
		return accType;
	}
	
	public int getAccNum() {
		return accNum;
	}
	
	public double checkBalance() {
		return this.accBalance;
	}
	
	public void withdraw(double amount) {
		if (this.accType.equals("Savings")) {
			if ((this.accBalance - amount) >= 0) {
				this.accBalance -= amount;
			}
			else {
				JOptionPane.showMessageDialog(null, "Amount cannot be withdrawn, balance is too low!");
			}
		}
		else {
			if ((this.accBalance - amount) >= 0) {
				this.accBalance -= amount;
			}
			else {
				JOptionPane.showMessageDialog(null, "Overdraft fee will be charged!");
				this.accBalance -= (amount + this.overdraftFee);
			}
		}
	}
	
	public void deposit(double amount) {
		this.accBalance += amount;
	}
	
	public void transfer(double amount, Account receivingAcc) {
		this.accBalance -= amount;
		receivingAcc.accBalance += amount;
	}
	
}
