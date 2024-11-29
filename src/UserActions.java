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
    // display profile picture (server code 3)
    public void displayProfilePic() throws IOException {
        output.println("3");
        System.out.println("Enter user to get profile picture for: ");
        Scanner scanner = new Scanner(System.in); //TODO delete this line after converting displayProfilePicture to GUI
        String user = scanner.nextLine(); //TODO delete this line after converting displayProfilePicture to GUI
        // instantiate HomePage to use its methods
        HomePage homePage = new HomePage(client);
        homePage.showHomePage();
    }



    // --------------------------------------
    // view contacts (server code 5)
    public void viewContacts() {
        // create frame & panel
        JFrame frame = new JFrame("My Contacts");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // server code
        output.println("5");
        // create JList for list of contacts
        try {
            String[] contacts = bfr.readLine().split(";");
            JList<String> contactList = new JList<>(contacts);
            // make scrollable
            JScrollPane scrollPane = new JScrollPane(contactList);
            panel.add(scrollPane, BorderLayout.CENTER);
        } catch (IOException e) {
            JLabel error = new JLabel("Error reading contacts list.");
            panel.add(error, BorderLayout.CENTER);
        }

        // back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });
        panel.add(backButton, BorderLayout.SOUTH);

        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // add new contact (server code 6)
    public void addNewContact() {
        // create frame & panel
        JFrame frame = new JFrame("Add New Contact");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // create components
        JLabel nameLabel = new JLabel("Contact Name:");
        JTextField nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        JButton submitButton = new JButton("Add Contact");
        JButton backButton = new JButton("Back");
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(submitButton);
        panel.add(backButton);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneLabel.setVerticalAlignment(JLabel.CENTER);

        // action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // send server code & the user inputs
                    output.println("6");
                    String name = nameField.getText();
                    String phoneNumber = phoneField.getText();
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
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // remove contact (server code 10)
    public void removeContact() {
        // create frame & panel
        JFrame frame = new JFrame("Remove Contact");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel(new GridLayout(2, 2));

        // components
        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        JButton removeButton = new JButton("Remove Contact");
        JButton backButton = new JButton("Back");
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(removeButton);
        panel.add(backButton);
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneLabel.setVerticalAlignment(JLabel.CENTER);

        // action listener
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // send server code & inputs to server
                    output.println("10");
                    String phoneNumber = phoneField.getText().trim();
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
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // view block list (server code 9)
    public void viewBlockList() {
        // create frame & panel
        JFrame frame = new JFrame("Block List");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel(new BorderLayout());

        // server code
        output.println("9");
        // get block list
        try {
            // get array of blocked contacts & put in JList
            String[] blockedContacts = bfr.readLine().split(";");
            JList<String> blockList = new JList<>(blockedContacts);
            // scrollable
            JScrollPane scrollPane = new JScrollPane(blockList);
            panel.add(scrollPane, BorderLayout.CENTER);
        } catch (IOException e) {
            JLabel error = new JLabel("Error reading block list.");
            panel.add(error, BorderLayout.CENTER);
        }

        // back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });
        panel.add(backButton, BorderLayout.SOUTH);

        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // block contact (server code 7)
    public void blockContact() {
        // create frame & panel
        JFrame frame = new JFrame("Block Contact");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        // components
        JLabel usernameLabel = new JLabel("Enter username to block:");
        JTextField usernameField = new JTextField();
        JButton blockButton = new JButton("Block User");
        JButton backButton = new JButton("Back");
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(blockButton);
        panel.add(backButton);
        frame.add(panel);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setVerticalAlignment(JLabel.CENTER);

        // action listeners
        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
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
        JFrame frame = new JFrame("Unblock Contact");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        // components
        JLabel usernameLabel = new JLabel("Enter username to unblock:");
        JTextField usernameField = new JTextField();
        JButton unblockButton = new JButton("Unblock");
        JButton backButton = new JButton("Back");
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(unblockButton);
        panel.add(backButton);
        frame.add(panel);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setVerticalAlignment(JLabel.CENTER);

        // action listeners
        unblockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
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
        JFrame frame = new JFrame("Start Conversation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        // components
        JLabel userLabel = new JLabel("<html>Enter username to start<br>a conversation with:</html>");
        userLabel.setHorizontalAlignment(JLabel.CENTER);
        userLabel.setVerticalAlignment(JLabel.CENTER);
        JTextField userField = new JTextField();
        JButton startButton = new JButton("Start Conversation");
        JButton backButton = new JButton("Back");
        panel.add(userLabel);
        panel.add(userField);
        panel.add(startButton);
        panel.add(backButton);
        frame.add(panel);

        // action listeners
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText().trim();
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
        JFrame frame = new JFrame("Delete Conversation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        // components
        JLabel userLabel = new JLabel("<html>Enter username to delete<br>a conversation with:</html>");
        userLabel.setHorizontalAlignment(JLabel.CENTER);
        userLabel.setVerticalAlignment(JLabel.CENTER);
        JTextField userField = new JTextField();
        JButton deleteButton = new JButton("Delete Conversation");
        JButton backButton = new JButton("Back");
        panel.add(userLabel);
        panel.add(userField);
        panel.add(deleteButton);
        panel.add(backButton);
        frame.add(panel);

        // action listeners
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText().trim();
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
        // create frame
        JFrame frame = new JFrame("View Messages");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        // create panels
        JPanel panel = new JPanel(new BorderLayout());

        // panel & components for input field (entering contact name)
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JLabel userLabel = new JLabel("Enter contact's username:");
        JTextField userField = new JTextField();
        inputPanel.add(userLabel);
        inputPanel.add(userField);
        // panel & components for view/back buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton viewButton = new JButton("View Messages");
        JButton backButton = new JButton("Back");
        buttonPanel.add(viewButton);
        buttonPanel.add(backButton);
        // create JList to display messages
        JList<String> messageList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(messageList);

        // add components to main frame
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // action listeners
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messenger = userField.getText().trim();
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
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // send message (server code 14)
    public void sendMessage() {
        // create frame & main panel
        JFrame frame = new JFrame("Send Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // input panel for username/message input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 5, 5));
        // components for enter username
        JLabel usernameLabel = new JLabel("<html>Enter username to<br>send a message to:</html>");
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        JTextField usernameField = new JTextField();
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        // components for enter message
        JLabel messageLabel = new JLabel("Enter your message:");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        JTextField messageField = new JTextField();
        inputPanel.add(messageLabel);
        inputPanel.add(messageField);
        // add input panel to main panel
        panel.add(inputPanel, BorderLayout.CENTER);

        // bottom panel
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        // send message button & action listener
        JButton sendButton = new JButton("Send Message");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // store user inputs
                String username = usernameField.getText();
                String message = messageField.getText();
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
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });
        bottomPanel.add(sendButton);

        // back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });
        bottomPanel.add(backButton);

        // add panel to frame
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // delete message (server code 15)
    public void deleteMessage() {
        // create frame & main panel
        JFrame frame = new JFrame("Delete Message");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // input panel for username/message input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 5, 5));
        // components for enter username
        JLabel usernameLabel = new JLabel("<html>Enter username to delete<br>a a message from:</html>");
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        JTextField usernameField = new JTextField();
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        // components for enter message
        JLabel messageLabel = new JLabel("Enter your message to delete:");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        JTextField messageField = new JTextField();
        inputPanel.add(messageLabel);
        inputPanel.add(messageField);
        // add input panel to main panel
        panel.add(inputPanel, BorderLayout.CENTER);

        // bottom panel
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        // send message button & action listener
        JButton deleteButton = new JButton("Delete Message");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // store user inputs
                String username = usernameField.getText();
                String message = messageField.getText();
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
        bottomPanel.add(deleteButton);

        // back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage = new HomePage(client);
                homePage.showHomePage();
            }
        });
        bottomPanel.add(backButton);

        // add panel to frame
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
    }


}
