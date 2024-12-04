import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Contains GUI elements for the home page with all user action buttons once logged in.
 * The user action buttons are for adding contacts, messaging, etc.
 * Calls methods from UserActionsGUI for server interaction.
 *
 * @author Nicholas Chong
 *
 * @version 11/27/2024
 *
 */

public class HomePage {

    private Client client;
    private PrintWriter output;
    private BufferedReader bfr;

    public HomePage(Client client) {
        this.client = client;
        this.output = client.getOutput();
        this.bfr = client.getBfr();
    }


    // home-page for all user choices
    public void showHomePage() {

        // create JFrame
        JFrame frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);
        frame.getContentPane().setBackground(new Color(0x053e71));
        frame.setLayout(new FlowLayout());


        JPanel basePanel = new JPanel() ;
        basePanel.setLayout(null);
        basePanel.setPreferredSize(new Dimension(2000 , 1000));
        basePanel.setBackground(new Color(0x053e71));

        ImageIcon front = new ImageIcon("img_2.png") ;
        Image frontImage = front.getImage().getScaledInstance(1750 , 990 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;

        // labels & buttons
        JLabel label = new JLabel("Welcome ");
        label.setLayout(null);

        label.setBackground(new Color(0x053e71));
        label.setFont(new Font("Monospaced", Font.PLAIN, 63));
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBounds(100, 50, 400, 50); // Adjusted position and size


        label.setBounds(0, 0, 2000, 1000); // Ensure bounds do not cover header
        label.setIcon(frontIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-200);

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        basePanel.setLayout(null); // Ensure absolute positioning
        basePanel.add(label);      // Add label first

        // Contacts
        JPanel contactsPanel = new JPanel() ;
        contactsPanel.setLayout(new GridLayout(2 , 1 , 0 , 0));
        contactsPanel.setBounds(470 , 250 , 200 , 450 );
        contactsPanel.setOpaque(false);
        JLabel contactsLabel = new JLabel(" Contacts");
        contactsLabel.setForeground(Color.white);
        contactsLabel.setFont(new Font("Monospaced", Font.PLAIN, 30));
        contactsLabel.setBackground(Color.blue);
        contactsLabel.setOpaque(false); // makes label background transparent
        contactsLabel.setBorder(BorderFactory.createEmptyBorder());
        contactsLabel.setPreferredSize(new Dimension(100 , 10));
        contactsPanel.add(contactsLabel) ;
        //buttons
        JPanel buttonPanel = new JPanel() ;
        buttonPanel.setLayout(new GridLayout(3, 1 , 0 , 15));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(200 , 400));
        JButton viewContactsButton = new JButton("View Contacts");
        viewContactsButton.setBackground(Color.white) ;
        viewContactsButton.setBorder(BorderFactory.createEtchedBorder());
        viewContactsButton.setFocusable(false);
        viewContactsButton.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        viewContactsButton.setForeground(Color.white);
        viewContactsButton.setOpaque(false);
        viewContactsButton.setPreferredSize(new Dimension(100 , 50) ) ;
        buttonPanel.add(viewContactsButton) ;
        contactsPanel.add(buttonPanel) ;

        //add contact
        JButton addContactButton = new JButton("Add Contact");
        addContactButton.setBackground(Color.white) ;
        addContactButton.setBorder(BorderFactory.createEtchedBorder());
        addContactButton.setFocusable(false);
        addContactButton.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        addContactButton.setForeground(Color.white);
        addContactButton.setOpaque(false);
        addContactButton.setPreferredSize(new Dimension(100 , 50) ) ;
        buttonPanel.add(addContactButton) ;
        contactsPanel.add(buttonPanel) ;

        //remove contact
        JButton removeContactButton = new JButton("Remove Contact");
        removeContactButton.setBackground(Color.white) ;
        removeContactButton.setBorder(BorderFactory.createEtchedBorder());
        removeContactButton.setFocusable(false);
        removeContactButton.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        removeContactButton.setForeground(Color.white);
        removeContactButton.setOpaque(false);
        removeContactButton.setPreferredSize(new Dimension(100 , 50) ) ;
        buttonPanel.add(removeContactButton) ;
        contactsPanel.add(buttonPanel) ;

        basePanel.add(contactsPanel, JLabel.CENTER) ;
        frame.add(basePanel);
        frame.setVisible(true);

        // Cosmic Conversations
        JPanel convPanel = new JPanel() ;
        convPanel.setLayout(new GridLayout(2 , 1 , 0 , 20));
        convPanel.setBounds(800 , 270 , 400 , 400 );
        convPanel.setOpaque(false);
        JLabel convLabel = new JLabel(" Cosmic Conversations");
        convLabel.setForeground(Color.white);
        convLabel.setFont(new Font("Monospaced", Font.PLAIN, 30));
        convLabel.setOpaque(false); // makes label background transparent
        convLabel.setBorder(BorderFactory.createEmptyBorder());
        convLabel.setPreferredSize(new Dimension(200 , 50));
        convPanel.add(convLabel , JLabel.CENTER) ;

        // Buttons
        JPanel convButtonPanel = new JPanel() ;
        convButtonPanel.setLayout(new GridLayout(2, 1 , 0 , 15));
        convButtonPanel.setOpaque(false);
        convButtonPanel.setPreferredSize(new Dimension(100 , 400));
        // start convo
        JButton startConvButton = new JButton("Start Conversation");
        startConvButton.setBackground(Color.white) ;
        startConvButton.setBorder(BorderFactory.createEtchedBorder());
        startConvButton.setFocusable(false);
        startConvButton.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        startConvButton.setForeground(Color.white);
        startConvButton.setOpaque(false);
        startConvButton.setPreferredSize(new Dimension(100 , 50) ) ;
        convButtonPanel.add(startConvButton) ;
        convPanel.add(convButtonPanel) ;

        // delete convo
        JButton delConvButton = new JButton("Delete Conversation");
        delConvButton.setBackground(Color.white) ;
        delConvButton.setBorder(BorderFactory.createEtchedBorder());
        delConvButton.setFocusable(false);
        delConvButton.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        delConvButton.setForeground(Color.white);
        delConvButton.setOpaque(false);
        delConvButton.setPreferredSize(new Dimension(100 , 50) ) ;
        convButtonPanel.add(delConvButton) ;
        convPanel.add(convButtonPanel) ;

        basePanel.add(convPanel, JLabel.CENTER) ;
        frame.add(basePanel);
        frame.setVisible(true);

        // Messages
        JPanel msgPanel = new JPanel() ;
        msgPanel.setLayout(new GridLayout(2 , 1 , 0 , 0));
        msgPanel.setBounds(1350 , 250 , 200 , 450 );
        msgPanel.setOpaque(false);
        JLabel msgLabel = new JLabel("Messages");
        msgLabel.setForeground(Color.white);
        msgLabel.setFont(new Font("Monospaced", Font.PLAIN, 30));
        msgLabel.setOpaque(false); // makes label background transparent
        msgLabel.setBorder(BorderFactory.createEmptyBorder());
        msgLabel.setPreferredSize(new Dimension(200 , 50));
        msgPanel.add(msgLabel , JLabel.CENTER) ;

        // Buttons
        JPanel msgButtonPanel = new JPanel() ;
        msgButtonPanel.setLayout(new GridLayout(3, 1 , 0 , 15));
        msgButtonPanel.setOpaque(false);
        msgButtonPanel.setPreferredSize(new Dimension(200 , 450));
        //view
        JButton viewMsgsButton = new JButton("View Messages");
        viewMsgsButton.setBackground(Color.white) ;
        viewMsgsButton.setBorder(BorderFactory.createEtchedBorder());
        viewMsgsButton.setFocusable(false);
        viewMsgsButton.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        viewMsgsButton.setForeground(Color.white);
        viewMsgsButton.setOpaque(false);
        viewMsgsButton.setPreferredSize(new Dimension(200 , 50) ) ;
        msgButtonPanel.add(viewMsgsButton) ;

        //add contact
        JButton sendMsgButton = new JButton("Send Message");
        sendMsgButton.setBackground(Color.white) ;
        sendMsgButton.setBorder(BorderFactory.createEtchedBorder());
        sendMsgButton.setFocusable(false);
        sendMsgButton.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        sendMsgButton.setForeground(Color.white);
        sendMsgButton.setOpaque(false);
        sendMsgButton.setPreferredSize(new Dimension(200 , 50) ) ;
        msgButtonPanel.add(sendMsgButton) ;

        //remove contact
        JButton delMsgButton = new JButton("Delete Message");
        delMsgButton.setBackground(Color.white) ;
        delMsgButton.setBorder(BorderFactory.createEtchedBorder());
        delMsgButton.setFocusable(false);
        delMsgButton.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        delMsgButton.setForeground(Color.white);
        delMsgButton.setOpaque(false);
        delMsgButton.setPreferredSize(new Dimension(200 , 50) ) ;
        msgButtonPanel.add(delMsgButton) ;

        msgPanel.add(msgButtonPanel) ;

        basePanel.add(msgPanel, JLabel.CENTER) ;
        frame.add(basePanel);
        frame.setVisible(true);


        //log out
        JButton logOutButton = new JButton("Log Out") ;
        logOutButton.setBackground(Color.white) ;
        logOutButton.setBorder(BorderFactory.createEtchedBorder());
        logOutButton.setFocusable(false);
        logOutButton.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        logOutButton.setForeground(Color.white);
        logOutButton.setOpaque(false);
        logOutButton.setBounds(1600 , 10 , 150 , 50 ) ;
        basePanel.add(logOutButton , JLabel.CENTER) ;

        frame.add(basePanel);
        frame.setVisible(true);


        // instantiate UserActions to use their methods
        UserActions userActions = new UserActions(client);


        /*
        // action listeners
        // display profile pic
        displayProfilePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // displayProfilePic(); TODO: unimplemented rn -- don't know how to
            }
        });
        */

        // view contacts
        viewContactsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.viewContacts();
            }
        });
        // add new contact
        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.addNewContact();
            }
        });
        // remove contact
        removeContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.removeContact();
            }
        });
        /*
        // view block list
        viewBlockListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.viewBlockList();
            }
        });
        // block contact
        blockContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.blockContact();
            }
        });
        // unblock contact
        unblockContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.unblockContact();
            }
        });
        */
        // start conversation
        startConvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.startConversation();
            }
        });
        // delete conversation
        delConvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.deleteConversation();
            }
        });
        // view messages with contact
        viewMsgsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.getMessages();
            }
        });
        // send message
        sendMsgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.sendMessage();
            }
        });
        // delete message
        delMsgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.deleteMessage();
            }
        });

        // log out
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainAuthPage(client);
            }
        });


    }

}