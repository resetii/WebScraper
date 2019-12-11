import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class scrapes the website for the information we are looking for 
 * @author James Campion
 * @version 1.0
 */
public class WebScraper {
	/**
	 * The website in question are from the snellman Terra Mystica competitive server data logs. URL are in format:
	 * https://terra.snellman.net/app/results/v2/YYYY/MM/DD 
	 * and you can fill in any date by replacing the YYYY, MM, and DD with year, month and date respectively. 
	 * Since these web pages are all data dumps meant to be parsed they contain simple strings and 
	 * has predictable formatting. I delimited the string by colons and am able to read in the usernames and IDs
	 * because they end up being the 2nd and 4th item respectively after the word username shows up. 
	 * @param url A string for url we are taking in 
	 * @return ArrayList of user objects generated from the site
	 */
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
