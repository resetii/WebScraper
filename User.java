public class User {
	String username, hashID;
	
	public User() {
		this.username = "";
		this.hashID = "";
	}
	
	public User(String username, String hashID) {
		this.username = username;
		this.hashID = hashID;
	}

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
