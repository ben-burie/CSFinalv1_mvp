
package Project.Backend;

import java.io.*;
import java.util.*;

public class AuthenticationService {
    private Map<String, String> userDatabase = new HashMap<>();
    private static final String DB_FILE = "C:\\Users\\benbu\\eclipse-workspace\\CS220\\FinalProjectCombined\\AccountDatabase.csv"; // Ensure this path is correct

    public AuthenticationService() {
        loadUserData();
    }

    private void loadUserData() {
        try (BufferedReader br = new BufferedReader(new FileReader(DB_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    userDatabase.put(data[0], data[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        if (userDatabase.containsKey(username)) {
            try {
                return AuthUtils.verifyPassword(password, userDatabase.get(username));
            } catch (Exception e) {
                System.out.println("Failed to verify password: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    private void saveUserData() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DB_FILE))) {
            for (Map.Entry<String, String> entry : userDatabase.entrySet()) {
                pw.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to register or update a user's password
    public void registerOrUpdateUser(String username, String password) {
        try {
            String hashedPassword = AuthUtils.hashPassword(password); // Hash the password
            userDatabase.put(username, hashedPassword); // Add or update the username and hashed password in the map
            saveUserData(); // Save the updated user data back to the CSV file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}