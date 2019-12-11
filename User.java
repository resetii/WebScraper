/**
 * This is a class called user that contains a username and hash ID of a user that visited 
 *   the snellman competitive Terra Mystica server.
 * @author James Campion
 * @version 1.0
 */
public class User {
	String username, hashID;
	
	/**
	 * Generic default constructor
	 */
	public User() {
		this.username = "";
		this.hashID = "";
	}
	
	/**
	 * Constructs the user with given username (string) and hashID (string)
	 * @param username Username of a visitor
	 * @param hashID Their hash id the server generates
	 */
	public User(String username, String hashID) {
		this.username = username;
		this.hashID = hashID;
	}

	/**
     * Overrides Java's toString function to be able to print users in desired format
     * @return a formatted list of all data for that user
     * @see java.lang.Object.toString
     */
	@Override
    public String toString() { 
    	return String.format("Username: %s\nHash ID: %s", username, hashID);
    }
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHashID() {
		return hashID;
	}
	public void setHashID(String hashID) {
		this.hashID = hashID;
	}
	
}
