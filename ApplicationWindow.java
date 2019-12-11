import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * This is the application that builds and manages the GUI. 
 * This was tested with the following URL input:
 * https://terra.snellman.net/app/results/v2/2018/09/18
 * @author James Campion
 * @version 1.0
 */
public class ApplicationWindow extends JFrame  {

	private JTextArea text;
	ArrayList<User> users;
	String urlInput; 

	/**
	 * This setups up the top menu bar with the file and help options
	 * @param File shows the user the option to exit
	 * @param Help shows the user an about option that displays info 
	 */
    public void setupMenu() {
        JMenuBar mbar = new JMenuBar();
        // create the file and help menus at top 
        JMenu mnuFile = new JMenu("File");
        JMenu mnuHelp = new JMenu("Help");
        
        // Add options to menus
        JMenuItem miExit = new JMenuItem("Exit");
        miExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuFile.add(miExit);
        
        // Adding the info to the About section
        JMenuItem helpAbout = new JMenuItem("About");
        mnuHelp.add(helpAbout);
        helpAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(null, "Written by James Campion\nThis program will accept url input in the form of\nhttps://terra.snellman.net/app/results/v2/YYYY/MM/DD\nand you can fill in any date.");
            	
            }
        });
        
        // Finalize the top bar
        mbar.add(mnuFile);
        mbar.add(mnuHelp);
        setJMenuBar(mbar);
    }
    
    /**
     * Builds the UI elements. Generates a windows with the elements and various buttons. 
     * User will type in the address and fetch data from that URL 
     */
    public void BuildUI() {
    	// Building up basic UI elements and sizing up and putting the bottom flow on 
        setTitle("Web Scraper");
        setBounds(100,50,500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new FlowLayout());
        
        // create the saving buttons for the bottom
        JButton btnText = new JButton("Save to text");
        JButton btnJson = new JButton("Save to json");
        
        // These buttons call user writer and give you a pop-up message when the write process completes
        btnText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                UserWriter.printUsersToTextFile(users, "UsersList.txt");
                JOptionPane.showMessageDialog(null, "Printed to UsersList.txt");
            }
        });
        btnJson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent bc) {
                UserWriter.writeUsersToJSON(users, "TMUsersList");
                JOptionPane.showMessageDialog(null, "Printed to TMUsersList");
            }
        });

        panSouth.add(btnText);
        panSouth.add(btnJson);
        c.add(panSouth, BorderLayout.SOUTH);
        
        // create a scrollable text area in the center
        text = new JTextArea();
        Font f = new Font("Monospaced",Font.BOLD,24);
        text.setFont(f);
        text.setEditable(false);
        JScrollPane scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        // Generate the top bar with the url field
        JPanel topPan = new JPanel(new BorderLayout());
        JLabel urlLabel = new JLabel("Enter URL:");
        JTextField urlField = new JTextField();
        
        // set up the fetch button to take the user input
        JButton urlFetch = new JButton("Fetch");
        urlFetch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent cd) {
                urlInput = urlField.getText();
                JOptionPane.showMessageDialog(null, "Fetching from "+ urlInput);
            	users = WebScraper.scrapeToUser(urlInput);
                for (int i=0; i < users.size(); i++) {
                    text.append("Username: "+ users.get(i).getUsername() + "\nHash ID: " + users.get(i).getHashID() + "\n\n");
                }
            }
        });
        
        // align all the fields and panels up on the window
	    urlLabel.setLabelFor(urlField);
	    topPan.add(urlLabel, BorderLayout.WEST);
	    topPan.add(urlField, BorderLayout.CENTER);
	    topPan.add(urlFetch, BorderLayout.EAST);
	    c.add(topPan, BorderLayout.NORTH);
	    c.add(scroll, BorderLayout.CENTER);

	    // create the top menu bar
	    setupMenu();
    }
}