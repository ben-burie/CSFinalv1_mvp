
package Project.Backend;

/*
 * Author: Dante Poe
 */
public class User {
    private String username; // Stores the username of the user.
    private String hashedPassword; // Stores the hashed password of the user.

    // Constructor to initialize a user object with a username and a hashed password.
    public User(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    // Getter method to return the username of the user.
    public String getUsername() {
        return username;
    }

    // Getter method to return the hashed password of the user.
    public String getHashedPassword() {
        return hashedPassword;
    }
}
