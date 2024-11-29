import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Contains GUI elements for the main page of user authentication.
 * Includes the main starting menu with login and signup GUI screens.
 * Also sends/receives information to/from the server.
 *
 * @author Nicholas Chong
 *
 * @version 11/27/2024
 *
 */

public class MainAuthPage {

    private Client client;
    private PrintWriter output;
    private BufferedReader bfr;

    public MainAuthPage(Client client) {
        this.client = client;
        this.output = client.getOutput();
        this.bfr = client.getBfr();

        start();
    }


    // main page (login, sign up)
    public void start() {

        // DEFAULT FONTS for buttons/labels for ALL gui components
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 30)); // JLabels
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 20)); // buttons
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 20)); // text input field
        UIManager.put("PasswordField.font", new Font("Arial", Font.PLAIN, 20)); // password input field
        UIManager.put("List.font", new Font("Arial", Font.PLAIN, 20)); // JList


        // create JFrame
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        // panels & layout
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // labels & buttons
        JLabel label = new JLabel("Welcome to the Messaging App!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
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
                int confirmExit = JOptionPane.showConfirmDialog(frame, "Are you sure you want to close the app?", "Exit", JOptionPane.YES_NO_OPTION);
                if (confirmExit == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
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


    // login details (server code 2)
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

        // instantiate HomePageGUI to use its methods
        HomePage homePage = new HomePage(client);

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
                        homePage.showHomePage();
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
                start();
            }
        });

        // add components to panel
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(backButton);
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setVerticalAlignment(JLabel.CENTER);
        // add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }


    // signup details (server code 1)
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


}
