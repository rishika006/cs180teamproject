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

        /*

        // DEFAULT FONTS for buttons/labels for ALL gui components
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 30)); // JLabels
        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 20)); // buttons
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 20)); // text input field
        UIManager.put("PasswordField.font", new Font("Arial", Font.PLAIN, 20)); // password input field
        UIManager.put("List.font", new Font("Arial", Font.PLAIN, 20)); // JList

         */


        // create JFrame
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(new FlowLayout());


        JPanel basePanel = new JPanel() ;
        basePanel.setLayout(null);
        basePanel.setPreferredSize(new Dimension(2000 , 1000));
        basePanel.setBackground(Color.black);

        ImageIcon front = new ImageIcon("frontBg.jpg") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 990 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;





        // panels & layout
       // JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        //panel.setBounds();


        // labels & buttons
        JLabel label = new JLabel("Welcome to Cosmic Conversations!");
        label.setLayout(null);

        label.setBackground(Color.black);
        label.setFont(new Font("Monospaced", Font.BOLD, 30));
        label.setForeground(new Color(0x6eaa6b));
        label.setOpaque(true);
        label.setBounds(100, 50, 400, 50); // Adjusted position and size


        label.setBounds(0, 0, 2000, 1000); // Ensure bounds do not cover header
        label.setIcon(frontIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(-250);

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        basePanel.setLayout(null); // Ensure absolute positioning
        basePanel.add(label);      // Add label first
        //basePanel.add(header);     // Add header on top
        frame.add(basePanel);



        // Button panel
        JPanel buttonPanel = new JPanel() ;
        buttonPanel.setLayout(new GridLayout(3 , 1 , 10 , 15));
        buttonPanel.setBounds(880 , 300, 200 , 200) ;
        basePanel.add(buttonPanel , JLabel.CENTER) ;
        buttonPanel.setAlignmentX(400);
        buttonPanel.setAlignmentY(400);
        buttonPanel.setBackground(Color.black);


        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.magenta) ;
        signUpButton.setBorder(BorderFactory.createEtchedBorder());
        signUpButton.setFocusable(false);
        signUpButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        signUpButton.setForeground(Color.black);
        signUpButton.setOpaque(true);
        signUpButton.setPreferredSize(new Dimension(200 , 100) );


        JButton loginButton = new JButton("Log In");
        loginButton.setBackground(Color.YELLOW) ;
        loginButton.setBorder(BorderFactory.createEtchedBorder());
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        loginButton.setForeground(Color.black);
        loginButton.setOpaque(true);
        loginButton.setPreferredSize(new Dimension(200 , 100) )  ;


        JButton closeButton = new JButton("Close App");
        closeButton.setBackground(new Color(0x6eaa6b)) ;
        closeButton.setBorder(BorderFactory.createEtchedBorder());
        closeButton.setFocusable(false);
        closeButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        closeButton.setForeground(Color.black);
        closeButton.setOpaque(true);
        closeButton.setPreferredSize(new Dimension(200 , 100) ) ;

        buttonPanel.add(signUpButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(closeButton);



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

        frame.setVisible(true);
    }




    // login details (server code 2)
    private void login() {

        // create a frame
        JFrame frame = new JFrame("Log In") ;
        frame.setLayout(new FlowLayout());
        frame.setSize(new Dimension(2000 , 1000));
        frame.getContentPane().setBackground(new Color(0x141414));



        JPanel topPanel = new JPanel() ;
        topPanel.setPreferredSize(new Dimension(2000 , 1000));
        topPanel.setBackground(new Color(0x141414));
        topPanel.setLayout(null);

        JLabel banner = new JLabel("       Log In") ;
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

        inputPanel.setBounds(730,310, 550,200);
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

        JLabel password = new JLabel("Password: ") ; // Labels don't need prefered size
        password.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        password.setForeground(Color.yellow);

        JTextField passwordFeild = new JTextField() ;
        passwordFeild.setPreferredSize(new Dimension(300 ,30));
        passwordFeild.setBorder(BorderFactory.createEmptyBorder());
        passwordFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(password);
        inputPanel.add(passwordFeild) ;
        topPanel.add(inputPanel);



        //Buttons
        JPanel controlPanel = new JPanel() ;
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 50 ,10));
        controlPanel.setBounds(650,550, 700,250);
        controlPanel.setBackground(new Color(0x141414));

        JButton loginButton = new JButton("Log In");
        loginButton.setPreferredSize(new Dimension(170, 55));
        loginButton.setBackground(Color.magenta) ;
        loginButton.setBorder(BorderFactory.createEtchedBorder());
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        loginButton.setForeground(Color.black);
        loginButton.setOpaque(true);
        

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(170, 55));
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);
        
        

        // instantiate HomePageGUI to use its methods
        HomePage homePage = new HomePage(client);

        controlPanel.add(loginButton) ;
        controlPanel.add(backButton) ;
        topPanel.add(controlPanel) ;

        frame.add(topPanel) ;

        frame.setVisible(true);

        // action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    output.println("2");

                    output.println(usernameFeild.getText());
                    output.println(new String(passwordFeild.getText())) ;
                    UserManager userManager = new UserManager() ;
                    User thisUser = userManager.getUser(usernameFeild.getText()) ;
                    client.user = thisUser ;
                    // server response
                    String serverResponse = bfr.readLine();
                    if (serverResponse.equals("true")) {
                        frame.dispose();
                        homePage.showHomePage(thisUser);
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


    }


    // signup details (server code 1)
    private void signUp() {

        // create a frame
        JFrame frame = new JFrame("Sign Up") ;
        frame.setLayout(new FlowLayout());
        frame.setSize(new Dimension(2000 , 1000));
        frame.getContentPane().setBackground(new Color(0x141414));



        JPanel topPanel = new JPanel() ;
        topPanel.setPreferredSize(new Dimension(2000 , 1000));
        topPanel.setBackground(new Color(0x141414));
        topPanel.setLayout(null);

        JLabel banner = new JLabel() ;
        banner.setText("         Create New Account.");
        banner.setFont(new Font("Monospaced" , Font.BOLD , 35));
        ImageIcon front = new ImageIcon("img_1.png") ;
        Image frontImage = front.getImage().getScaledInstance(1800 , 325 , Image.SCALE_SMOOTH) ;
        ImageIcon frontIcon = new ImageIcon(frontImage) ;
        banner.setIcon(frontIcon);
        banner.setVerticalTextPosition(JLabel.TOP);
        banner.setHorizontalTextPosition(JLabel.CENTER);
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setForeground(new Color(0x6eaa6b));
        banner.setOpaque(true);
        banner.setIconTextGap(-220);
        banner.setBounds(0,0 , 1800 , 325);
        topPanel.add(banner) ;



        JPanel inputPanel = new JPanel() ;
        inputPanel.setLayout(new FlowLayout( FlowLayout.CENTER , 10 ,20));

        inputPanel.setBounds(570,300, 650,450);

        inputPanel.setBackground(new Color(0x141414));


        JLabel firstName = new JLabel("      First Name: ") ;
        // Labels don't need prefered size
        firstName.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        firstName.setForeground(Color.yellow);

        JTextField firstNameFeild = new JTextField() ;
        firstNameFeild.setPreferredSize(new Dimension(350 ,40));
        firstNameFeild.setBorder(BorderFactory.createEmptyBorder());
        firstNameFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));

        inputPanel.add(firstName);
        inputPanel.add(firstNameFeild) ;

        JLabel lastName = new JLabel("       Last Name: ") ; // Labels don't need prefered size
        lastName.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        lastName.setForeground(Color.yellow);

        JTextField lastNameFeild = new JTextField() ;
        lastNameFeild.setPreferredSize(new Dimension(350 ,40));
        lastNameFeild.setBorder(BorderFactory.createEmptyBorder());
        lastNameFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(lastName);
        inputPanel.add(lastNameFeild) ;
        topPanel.add(inputPanel);

        // Phone
        JLabel phone  = new JLabel("          Phone:  ") ; // Labels don't need prefered size
        phone.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        phone.setForeground(Color.yellow);

        JTextField phoneFeild = new JTextField() ;
        phoneFeild.setPreferredSize(new Dimension(350 ,40));
        phoneFeild.setBorder(BorderFactory.createEmptyBorder());
        phoneFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(phone );
        inputPanel.add(phoneFeild) ;
        topPanel.add(inputPanel);
        
        // Email
        // Phone
        JLabel email  = new JLabel("           Email: ") ; // Labels don't need prefered size
        email.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        email.setForeground(Color.yellow);

        JTextField emailFeild = new JTextField() ;
        emailFeild.setPreferredSize(new Dimension(350 ,40));
        emailFeild.setBorder(BorderFactory.createEtchedBorder());
        emailFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(email );
        inputPanel.add(emailFeild) ;
        topPanel.add(inputPanel);
        
        //Username
        JLabel username  = new JLabel("        Username: ") ; // Labels don't need prefered size
        username.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        username.setForeground(Color.yellow);

        JTextField usernameFeild = new JTextField() ;
        usernameFeild.setPreferredSize(new Dimension(350 ,40));
        usernameFeild.setBorder(BorderFactory.createEmptyBorder());
        usernameFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(username);
        inputPanel.add(usernameFeild) ;
        topPanel.add(inputPanel);

        //Password
        JLabel password  = new JLabel("        Password: ") ; // Labels don't need prefered size
        password.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        password.setForeground(Color.yellow);

        JTextField passwordFeild = new JTextField() ;
        passwordFeild.setPreferredSize(new Dimension(350 ,40));
        passwordFeild.setBorder(BorderFactory.createEmptyBorder());
        passwordFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(password);
        inputPanel.add(passwordFeild) ;
        topPanel.add(inputPanel);

        // Confirm Password
        JLabel confirm  = new JLabel("Confirm Password: ") ; // Labels don't need prefered size
        confirm.setFont(new Font("Monospaced" , Font.PLAIN , 20));
        confirm.setForeground(Color.yellow);

        JTextField confirmFeild = new JTextField() ;
        confirmFeild.setPreferredSize(new Dimension(350 ,40));
        confirmFeild.setBorder(BorderFactory.createEmptyBorder());
        confirmFeild.setFont(new Font("Monospaced" , Font.BOLD , 20));
        inputPanel.add(confirm);
        inputPanel.add(confirmFeild) ;
        topPanel.add(inputPanel);


        //Buttons
        JPanel controlPanel = new JPanel() ;
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER , 50 ,10));
        controlPanel.setBounds(630,800, 700,250);
        controlPanel.setBackground(new Color(0x141414));

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(150, 50));
        signUpButton.setBackground(Color.magenta) ;
        signUpButton.setBorder(BorderFactory.createEtchedBorder());
        signUpButton.setFocusable(false);
        signUpButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        signUpButton.setForeground(Color.black);
        signUpButton.setOpaque(true);


        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setBackground(new Color(0x6eaa6b)) ;
        backButton.setBorder(BorderFactory.createEtchedBorder());
        backButton.setFocusable(false);
        backButton.setFont(new Font("Monospaced" , Font.BOLD , 20));
        backButton.setForeground(Color.black);
        backButton.setOpaque(true);



        // instantiate HomePageGUI to use its methods
        HomePage homePage = new HomePage(client);

        controlPanel.add(signUpButton) ;
        controlPanel.add(backButton) ;
        topPanel.add(controlPanel) ;

        frame.add(topPanel) ;
        frame.setVisible(true);

        // action listeners
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    output.println("1");
                    output.println(firstNameFeild.getText());
                    output.println(lastNameFeild.getText());
                    output.println(phoneFeild.getText());
                    output.println(emailFeild.getText());
                    output.println(usernameFeild.getText());
                    output.println(passwordFeild.getText());
                    output.println(confirmFeild.getText());
                    // server response
                    String serverResponse = bfr.readLine();
                    if (serverResponse.equals("true")) {
                        JOptionPane.showMessageDialog(frame, "Signup successful!");
                        frame.dispose();
                        start();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid Entry! Username already exists.");
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

    }


}