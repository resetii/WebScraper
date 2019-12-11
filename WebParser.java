import java.util.ArrayList;

public class WebParser {

	public static void main(String[] args) {
		
		ApplicationWindow frm = new ApplicationWindow();
        frm.setVisible(true);
        frm.BuildUI();
		
		// https://terra.snellman.net/app/results/v2/2019/09/18
		//ArrayList<User> users = WebScraper.scrapeToUser("https://terra.snellman.net/app/results/v2/2018/09/18");
		//UserWriter.printUsersToScreen(users);
        
		//UserWriter.printUsersToTextFile(users, "users.txt");
		//UserWriter.writeUsersToJSON(users, "Userlist.json"); CHECK LATER

	}
}
