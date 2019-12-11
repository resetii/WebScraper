/**
 * Runs a web parser to get data from a site and then opens a GUI to let user interact with the data. 
 * @author James Campion
 */
public class WebParser {
	public static void main(String[] args) {
		ApplicationWindow frm = new ApplicationWindow();
        frm.setVisible(true);
        frm.BuildUI();
	}
}
