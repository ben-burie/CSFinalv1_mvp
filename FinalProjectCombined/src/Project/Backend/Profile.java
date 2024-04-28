package Project.Backend;

public class Profile {
	
	private String username;
	private String password;
	
	public Profile(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Profile() {
		//default constructor
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String toString() {
		return this.username + ", " + this.password;
	}

}
