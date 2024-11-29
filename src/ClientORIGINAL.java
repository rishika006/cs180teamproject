import java.io.*;
import java.net.Socket;
import java.util.Scanner; // TODO delete after fixing displayProfilePicture
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// HOST & PORT: localhost 5252


public class ClientORIGINAL {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader bfr;

    private Scanner scanner; // TODO delete after fixing displayProfilePicture



    // --------------------------------------
    // constructor
    public ClientORIGINAL(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        output = new PrintWriter(socket.getOutputStream(), true);
        bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        scanner = new Scanner(System.in); // TODO delete after fixing displayProfilePicture
        start();
    }


    // --------------------------------------
    // main page (login, sign up)
    private void start() {
        // create JFrame
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        // panels & layout
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // labels & buttons
        JLabel label = new JLabel("Welcome to the Messaging App!", SwingConstants.CENTER);
        JButton signUpButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Log In");
        JButton closeButton = new JButton("Close App");

        // action listeners for buttons
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                signUp();
            }
        });
        loginButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                login();
            }
        }));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // add components to panel
        panel.add(label);
        panel.add(signUpButton);
        panel.add(loginButton);
        panel.add(closeButton);
        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // helper method for login details (code 2)
    private void login() {
        // create frame
        JFrame frame = new JFrame("Log In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        // panels & layout
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // create fields/buttons
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Log In");
        JButton backButton = new JButton("Back");

        // action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    output.println("2");
                    output.println(usernameField.getText());
                    output.println(new String(passwordField.getPassword()));
                    // server response
                    String serverResponse = bfr.readLine();
                    if (serverResponse.equals("true")) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                        frame.dispose();
                        showHomePage();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Login failed. Please check that username and password are correct.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                showHomePage();
            }
        });

        // add components to panel
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(backButton);
        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // helper method for signup details (code 1)
    private void signUp() {
        // create frame
        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        // create panel
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // text fields & buttons
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();
        JButton submitButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        // action listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    output.println("1");
                    output.println(firstNameField.getText());
                    output.println(lastNameField.getText());
                    output.println(phoneField.getText());
                    output.println(emailField.getText());
                    output.println(usernameField.getText());
                    output.println(new String(passwordField.getPassword()));
                    output.println(new String(confirmPasswordField.getPassword()));
                    // server response
                    String serverResponse = bfr.readLine();
                    if (serverResponse.equals("true")) {
                        JOptionPane.showMessageDialog(frame, "Signup successful!");
                        frame.dispose();
                        start();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Sign-up failed. Phone number or username may already have an account, or else make sure your password matches your re-typed password.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                start();
            }
        });

        // add components to panel
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPasswordField);
        panel.add(submitButton);
        panel.add(backButton);
        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // home-page for all user choices
    private void showHomePage() {
        // create frame
        JFrame frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        // create panel
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // add components
        // home page
        JLabel label = new JLabel("Home Page", SwingConstants.CENTER);
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
                viewContacts();
            }
        });
        // add new contact
        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                addNewContact();
            }
        });
        // remove contact
        removeContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                removeContact();
            }
        });
        // view block list
        viewBlockListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                viewBlockList();
            }
        });
        // block contact
        blockContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                blockContact();
            }
        });
        // unblock contact
        unblockContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                unblockContact();
            }
        });
        // start conversation
        startConversationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                startConversation();
            }
        });
        // delete conversation
        deleteConversationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                deleteConversation();
            }
        });
        // view messages with contact
        viewMessagesWithContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                getMessages();
            }
        });
        // send message
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                sendMessage();
            }
        });
        // delete message
        deleteMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                deleteMessage();
            }
        });
        // log out
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Logged out successfully.");
                frame.dispose();
                start();
            }
        });

        // add components to panel
        panel.add(label);
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
        frame.setVisible(true);
    }



    // --------------------------------------
    // TODO: idk format for displaying image -- pls do this
    // display profile picture (code 3)
    public void displayProfilePic() throws IOException {
        output.println("3");
        System.out.println("Enter user to get profile picture for: ");
        String user = scanner.nextLine();
        showHomePage();
    }



    // --------------------------------------
    // view contacts (code 5)
    private void viewContacts() {
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
                showHomePage();
            }
        });
        panel.add(backButton, BorderLayout.SOUTH);

        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // add new contact (code 6)
    private void addNewContact() {
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
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(submitButton);

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
                    showHomePage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error adding contact. Check that user exists.");
                }
            }
        });

        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // remove contact (code 10)
    private void removeContact() {
        // create frame & panel
        JFrame frame = new JFrame("Remove Contact");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel(new GridLayout(2, 2));

        // components
        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        JButton removeButton = new JButton("Remove Contact");
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(removeButton);

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
                    showHomePage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error removing contact. Make sure you entered the right phone number and the person is in your contacts.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // view block list (code 9)
    private void viewBlockList() {
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
                showHomePage();
            }
        });
        panel.add(backButton, BorderLayout.SOUTH);

        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // block contact (code 7)
    private void blockContact() {
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
                        showHomePage();
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
                showHomePage();
            }
        });

        // make frame visible
        frame.setVisible(true);
    }



    // --------------------------------------
    // unblock contact (code 8)
    private void unblockContact() {
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
                        showHomePage();
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
                showHomePage();
            }
        });

        // set frame visible
        frame.setVisible(true);
    }



    // --------------------------------------
    // start conversation (code 11)
    private void startConversation() {
        // create frame & panel
        JFrame frame = new JFrame("Start Conversation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        // components
        JLabel userLabel = new JLabel("Enter username to start a conversation:");
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
                        showHomePage();
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
                showHomePage();
            }
        });

        // set frame visible
        frame.setVisible(true);
    }



    // --------------------------------------
    // delete conversation (code 12)
    private void deleteConversation() {
        // create frame & panel
        JFrame frame = new JFrame("Start Conversation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        // components
        JLabel userLabel = new JLabel("Enter username to start a conversation:");
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
                        showHomePage();
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
                showHomePage();
            }
        });

        // set frame visible
        frame.setVisible(true);
    }



    // --------------------------------------
    // get all messages with user (code 13)
    private void getMessages() {
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
                showHomePage();
            }
        });

        // set frame visible
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // send message (code 14)
    private void sendMessage() {
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
        JLabel usernameLabel = new JLabel("Enter username to send message to:");
        JTextField usernameField = new JTextField();
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        // components for enter message
        JLabel messageLabel = new JLabel("Enter your message:");
        JTextField messageField = new JTextField();
        inputPanel.add(messageLabel);
        inputPanel.add(messageField);
        // add input panel to main panel
        panel.add(inputPanel, BorderLayout.CENTER);

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
                showHomePage();
            }
        });
        panel.add(sendButton, BorderLayout.SOUTH);

        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // delete message (code 15)
    private void deleteMessage() {
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
        JLabel usernameLabel = new JLabel("Enter username to delete message from:");
        JTextField usernameField = new JTextField();
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        // components for enter message
        JLabel messageLabel = new JLabel("Enter your message to delete:");
        JTextField messageField = new JTextField();
        inputPanel.add(messageLabel);
        inputPanel.add(messageField);
        // add input panel to main panel
        panel.add(inputPanel, BorderLayout.CENTER);

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
                showHomePage();
            }
        });
        panel.add(deleteButton, BorderLayout.SOUTH);

        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }



    // --------------------------------------
    // MAIN METHOD
    public static void main(String[] args) {
        try {
            new Client("localhost", 5252);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to server: " + e.getMessage());
        }
    }

}