package Project.Backend;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * NOTES:
 * 
 * -make it so user can only create a max number of checking accounts
 * 
 */

public class Demo {

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter username: ");
		String username = input.next();
		
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
		
		//ABOVE ADDED 4-17 ^^^^^^
		
		//START SCREEN 3 CODE
		System.out.println("\nWelcome to " + username + "'s profile!");
		System.out.println("Choose an action:\n1.Open an Account\n2.View Accounts\n3.Change Profile Info");
		int choice = input.nextInt();
		//END SCREEN 3 CODE
		
		
		//START SCREEN 4A CODE
		//user opening a new account
		if (choice == 1) {
			System.out.println("Savings or Checking?");
			String type = input.next();
			
			if (type.equalsIgnoreCase("savings")) {
				Account s = new SavingsAccount();
				openAccount(s, filename);
			}
			else if (type.equalsIgnoreCase("checking")) {
				Account c = new CheckingAccount();
				openAccount(c, filename);
			}
		}
		//END SCREEN 4A CODE
		//ADDED 4-17 ^^^^^^^^^

		//START SCREEN 4B CODE, also more in another method
		//user viewing existing accounts
		else if (choice == 2) {
			System.out.println("\n" + username + "'s Accounts\n");
			viewAccountsList(filename);
			System.out.print("Enter account number to view/modify: ");
			int accChoice = input.nextInt();
			if (checkAccountExists(filename, accChoice) == true) {
				enterAccount(accChoice, filename);
			}
			else {
				System.out.println("Account not found, please try again: ");
				accChoice = input.nextInt();
			}
		}
		//END SCREEN 4B CODE
		
		//user changing profile info

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
	
	//START SCREEN 4B CODE
	public static void viewAccountsList(String filename) throws FileNotFoundException {
		//prints all user accounts
		File file = new File(filename);
		Scanner reader = new Scanner(file);

		
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			String[] items = line.split(",");
			System.out.println(items[0] + " Account #" + items[1] + " -> Balance: " + items[2]); 
		}
		
		reader.close();

	}
	//END SCREEN 4B CODE
	
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
	
	//START SCREEN 5 CODE
	public static void enterAccount(int accNum, String filename) throws IOException { //////////////////////
		
		File file = new File(filename);
		Scanner reader = new Scanner(file);

		String accountOpenType = null;
		
		SavingsAccount openSavings = null;
		CheckingAccount openChecking = null;
		
		
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			String[] items = line.split(",");
			if (Integer.parseInt(items[1]) == accNum) {
				if (items[0].equals("Savings")) {
					accountOpenType = "Savings";
					openSavings = new SavingsAccount(items[0], Integer.parseInt(items[1]), Double.parseDouble(items[2]));
				}
				else if (items[0].equals("Checking")) {
					accountOpenType = "Checking";
					openChecking = new CheckingAccount(items[0], Integer.parseInt(items[1]), Double.parseDouble(items[2]));
				}
			}
		}
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nAccount #" + accNum + " - Choose an action: ");
		System.out.println("1. Check Balance\n2.Withdraw Money\n3.Deposit Money\n4.Close Account\n5. Quit");
		int userChoice = input.nextInt();
		
		
		if (accountOpenType.equals("Savings")) {
			if (userChoice == 1) { //check balance
				System.out.println("Balance: " + openSavings.checkBalance());
			}
			if (userChoice == 2) { //withdraw money
				System.out.println("Enter amount to withdraw: ");
				double withdrawAmount = input.nextDouble();
				openSavings.withdraw(withdrawAmount);
				editAccount(accNum, openSavings, filename);
			}
			if (userChoice == 3) {
				System.out.println("Enter amount to deposit: ");
				double depositAmount = input.nextDouble();
				openSavings.deposit(depositAmount);
				editAccount(accNum, openSavings, filename);
			}
		}
		if (accountOpenType.equals("Checking")) {
			if (userChoice == 1) { //check balance
				System.out.println("Balance: " + openChecking.checkBalance());
			}
			if (userChoice == 2) { //withdraw money
				System.out.println("Enter amount to withdraw: ");
				double withdrawAmount = input.nextDouble();
				openChecking.withdraw(withdrawAmount);
				editAccount(accNum, openChecking, filename);
			}
			if (userChoice == 3) {
				System.out.println("Enter amount to deposit: ");
				double depositAmount = input.nextDouble();
				openChecking.deposit(depositAmount);
				editAccount(accNum, openChecking, filename);
			}
		}
		
		
		reader.close();
		input.close();
	}
	//END SCREEN 5 CODE
	
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
