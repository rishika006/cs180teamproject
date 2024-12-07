import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner; //TODO delete this line after converting displayProfilePicture to GUI

/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Contains methods & GUI screens/elements for user actions once logged in.
 * The user action buttons are for adding contacts, messaging, etc.
 * Interacts with server to display output in the GUI.
 *
 * @author Nicholas Chong
 *
 * @version 11/27/2024
 *
 */

public class UserActions {

    private Client client;
    private PrintWriter output;
    private BufferedReader bfr;


    public UserActions(Client client) {
        this.client = client;
        this.output = client.getOutput();
        this.bfr = client.getBfr();
    }


    // --------------------------------------
    // TODO: idk format for displaying image -- pls do this
    // TODO: turn this (terminal code) into GUI
    // TODO: look at the methods below to copy 'back' button if needed
    // display profile picture (server code 3)
    public void displayProfilePic() throws IOException {
        output.println("3");
        System.out.println("Enter user to get profile picture for: ");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        // instantiate HomePage to use its methods
        HomePage homePage = new HomePage(client);
        homePage.showHomePage();
    }
    // TODO: once you fix this method, delete the scanner import at the top



    // --------------------------------------
    // view contacts (server code 5)
    public void viewContacts() {
        // create frame & panel
        JFrame frame = new JFrame("My Contacts");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel panel = new JPanel() ;

        ImageIcon front = new ImageIcon("frontBg.jpg") ;
        Image frontImage = front.getImage().getScaledInstance(1750 , 1200 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        // labels & buttons
        JLabel label = new JLabel("MY CONTACTS");
        label.setLayout(null);

        label.setBackground(new Color(0x053e71));
        label.setFont(new Font("Monospaced", Font.PLAIN, 25));
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBounds(0, 0, 700, 900); // Adjusted position and size
        label.setIcon(frontIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-300) ;

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        frame.getContentPane().add(label);
        label.setLayout(null); // Ensure absolute positioning


        // server code
        output.println("5");
        // create JList for list of contacts
        try {
            String[] contacts = bfr.readLine().split(";");
            JList<String> contactList = new JList<>(contacts);
            contactList.setFont(new Font("Monospaced" , Font.PLAIN , 20));
            contactList.setForeground(Color.white);
            contactList.setBackground(new Color(0 , 0, 0 ,0 ));

            // make scrollable
            JScrollPane scrollPane = new JScrollPane(contactList);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setOpaque(false);
            scrollPane.setBounds(150 , 240 , 500 , 700);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            frame.add(scrollPane , JLabel.CENTER) ;
        } catch (IOException e) {
            JLabel error = new JLabel("Error reading contacts list.");
            error.setBounds(400 ,400 , 200 , 200);
            frame.add(error);
        }

        // back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(270 , 565 , 150 ,50);
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });
        frame.add(backButton , JLabel.CENTER);

        // add panel to frame

        frame.setVisible(true);
    }



    // --------------------------------------
    // add new contact (server code 6)
    public void addNewContact() {
        // create frame & panel
        // create a frame
        JFrame frame = new JFrame("Add Contact") ;
        frame.setLayout(new FlowLayout());
        frame.setLocation(400 , 0);
        frame.setSize(new Dimension(700 , 960));
        frame.getContentPane().setBackground(new Color(0x141414));



        JPanel topPanel = new JPanel() ;
        topPanel.setPreferredSize(new Dimension(2000 , 1000));
        topPanel.setBackground(new Color(0x141414));
        topPanel.setLayout(null);

        JLabel banner = new JLabel("        New Contact") ;
        banner.setFont(new Font("Monospaced", Font.BOLD, 40));
        banner.setForeground(new Color(0x6eaa6b));
        banner.setOpaque(true);
        ImageIcon front = new ImageIcon("img_1.png") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 325 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        banner.setIcon(frontIcon);
        banner.setHorizontalTextPosition(JLabel.CENTER);
        banner.setVerticalTextPosition(JLabel.TOP);
        banner.setIconTextGap(-200);

        banner.setVerticalAlignment(JLabel.CENTER);
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setBounds(0,0 , 1800 , 325);
        topPanel.add(banner) ;



        JPanel inputPanel = new JPanel() ;
        inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER , 10 ,50));

        inputPanel.setBounds(690,310, 550,200);
        inputPanel.setBackground(new Color(0x141414));


        JLabel username = new JLabel("Username: ") ;
        // Labels don't need prefered size
        username.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        username.setForeground(Color.yellow);

        JTextField usernameFeild = new JTextField() ;
        usernameFeild.setPreferredSize(new Dimension(300 ,30));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));

        inputPanel.add(username);
        inputPanel.add(usernameFeild) ;

        JLabel phone = new JLabel("   Phone: ") ; // Labels don't need prefered size
        phone.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        phone.setForeground(Color.yellow);

        JTextField phoneFeild = new JTextField() ;
        phoneFeild.setPreferredSize(new Dimension(300 ,30));
        phoneFeild.setBorder(BorderFactory.createEmptyBorder());
        phoneFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(phone);
        inputPanel.add(phoneFeild) ;
        topPanel.add(inputPanel);



        //Buttons
        JPanel controlPanel = new JPanel() ;
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 50 ,10));
        controlPanel.setBounds(650,550, 700,250);
        controlPanel.setBackground(new Color(0x141414));

        JButton addButton = new JButton("Add Contact");
        addButton.setPreferredSize(new Dimension(170, 55));
        addButton.setBackground(Color.magenta) ;
        addButton.setBorder(BorderFactory.createEtchedBorder());
        addButton.setFocusable(false);
        addButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        addButton.setForeground(Color.black);
        addButton.setOpaque(true);


        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(170, 55));
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);



        // instantiate HomePageGUI to use its methods
        // HomePage homePage = new HomePage(client);

        controlPanel.add(addButton) ;
        controlPanel.add(backButton) ;
        topPanel.add(controlPanel) ;

        frame.add(topPanel) ;

        frame.setVisible(true);

        // action listener for submit button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // send server code & the user inputs
                    output.println("6");
                    String name = usernameFeild.getText();
                    String phoneNumber = phoneFeild.getText();
                    output.println(name);
                    output.println(phoneNumber);
                    // get server response & show msg dialog
                    String serverResponse = bfr.readLine();
                    JOptionPane.showMessageDialog(frame, serverResponse);
                    // return home
                    frame.dispose();
                    HomePage homePage = new HomePage(client);
                    homePage.showHomePage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error adding contact. Check that user exists.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });

        // add panel to frame

        frame.setVisible(true);
    }



    // --------------------------------------
    // remove contact (server code 10)
    public void removeContact() {
        JFrame frame = new JFrame(" Remove Contact") ;
        frame.setLayout(new FlowLayout());
        frame.setLocation(400 , 0);
        frame.setSize(new Dimension(700 , 960));
        frame.getContentPane().setBackground(new Color(0x141414));



        JPanel topPanel = new JPanel() ;
        topPanel.setPreferredSize(new Dimension(2000 , 1000));
        topPanel.setBackground(new Color(0x141414));
        topPanel.setLayout(null);

        JLabel banner = new JLabel("          Remove Contact") ;
        banner.setFont(new Font("Monospaced", Font.BOLD, 30));
        banner.setForeground(new Color(0x6eaa6b));
        banner.setOpaque(true);
        ImageIcon front = new ImageIcon("img_1.png") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 325 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        banner.setIcon(frontIcon);
        banner.setHorizontalTextPosition(JLabel.CENTER);
        banner.setVerticalTextPosition(JLabel.TOP);
        banner.setIconTextGap(-200);

        banner.setVerticalAlignment(JLabel.CENTER);
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setBounds(0,0 , 1800 , 325);
        topPanel.add(banner) ;



        JPanel inputPanel = new JPanel() ;
        inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER , 10 ,50));

        inputPanel.setBounds(690,310, 550,200);
        inputPanel.setBackground(new Color(0x141414));




        JLabel phone = new JLabel("   Phone: ") ; // Labels don't need prefered size
        phone.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        phone.setForeground(Color.yellow);

        JTextField phoneFeild = new JTextField() ;
        phoneFeild.setPreferredSize(new Dimension(300 ,30));
        phoneFeild.setBorder(BorderFactory.createEmptyBorder());
        phoneFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(phone);
        inputPanel.add(phoneFeild) ;
        topPanel.add(inputPanel);



        //Buttons
        JPanel controlPanel = new JPanel() ;
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 50 ,10));
        controlPanel.setBounds(650,470, 700,250);
        controlPanel.setBackground(new Color(0x141414));

        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(150, 50));
        removeButton.setBackground(Color.magenta) ;
        removeButton.setBorder(BorderFactory.createEtchedBorder());
        removeButton.setFocusable(false);
        removeButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        removeButton.setForeground(Color.black);
        removeButton.setOpaque(true);


        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);



        // instantiate HomePageGUI to use its methods
        // HomePage homePage = new HomePage(client);

        controlPanel.add(removeButton) ;
        controlPanel.add(backButton) ;
        topPanel.add(controlPanel) ;

        frame.add(topPanel) ;

        frame.setVisible(true);


        // action listener
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // send server code & inputs to server
                    output.println("10");
                    String phoneNumber = phoneFeild.getText().trim();
                    output.println(phoneNumber);
                    // display server response
                    String serverResponse = bfr.readLine();
                    JOptionPane.showMessageDialog(frame, serverResponse, "Server Response", JOptionPane.INFORMATION_MESSAGE);
                    // close frame
                    frame.dispose();
                    HomePage homePage = new HomePage(client);
                    homePage.showHomePage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error removing contact. Make sure you entered the right phone number and the person is in your contacts.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });

        // add panel to frame

        frame.setVisible(true);
    }



    // --------------------------------------
    // view block list (server code 9)
    public void viewBlockList() {
        // create frame & panel
        JFrame frame = new JFrame("The people I hate.");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel panel = new JPanel() ;

        ImageIcon front = new ImageIcon("frontBg.jpg") ;
        Image frontImage = front.getImage().getScaledInstance(1750 , 1200 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        // labels & buttons
        JLabel label = new JLabel("MY BLOCKLIST");
        label.setLayout(null);

        label.setBackground(new Color(0x053e71));
        label.setFont(new Font("Monospaced", Font.PLAIN, 25));
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBounds(0, 0, 700, 900); // Adjusted position and size
        label.setIcon(frontIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-300) ;

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        frame.getContentPane().add(label);
        label.setLayout(null); // Ensure absolute positioning


        // server code
        output.println("5");
        // create JList for list of contacts
        try {
            String[] contacts = bfr.readLine().split(";");
            JList<String> contactList = new JList<>(contacts);
            contactList.setFont(new Font("Monospaced" , Font.PLAIN , 20));
            contactList.setForeground(Color.white);
            contactList.setBackground(new Color(0 , 0, 0 ,0 ));

            // make scrollable
            JScrollPane scrollPane = new JScrollPane(contactList);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setOpaque(false);
            scrollPane.setBounds(150 , 240 , 500 , 700);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            frame.add(scrollPane , JLabel.CENTER) ;
        } catch (IOException e) {
            JLabel error = new JLabel("Error reading block list.");
            error.setBounds(400 ,400 , 200 , 200);
            frame.add(error);
        }

        // back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(270 , 565 , 150 ,50);
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });
        panel.add(backButton, BorderLayout.SOUTH);
        frame.add(backButton , JLabel.CENTER) ;

        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // block contact (server code 7)
    public void blockContact() {
        // create frame & panel
        // create a frame
        JFrame frame = new JFrame("Block Contact") ;
        frame.setLayout(new FlowLayout());
        frame.setLocation(400 , 0);
        frame.setSize(new Dimension(700 , 960));
        frame.getContentPane().setBackground(new Color(0x141414));



        JPanel topPanel = new JPanel() ;
        topPanel.setPreferredSize(new Dimension(2000 , 1000));
        topPanel.setBackground(new Color(0x141414));
        topPanel.setLayout(null);

        JLabel banner = new JLabel("         Block Contact") ;
        banner.setFont(new Font("Monospaced", Font.BOLD, 40));
        banner.setForeground(new Color(0x6eaa6b));
        banner.setOpaque(true);
        ImageIcon front = new ImageIcon("img_1.png") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 325 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        banner.setIcon(frontIcon);
        banner.setHorizontalTextPosition(JLabel.CENTER);
        banner.setVerticalTextPosition(JLabel.TOP);
        banner.setIconTextGap(-200);

        banner.setVerticalAlignment(JLabel.CENTER);
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setBounds(0,0 , 1800 , 325);
        topPanel.add(banner) ;



        JPanel inputPanel = new JPanel() ;
        inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER , 10 ,50));

        inputPanel.setBounds(710,310, 550,200);
        inputPanel.setBackground(new Color(0x141414));
        inputPanel.setOpaque(false);


        JLabel username = new JLabel("Username: ") ;
        // Labels don't need prefered size
        username.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        username.setForeground(Color.yellow);

        JTextField usernameFeild = new JTextField() ;
        usernameFeild.setPreferredSize(new Dimension(300 ,30));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));

        inputPanel.add(username);
        inputPanel.add(usernameFeild) ;
        topPanel.add(inputPanel , JLabel.CENTER) ;




        //Buttons
        JPanel controlPanel = new JPanel() ;
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 50 ,10));
        controlPanel.setBounds(650,480, 700,250);
        controlPanel.setBackground(new Color(0x141414));

        JButton blockButton = new JButton("Block");
        blockButton.setPreferredSize(new Dimension(170, 55));
        blockButton.setBackground(Color.magenta) ;
        blockButton.setBorder(BorderFactory.createEtchedBorder());
        blockButton.setFocusable(false);
        blockButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        blockButton.setForeground(Color.black);
        blockButton.setOpaque(true);


        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(170, 55));
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);



        // instantiate HomePageGUI to use its methods
        // HomePage homePage = new HomePage(client);

        controlPanel.add(blockButton) ;
        controlPanel.add(backButton) ;
        topPanel.add(controlPanel) ;

        frame.add(topPanel) ;

        frame.setVisible(true);
        
        // action listeners
        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameFeild.getText().trim();
                if (!username.isEmpty()) {
                    try {
                        // send server code & user inputs
                        output.println("7");
                        output.println(username);
                        // get server response then show success/fail
                        String serverResponse = bfr.readLine();
                        JOptionPane.showMessageDialog(frame, serverResponse);
                        // return home
                        frame.dispose();
                        HomePage homePage = new HomePage(client);
                        homePage.showHomePage();
                    } catch(IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error communicating with server.");
                    }
                } else {
                    // else username is empty
                    JOptionPane.showMessageDialog(frame, "Username cannot be empty. Please try again.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });

        // make frame visible
        frame.setVisible(true);
    }



    // --------------------------------------
    // unblock contact (server code 8)
    public void unblockContact() {
        // create frame & panel
        // create a frame
        JFrame frame = new JFrame("Unblock Contact") ;
        frame.setLayout(new FlowLayout());
        frame.setLocation(400 , 0);
        frame.setSize(new Dimension(700 , 960));
        frame.getContentPane().setBackground(new Color(0x141414));



        JPanel topPanel = new JPanel() ;
        topPanel.setPreferredSize(new Dimension(2000 , 1000));
        topPanel.setBackground(new Color(0x141414));
        topPanel.setLayout(null);

        JLabel banner = new JLabel("        Unblock Contact") ;
        banner.setFont(new Font("Monospaced", Font.BOLD, 40));
        banner.setForeground(new Color(0x6eaa6b));
        banner.setOpaque(true);
        ImageIcon front = new ImageIcon("img_1.png") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 325 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        banner.setIcon(frontIcon);
        banner.setHorizontalTextPosition(JLabel.CENTER);
        banner.setVerticalTextPosition(JLabel.TOP);
        banner.setIconTextGap(-200);

        banner.setVerticalAlignment(JLabel.CENTER);
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setBounds(0,0 , 1800 , 325);
        topPanel.add(banner) ;



        JPanel inputPanel = new JPanel() ;
        inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER , 10 ,50));

        inputPanel.setBounds(710,310, 550,200);
        inputPanel.setBackground(new Color(0x141414));
        inputPanel.setOpaque(false);


        JLabel username = new JLabel("Username: ") ;
        // Labels don't need prefered size
        username.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        username.setForeground(Color.yellow);

        JTextField usernameFeild = new JTextField() ;
        usernameFeild.setPreferredSize(new Dimension(300 ,30));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));

        inputPanel.add(username);
        inputPanel.add(usernameFeild) ;
        topPanel.add(inputPanel , JLabel.CENTER) ;




        //Buttons
        JPanel controlPanel = new JPanel() ;
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 50 ,10));
        controlPanel.setBounds(650,480, 700,250);
        controlPanel.setBackground(new Color(0x141414));

        JButton unblockButton = new JButton("Unblock");
        unblockButton.setPreferredSize(new Dimension(170, 55));
        unblockButton.setBackground(Color.magenta) ;
        unblockButton.setBorder(BorderFactory.createEtchedBorder());
        unblockButton.setFocusable(false);
        unblockButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        unblockButton.setForeground(Color.black);
        unblockButton.setOpaque(true);


        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(170, 55));
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);



        // instantiate HomePageGUI to use its methods
        // HomePage homePage = new HomePage(client);

        controlPanel.add(unblockButton) ;
        controlPanel.add(backButton) ;
        topPanel.add(controlPanel) ;

        frame.add(topPanel) ;

        frame.setVisible(true);

        // action listeners
        unblockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameFeild.getText().trim();
                if (!username.isEmpty()) {
                    try {
                        // send server code & user input
                        output.println("8");
                        output.println(username);
                        // get server response & show success/fail
                        String serverResponse = bfr.readLine();
                        JOptionPane.showMessageDialog(frame, serverResponse);
                        // return home
                        frame.dispose();
                        HomePage homePage = new HomePage(client);
                        homePage.showHomePage();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error communicating with server.");
                    }
                } else {
                    // else username input is empty
                    JOptionPane.showMessageDialog(frame, "Username cannot be empty. Please try again.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });

        // set frame visible
        frame.setVisible(true);
    }



    // --------------------------------------
    // start conversation (server code 11)
    public void startConversation() {

        // create frame & panel
        JFrame frame = new JFrame(" Remove Contact") ;
        frame.setLayout(new FlowLayout());
        frame.setLocation(400 , 0);
        frame.setSize(new Dimension(700 , 960));
        frame.getContentPane().setBackground(Color.white);



        JPanel topPanel = new JPanel() ;
        topPanel.setPreferredSize(new Dimension(2000 , 1000));
        topPanel.setBackground(new Color(0x141414));
        topPanel.setLayout(null);

        JLabel banner = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Start Your New<br>&nbsp;&nbsp;&nbsp;&nbsp;" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cosmic Conversation!</html>") ;
        banner.setFont(new Font("Monospaced", Font.PLAIN, 30));
        banner.setForeground(Color.white);
        banner.setOpaque(true);
        ImageIcon front = new ImageIcon("img_2.png") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 990 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        banner.setIcon(frontIcon);
        banner.setHorizontalTextPosition(JLabel.CENTER);
        banner.setVerticalTextPosition(JLabel.TOP);
        banner.setIconTextGap(-200);

        banner.setVerticalAlignment(JLabel.CENTER);
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setBounds(0,0 , 1800 , 990);
        topPanel.add(banner) ;



        JPanel inputPanel = new JPanel() ;
        inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER , 10 ,50));

        inputPanel.setBounds(800,280, 400,250);
        inputPanel.setBackground(Color.white);
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEtchedBorder());




        JLabel username = new JLabel("Username: ") ; // Labels don't need prefered size
        username.setFont(new Font("Monospaced" , Font.PLAIN , 30));
        username.setForeground(Color.white);
        username.setBackground(Color.white);
        // username.setBorder(BorderFactory.createEtchedBorder());
        username.setOpaque(false);

        JTextField usernameFeild = new JTextField() ;
        usernameFeild.setPreferredSize(new Dimension(300 ,40));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(username);
        inputPanel.add(usernameFeild) ;
        topPanel.add(inputPanel , JLabel.CENTER);



        //Buttons
        JPanel controlPanel = new JPanel() ;
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 50 ,10));
        controlPanel.setBounds(650,550, 700,250);
        controlPanel.setBackground(new Color(0x141414));
        controlPanel.setOpaque(false);

        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(150, 50));
        startButton.setBackground(Color.white) ;
        startButton.setBorder(BorderFactory.createEtchedBorder());
        startButton.setFocusable(false);
        startButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        startButton.setForeground(Color.white);
        startButton.setOpaque(false);


        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setBackground(Color.white) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.white);
        backButton.setOpaque(false);



        // instantiate HomePageGUI to use its methods
        // HomePage homePage = new HomePage(client);

        controlPanel.add(startButton) ;
        controlPanel.add(backButton) ;
        topPanel.add(controlPanel , JLabel.CENTER) ;

        frame.add(topPanel) ;

        frame.setVisible(true);
        // action listeners
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usernameFeild.getText().trim();
                if (!user.isEmpty()) {
                    try {
                        // send server code & user input
                        output.println("11");
                        output.println(user);
                        // get server response & show success/fail
                        String serverResponse = bfr.readLine();
                        JOptionPane.showMessageDialog(frame, serverResponse);
                        // return home
                        frame.dispose();
                        HomePage homePage = new HomePage(client);
                        homePage.showHomePage();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error communicating with server.");
                    }
                } else {
                    // else username input is empty
                    JOptionPane.showMessageDialog(frame, "Username cannot be empty. Please try again.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });

        // set frame visible
        frame.setVisible(true);
    }



    // --------------------------------------
    // delete conversation (server code 12)
    public void deleteConversation() {
        // create frame & panel
        JFrame frame = new JFrame(" Remove Contact") ;
        frame.setLayout(new FlowLayout());
        frame.setLocation(400 , 0);
        frame.setSize(new Dimension(700 , 960));
        frame.getContentPane().setBackground(Color.white);



        JPanel topPanel = new JPanel() ;
        topPanel.setPreferredSize(new Dimension(2000 , 1000));
        topPanel.setBackground(new Color(0x141414));
        topPanel.setLayout(null);

        JLabel banner = new JLabel("       Delete Conversation") ;
        banner.setFont(new Font("Monospaced", Font.PLAIN, 30));
        banner.setForeground(Color.white);
        banner.setOpaque(true);
        ImageIcon front = new ImageIcon("img_2.png") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 990 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        banner.setIcon(frontIcon);
        banner.setHorizontalTextPosition(JLabel.CENTER);
        banner.setVerticalTextPosition(JLabel.TOP);
        banner.setIconTextGap(-200);

        banner.setVerticalAlignment(JLabel.CENTER);
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setBounds(0,0 , 1800 , 990);
        topPanel.add(banner) ;



        JPanel inputPanel = new JPanel() ;
        inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER , 10 ,50));

        inputPanel.setBounds(800,280, 400,250);
        inputPanel.setBackground(Color.white);
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEtchedBorder());




        JLabel username = new JLabel("Username: ") ; // Labels don't need prefered size
        username.setFont(new Font("Monospaced" , Font.PLAIN , 30));
        username.setForeground(Color.white);
        username.setBackground(Color.white);
        // username.setBorder(BorderFactory.createEtchedBorder());
        username.setOpaque(false);

        JTextField usernameFeild = new JTextField() ;
        usernameFeild.setPreferredSize(new Dimension(300 ,40));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(username);
        inputPanel.add(usernameFeild) ;
        topPanel.add(inputPanel , JLabel.CENTER);



        //Buttons
        JPanel controlPanel = new JPanel() ;
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 50 ,10));
        controlPanel.setBounds(650,550, 700,250);
        controlPanel.setBackground(new Color(0x141414));
        controlPanel.setOpaque(false);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(150, 50));
        deleteButton.setBackground(Color.white) ;
        deleteButton.setBorder(BorderFactory.createEtchedBorder());
        deleteButton.setFocusable(false);
        deleteButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        deleteButton.setForeground(Color.white);
        deleteButton.setOpaque(false);


        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setBackground(Color.white) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.white);
        backButton.setOpaque(false);



        // instantiate HomePageGUI to use its methods
        // HomePage homePage = new HomePage(client);

        controlPanel.add(deleteButton) ;
        controlPanel.add(backButton) ;
        topPanel.add(controlPanel , JLabel.CENTER) ;

        frame.add(topPanel) ;

        frame.setVisible(true);
        // action listeners
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usernameFeild.getText().trim();
                if (!user.isEmpty()) {
                    try {
                        // send server code & user input
                        output.println("12");
                        output.println(user);
                        // get server response & show success/fail
                        String serverResponse = bfr.readLine();
                        JOptionPane.showMessageDialog(frame, serverResponse);
                        // return home
                        frame.dispose();
                        HomePage homePage = new HomePage(client);
                        homePage.showHomePage();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error communicating with server.");
                    }
                } else {
                    // else username input is empty
                    JOptionPane.showMessageDialog(frame, "Username cannot be empty. Please try again.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });

        // set frame visible
        frame.setVisible(true);
    }



    // --------------------------------------
    // get all messages with user (server code 13)
    public void getMessages() {
        JFrame frame = new JFrame("Chat History");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);


        ImageIcon front = new ImageIcon("frontBg.jpg");
        Image frontImage = front.getImage().getScaledInstance(1750, 1200, Image.SCALE_SMOOTH);
        ImageIcon frontIcon = new ImageIcon(frontImage);
        // labels & buttons
        JLabel label = new JLabel("CHAT HISTORY");
        label.setLayout(null);

        label.setBackground(new Color(0x053e71));
        label.setFont(new Font("Monospaced", Font.PLAIN, 25));
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBounds(0, 0, 700, 900); // Adjusted position and size
        label.setIcon(frontIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-300);

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        frame.getContentPane().add(label);
        label.setLayout(null); // Ensure absolute positioning

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBounds(75, 170, 550, 40);
        inputPanel.setOpaque(false);
        // components for enter username
        JLabel username = new JLabel("Username: ");
        // Labels don't need prefered size
        username.setFont(new Font("Monospaced", Font.PLAIN, 20));
        username.setForeground(Color.yellow);

        JTextField usernameFeild = new JTextField();
        usernameFeild.setPreferredSize(new Dimension(300, 25));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced", Font.BOLD, 20));

        inputPanel.add(username);
        inputPanel.add(usernameFeild);
        frame.add(inputPanel , JLabel.CENTER);


        // panel & components for view/back buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setOpaque(false);
        JButton viewButton = new JButton("View Messages");
        viewButton.setBackground(Color.yellow) ;
        viewButton.setBorder(BorderFactory.createEtchedBorder());
        viewButton.setFocusable(false);
        viewButton.setFont(new Font("Monospaced" , Font.BOLD , 15));
        viewButton.setForeground(Color.black);
        viewButton.setOpaque(true);
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 15));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);
        buttonPanel.setBounds(300, 635, 300, 50);
        buttonPanel.add(viewButton);
        buttonPanel.add(backButton);
        frame.add(buttonPanel , JLabel.CENTER) ;
        // create JList to display messages
        String[] messages =  {""} ;
        JList<String> messageList = new JList<>(messages);
        messageList.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        messageList.setForeground(Color.white);
        messageList.setBackground(new Color(0x141414));

        // make scrollable
        JScrollPane scrollPane = new JScrollPane(messageList);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBounds(75 , 230 , 540 , 380);
        scrollPane.setBackground(new Color(0x141414));
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        frame.add(scrollPane , JLabel.CENTER) ;

        // add components to main frame
        //panel.add(inputPanel, BorderLayout.NORTH);
        // panel.add(scrollPane, BorderLayout.CENTER);
        //panel.add(buttonPanel, BorderLayout.SOUTH);

        // action listeners
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messenger = (String) usernameFeild.getText().trim();
                if (!messenger.isEmpty()) {
                    try {
                        // send server code & user input
                        output.println("13");
                        output.println(messenger);
                        // create array of messages from server response
                        String[] messagesArray = bfr.readLine().split(",");
                        // put messages in messageList JList
                        if (messagesArray.length == 1 && messagesArray[0].isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "No messages yet.");
                        } else {
                            messageList.setListData(messagesArray);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error retrieving messages. Check that you have added the user as a contact and started a conversation with them.");
                    }
                } else {
                    // else input is empty
                    JOptionPane.showMessageDialog(frame, "Please enter a contact's username instead of leaving it empty.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });



        // set frame visible

        frame.setVisible(true);
    }



    // --------------------------------------
    // send message (server code 14)
    public void sendMessage() {
        JFrame frame = new JFrame("Chat History");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);


        ImageIcon front = new ImageIcon("frontBg.jpg");
        Image frontImage = front.getImage().getScaledInstance(1750, 1200, Image.SCALE_SMOOTH);
        ImageIcon frontIcon = new ImageIcon(frontImage);
        // labels & buttons
        JLabel label = new JLabel("SEND MESSAGE");
        label.setLayout(null);

        label.setBackground(new Color(0x053e71));
        label.setFont(new Font("Monospaced", Font.PLAIN, 25));
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBounds(0, 0, 700, 900); // Adjusted position and size
        label.setIcon(frontIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-285);

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        frame.getContentPane().add(label);
        label.setLayout(null); // Ensure absolute positioning

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBounds(75, 170, 550, 40);
        inputPanel.setOpaque(false);
        // components for enter username
        JLabel username = new JLabel("Username: ");
        // Labels don't need prefered size
        username.setFont(new Font("Monospaced", Font.PLAIN, 20));
        username.setForeground(Color.yellow);

        JTextField usernameFeild = new JTextField();
        usernameFeild.setPreferredSize(new Dimension(300, 25));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced", Font.BOLD, 20));

        inputPanel.add(username);
        inputPanel.add(usernameFeild);
        frame.add(inputPanel , JLabel.CENTER);


        // panel & components for view/back buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setOpaque(false);
        JButton sendButton = new JButton("Send");
        sendButton.setBackground(Color.yellow) ;
        sendButton.setBorder(BorderFactory.createEtchedBorder());
        sendButton.setFocusable(false);
        sendButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        sendButton.setForeground(Color.black);
        sendButton.setOpaque(true);
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);
        buttonPanel.setBounds(300, 560, 300, 50);
        buttonPanel.add(sendButton);
        buttonPanel.add(backButton);
        frame.add(buttonPanel , JLabel.CENTER) ;

        // make scrollable
        JTextArea textArea = new JTextArea("Enter Message (in a single line)");
        textArea.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        textArea.setForeground(Color.white);
        textArea.setOpaque(true);
        textArea.setBounds(75 , 230 , 540 , 300);
        textArea.setBackground(new Color(0x222222));
        textArea.setBorder(BorderFactory.createEtchedBorder());
        frame.add(textArea , JLabel.CENTER) ;

        frame.setVisible(true);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // store user inputs
                String username = usernameFeild.getText();
                String message = textArea.getText();
                try {
                    // send server code & user input to server
                    output.println("14");
                    output.println(username);
                    output.println(message);
                    // get server response & show success/fail
                    String serverResponse = bfr.readLine();
                    JOptionPane.showMessageDialog(frame, serverResponse);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error connecting to server.");
                }
                // close frame & go home
                frame.dispose();
                getMessages();

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });

        frame.setVisible(true);
    }



    // --------------------------------------
    // delete message (server code 15)
    public void deleteMessage() {

        JFrame frame = new JFrame("Delete Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);


        ImageIcon front = new ImageIcon("frontBg.jpg");
        Image frontImage = front.getImage().getScaledInstance(1750, 1200, Image.SCALE_SMOOTH);
        ImageIcon frontIcon = new ImageIcon(frontImage);
        // labels & buttons
        JLabel label = new JLabel("DELETE MESSAGE");
        label.setLayout(null);

        label.setBackground(new Color(0x053e71));
        label.setFont(new Font("Monospaced", Font.PLAIN, 25));
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBounds(0, 0, 700, 900); // Adjusted position and size
        label.setIcon(frontIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-285);

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        frame.getContentPane().add(label);
        label.setLayout(null); // Ensure absolute positioning

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBounds(75, 170, 550, 40);
        inputPanel.setOpaque(false);
        // components for enter username
        JLabel username = new JLabel("Username: ");
        // Labels don't need prefered size
        username.setFont(new Font("Monospaced", Font.PLAIN, 20));
        username.setForeground(Color.yellow);

        JTextField usernameFeild = new JTextField();
        usernameFeild.setPreferredSize(new Dimension(300, 25));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced", Font.BOLD, 20));

        inputPanel.add(username);
        inputPanel.add(usernameFeild);
        frame.add(inputPanel , JLabel.CENTER);


        // panel & components for view/back buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setOpaque(false);
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.yellow) ;
        deleteButton.setBorder(BorderFactory.createEtchedBorder());
        deleteButton.setFocusable(false);
        deleteButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        deleteButton.setForeground(Color.black);
        deleteButton.setOpaque(true);
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);
        buttonPanel.setBounds(300, 560, 300, 50);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        frame.add(buttonPanel , JLabel.CENTER) ;

        // make scrollable
        JTextArea textArea = new JTextArea("Enter Message (in a single line)");
        textArea.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        textArea.setForeground(Color.white);
        textArea.setOpaque(true);
        textArea.setBounds(75 , 230 , 540 , 300);
        textArea.setBackground(new Color(0x222222));
        textArea.setBorder(BorderFactory.createEtchedBorder());
        frame.add(textArea , JLabel.CENTER) ;

        frame.setVisible(true);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // store user inputs
                String username = usernameFeild.getText();
                String message = textArea.getText();
                try {
                    // send server code & user input to server
                    output.println("15");
                    output.println(username);
                    output.println(message);
                    // get server response & show success/fail
                    String serverResponse = bfr.readLine();
                    JOptionPane.showMessageDialog(frame, serverResponse);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error connecting to server.");
                }
                // close frame & go home
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });

        frame.setVisible(true);
    }


}