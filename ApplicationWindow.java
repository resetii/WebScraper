import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;  // simple ready-made dialogs
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ApplicationWindow extends JFrame  {

	private JTextArea text;
	ArrayList<User> users;
	String urlInput = "https://terra.snellman.net/app/results/v2/2018/09/18"; // set a default just in case user doesn't fetch
	
    public void fillTextArea(File file) {
        try {
            Scanner fsc = new Scanner(file);
            String content = "";
            while (fsc.hasNextLine()) {
                content = content + fsc.nextLine() + "\n";
            }
            fsc.close();
            text.setText(content);
        } catch (Exception ex) {
            text.setText("Error reading file");
        }
    }
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
    public void BuildUI() {
        setTitle("Web Scraper");
        setBounds(100,50,400,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new FlowLayout());
        
        JButton btnText = new JButton("Save to text");
        JButton btnJson = new JButton("Save to json");
        
        btnText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                UserWriter.printUsersToTextFile(users, "UsersList.txt");
                JOptionPane.showMessageDialog(null, "Printed to UsersList.txt");
            }
        });
        
        btnJson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent bc) {
                JOptionPane.showMessageDialog(btnText, "JSON");
            }
        });

        panSouth.add(btnText);
        panSouth.add(btnJson);

        c.add(panSouth, BorderLayout.SOUTH);
        text = new JTextArea();
        Font f = new Font("Monospaced",Font.BOLD,24);
        text.setFont(f);
        text.setEditable(false);
        JScrollPane scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        JPanel topPan = new JPanel(new BorderLayout());
        JLabel urlLabel = new JLabel("Enter URL:");
        JTextField urlField = new JTextField();
        
        JButton urlFetch = new JButton("Fetch");
        urlFetch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent cd) {
                urlInput = urlField.getText();
                JOptionPane.showMessageDialog(null, "Fetching from "+ urlInput);
                for (int i=0; i < users.size(); i++) {
                    text.append("Username: "+ users.get(i).getUsername() + "\nHash ID: " + users.get(i).getHashID() + "\n\n");
                }
            }
        });
        
	    urlLabel.setLabelFor(urlField);
	    topPan.add(urlLabel, BorderLayout.WEST);
	    topPan.add(urlField, BorderLayout.CENTER);
	    topPan.add(urlFetch, BorderLayout.EAST);
	    c.add(topPan, BorderLayout.NORTH);
	    c.add(scroll, BorderLayout.CENTER);

    	users = WebScraper.scrapeToUser(urlInput);
	    setupMenu();

    }
    
}