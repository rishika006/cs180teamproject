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

        // instantiate UserActions to use their methods
        UserActions userActions = new UserActions(client);

        // create frame
        JFrame frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setVisible(true);
        // create panel
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // add components
        // home page
        JLabel label = new JLabel("Home Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        JButton displayProfilePictureButton = new JButton("Display Profile Picture");
        // contacts
        JButton viewContactsButton = new JButton("View Contacts");
        JButton addContactButton = new JButton("Add New Contact");
        JButton removeContactButton = new JButton("Remove Contact");
        // block
        JButton viewBlockListButton = new JButton("View Block List");
        JButton blockContactButton = new JButton("Block Contact");
        JButton unblockContactButton = new JButton("Unblock Contact");
        // manage conversation
        JButton startConversationButton = new JButton("Start Conversation");
        JButton deleteConversationButton = new JButton("Delete Conversation");
        // messages
        JButton viewMessagesWithContactButton = new JButton("View Messages With Contact");
        JButton sendMessageButton = new JButton("Send Message");
        JButton deleteMessageButton = new JButton("Delete Message");
        // log out
        JButton logOutButton = new JButton("Log Out");

        // action listeners
        // display profile pic
        displayProfilePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // displayProfilePic(); TODO: unimplemented rn -- don't know how to
            }
        });
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
        // start conversation
        startConversationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.startConversation();
            }
        });
        // delete conversation
        deleteConversationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.deleteConversation();
            }
        });
        // view messages with contact
        viewMessagesWithContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.getMessages();
            }
        });
        // send message
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                userActions.sendMessage();
            }
        });
        // delete message
        deleteMessageButton.addActionListener(new ActionListener() {
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
                JOptionPane.showMessageDialog(frame, "Logged out successfully.");
                frame.dispose();
                new MainAuthPage(client);
            }
        });

        // add components to panel
        panel.add(label);
        panel.add(new JLabel());
        panel.add(displayProfilePictureButton);
        panel.add(viewContactsButton);
        panel.add(addContactButton);
        panel.add(removeContactButton);
        panel.add(viewBlockListButton);
        panel.add(blockContactButton);
        panel.add(unblockContactButton);
        panel.add(startConversationButton);
        panel.add(deleteConversationButton);
        panel.add(viewMessagesWithContactButton);
        panel.add(sendMessageButton);
        panel.add(deleteMessageButton);
        panel.add(logOutButton);
        // add panel to frame
        frame.add(panel);
    }

}
