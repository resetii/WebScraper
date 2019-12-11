import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class WebScraper {

	public static ArrayList<User> scrapeToUser (String url) {
		String addr = url;
        try {
            URL link = new URL(addr);
            Scanner usc = new Scanner(link.openStream());
            String out = null;
            while (usc.hasNextLine()) {
                out = usc.nextLine();
            }
            usc.close();
            String[] splitStr = out.split("\"");
    		ArrayList<User> users = new ArrayList<User>();
    		for (int i = 0; i < splitStr.length; i++) {
    			String test = splitStr[i].trim();
    			if ( test.equals("username") ) {
    				users.add(new User(splitStr[i+2],splitStr[i+4]));
    			}
    		}
            users.remove(users.size() - 1); // cutting out the moderator that is at the end of every list
    		return users;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Bad connection or wrong URL");
        }
		return null;
	}
}
