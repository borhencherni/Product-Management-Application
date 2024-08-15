package application;

public class User {
	    private String username;
	    private String password;
	    private String role;
	    private String id;

	    // Constructor
	    public User(String username, String password , String id) {
	        this.username = username;
	        this.password = password;
	        this.id=id;
	        
	    }

	    // Getters
	    public String getUsername() {
	        return username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public String getRole() {
	        return role;
	    }
	    
	    public String getid() {
	        return id;
	    }

}
