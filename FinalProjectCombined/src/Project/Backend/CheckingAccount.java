package Project.Backend;
public class CheckingAccount extends Account{

	private double overdraftFee;
	
	public CheckingAccount(String accType, int accNum, double accBalance) {
		super(accType, accNum, accBalance);
	}
	
	public CheckingAccount() { //default constructor
		super.accType = "Checking";
		overdraftFee = 20.00;
	}
	
	public String toString() {
		return accType + "," + accNum + "," + accBalance + "," + overdraftFee;
	}
	
}
