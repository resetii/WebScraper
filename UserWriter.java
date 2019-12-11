import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONObject;

public class UserWriter {
	public static void printUsersToScreen(ArrayList<User> users) {
		for (User name : users) {
			System.out.println(name.toString());
			System.out.println("--------------------");
		}
	}
	
	public static boolean printUsersToTextFile(ArrayList<User> users, File file) {
		try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            for (User name : users) {
            	pw.printf("%s : %s\n", name.getUsername(),name.getHashID());
            }
            pw.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
	}
	
	public static boolean printUsersToTextFile(ArrayList<User> users, String fname) {
        File f = new File(fname);
        return printUsersToTextFile(users, f);
    }
	
	public static boolean writeUsersToJSON(ArrayList<User> users, String fname) {

		try  {
			FileWriter file = new FileWriter(fname);
		    JSONObject obj = new JSONObject();
		    for (User name : users) {
		    	obj.put(name.getUsername(), name.getHashID());
		    }
		    file.write(obj.toString());
		    return true;
		} catch (Exception E)  {
		    System.out.println(E);
		    return false;
		} 
	}
}
