
package Project.Backend;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthUtils {

    // Method to hash a plain text password using SHA-256 hashing algorithm.
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256"); // Get an instance of SHA-256 from MessageDigest.
        md.update(password.getBytes()); // Update the digest using the byte representation of the password.
        byte[] digest = md.digest(); // Complete the hash computation and get the hash bytes.
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) { // Convert each byte in the hash to a hex value and append to StringBuilder.
            sb.append(String.format("%02x", b));
        }
        return sb.toString(); // Return the final hashed password in hexadecimal format.
    }

    // Method to verify if an input password matches the stored hashed password.
    public static boolean verifyPassword(String inputPassword, String storedHashedPassword) throws NoSuchAlgorithmException {
        String hashedInput = hashPassword(inputPassword); // Hash the input password using the same method used for storing.
        return hashedInput.equals(storedHashedPassword); // Compare the input hashed password with the stored hash.
    }
}
