import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONObject;

/**
 * Writes user data to screen, text file, or JSON
 * @author James Campion
 */
public class UserWriter {
	/**
	 * Uses override toString to print all user objects in a list to screen
	 * @param users ArrayList of user objects
	 */
	public static void printUsersToScreen(ArrayList<User> users) {
		for (User name : users) {
			System.out.println(name.toString());
			System.out.println("--------------------");
		}
	}
	
	/**
	 * Prints users into colon delimited text file
	 * @param users ArrayList of user objects
	 * @param file A text file to write to 
	 * @return Return true if it successfully wrote to the file
	 */
	public static boolean printUsersToTextFile(ArrayList<User> users, File file) {
		try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            // Prints users on their own line separated by spaces and a colon
            for (User name : users) {
            	pw.printf("%s : %s\n", name.getUsername(),name.getHashID());
            }
            pw.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
	}
	
	/**
	 * Prints users into colon delimited text file
	 * @param users ArrayList of user objects
	 * @param fname What you want to name file that is generated
	 * @return Return true if it successfully wrote to the text file
	 */
	public static boolean printUsersToTextFile(ArrayList<User> users, String fname) {
        File f = new File(fname);
        return printUsersToTextFile(users, f);
    }
	
	/**
	 * Writes user list to a JSON file
	 * @param users ArrayList of user objects
	 * @param fname What you want to name file that is generated
	 * @return Return true if it successfully wrote to the json file
	 */
	public static boolean writeUsersToJSON(ArrayList<User> users, String fname) {
		try  {
			FileWriter file = new FileWriter(fname);
		    JSONObject obj = new JSONObject();
		    for (User name : users) {
		    	obj.put("username", name.getUsername());
		    	obj.put("hash ID", name.getHashID());
		    }
		    String jsonStr = obj.toString();
		    PrintWriter prt1 = null;
		    prt1 = new PrintWriter(new FileWriter(fname));
		    prt1.write(jsonStr);
		    return true;
		} catch (Exception E)  {
		    System.out.println(E);
		    return false;
		} 
	}
}
