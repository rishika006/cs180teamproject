import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
        homePage.showHomePage(client.user);
    }
    // TODO: once you fix this method, delete the scanner import at the top


    public void userSearch() {

    }

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
                homePage.showHomePage(client.user);
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
                    homePage.showHomePage(client.user);
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
                homePage.showHomePage(client.user);
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
                    homePage.showHomePage(client.user);
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
                homePage.showHomePage(client.user);
            }
        });

        // add panel to frame
        frame.setVisible(true);

    }



    // --------------------------------------
    // view block list (server code 9)
    public void viewBlockList() {
        JFrame frame = new JFrame("The people I hate.");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); // Absolute positioning

        // Background label
        ImageIcon front = new ImageIcon("frontBg.jpg");
        Image frontImage = front.getImage().getScaledInstance(1750, 1200, Image.SCALE_SMOOTH);
        ImageIcon frontIcon = new ImageIcon(frontImage);
        JLabel label = new JLabel(frontIcon);
        label.setBounds(0, 0, 700, 900);
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label);

        // Title label
        JLabel titleLabel = new JLabel("MY BLOCKLIST", JLabel.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 25));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 100, 700, 50);
        label.add(titleLabel); // Add to label to keep layering consistent

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(270, 800, 150, 50);
        backButton.setBackground(new Color(0x6eaa6b));
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        backButton.setForeground(Color.BLACK);
        label.add(backButton); // Add to label for proper layering

        // Blocklist and Scroll Pane
        try {
            output.println("9");
            String[] blocked = bfr.readLine().split(";");
            System.out.println(Arrays.toString(blocked));

            JList<String> blockList = new JList<>(blocked);
            blockList.setFont(new Font("Monospaced", Font.PLAIN, 20));
            blockList.setForeground(Color.white);
            blockList.setBackground(new Color(0, 0, 0, 0)); // May need tweaking

            JScrollPane scrollPane = new JScrollPane(blockList);
            scrollPane.setBounds(200, 200, 500, 600);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setOpaque(false);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            label.add(scrollPane); // Add to label for proper layering
        } catch (IOException e) {
            JLabel error = new JLabel("Error reading block list.", JLabel.CENTER);
            error.setBounds(100, 400, 500, 50);
            error.setFont(new Font("Monospaced", Font.PLAIN, 20));
            error.setForeground(Color.RED);
            label.add(error); // Add to label for proper layering
        }

        // Back button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage(client.user);
            }
        });

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
                        homePage.showHomePage(client.user);
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
                homePage.showHomePage(client.user);
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
                        homePage.showHomePage(client.user);
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
                homePage.showHomePage(client.user);
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
                        homePage.showHomePage(client.user);
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
                homePage.showHomePage(client.user);
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
                        homePage.showHomePage(client.user);
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
                homePage.showHomePage(client.user);
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
                homePage.showHomePage(client.user);
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
                homePage.showHomePage(client.user);
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
                homePage.showHomePage(client.user);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage(client.user);
            }
        });

        frame.setVisible(true);
    }

    public void searchUser() {
        JFrame frame = new JFrame("Chat History");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);


        ImageIcon front = new ImageIcon("frontBg.jpg");
        Image frontImage = front.getImage().getScaledInstance(1750, 1200, Image.SCALE_SMOOTH);
        ImageIcon frontIcon = new ImageIcon(frontImage);
        // labels & buttons
        JLabel label = new JLabel("SEARCH   ");
        label.setLayout(null);

        label.setBackground(new Color(0x053e71));
        label.setFont(new Font("Monospaced", Font.PLAIN, 25));
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBounds(0, 0, 700, 900); // Adjusted position and size
        label.setIcon(frontIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-270);

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
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.yellow) ;
        searchButton.setBorder(BorderFactory.createEtchedBorder());
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        searchButton.setForeground(Color.black);
        searchButton.setOpaque(true);
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);
        buttonPanel.setBounds(300, 635, 300, 50);
        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);
        frame.add(buttonPanel , JLabel.CENTER) ;



        // create JList to display users
        UserManager userManager = new UserManager() ;
        userManager.loadUsers();
        String[] users  = new String[userManager.ALL_USERS.size()] ;
        // load all users into the users array
        int counter = 0 ;
        for (User user : userManager.ALL_USERS) {
            users[counter++] = "- @" + user.getUsername() ;
        }

        JList<String> usersList = new JList<>(users);
        usersList.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        usersList.setForeground(Color.white);
        usersList.setBackground(new Color(0x141414));

        // make scrollable
        JScrollPane scrollPane = new JScrollPane(usersList);
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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usertoSearch = (String) usernameFeild.getText().trim();
                if (!usertoSearch.isEmpty()) {
                    try {
                        // send server code & user input
                        output.println("16");
                        output.println(usertoSearch);
                        // create array of messages from server response
                        String userInfo = bfr.readLine() ;

                        if (userInfo.equals("User does not exist")) {
                            JOptionPane.showMessageDialog(frame , "User does not exist.");

                        } else {
                            String[] user = userInfo.split(",");
                            String message = String.format("User found!\n        @%s   \n" +
                                    " Would you like to view the user's profile?", user[4]);
                            // Display a dialog to the user
                            int response = JOptionPane.showConfirmDialog(
                                    null,
                                    message,
                                    "User Found.",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE
                            );

                            // Check the user's response
                            if (response == JOptionPane.YES_OPTION) {
                                userInfo(userInfo);
                                bfr.readLine() ;
                            } else if (response == JOptionPane.NO_OPTION) {
                               bfr.readLine();

                            }
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
                homePage.showHomePage(client.user);
            }
        });





        // set frame visible

        frame.setVisible(true);
    }

    public void userInfo(String userInfo) {
        String[] user = userInfo.split(",") ;
        // create frame & panel
        JFrame frame = new JFrame("User Profile");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 900);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel panel = new JPanel() ;

        ImageIcon front = new ImageIcon("frontBg.jpg") ;
        Image frontImage = front.getImage().getScaledInstance(1750 , 1200 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        // labels & buttons
        JLabel label = new JLabel("@"+ user[4] , JLabel.CENTER);
        label.setLayout(null);

        label.setBackground(new Color(0x053e71));
        label.setFont(new Font("Monospaced", Font.PLAIN, 30));
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

        String profile = String.format("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name :  " +
                "%s<br>Contact Info :  %s<br>&nbsp;&nbsp;&nbsp;&nbsp;Email Id :  %s<br></html>" , user[0] + " " + user[1] , user[2] , user[3]  );
        JLabel info = new JLabel() ;
        info.setText(profile);
        info.setBounds(150 ,80 , 600 , 400);
        info.setForeground(Color.white);
        info.setFont(new Font("Monospaced", Font.PLAIN, 20));
        frame.add(info , JLabel.CENTER) ;

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

            }
        });
        frame.add(backButton , JLabel.CENTER);

        // add panel to frame

        frame.setVisible(true);;
    }

}


