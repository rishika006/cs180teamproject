import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * CS 180 Team Project -- Social Media Platform with Direct Messaging
 *
 * This program is responsible for developing the user profile to be used on the social media platform.
 *
 * @author Hiya Jha, L28 Team 3
 *
 * @version 11/3/2024
 *
 */
public class UserManager  {

    public static ArrayList<User> ALL_USERS ;
    public static ArrayList<String> ALL_CONVERSATION_FILES ;
    public static ArrayList<String> ALL_CONTACTS_FILES ;


    // Creates a UserManager object that contains static ArrayLists to store User Data.
    // Initializes all Arrays.

    public UserManager() {
        if (ALL_USERS == null) {
            ALL_USERS = new ArrayList<>();
            ALL_CONVERSATION_FILES = new ArrayList<>() ;
            ALL_CONTACTS_FILES = new ArrayList<>() ;
        }
        if (ALL_CONVERSATION_FILES == null) {
            ALL_CONVERSATION_FILES = new ArrayList<>() ;
        }

        if (ALL_CONTACTS_FILES == null) {
            ALL_CONTACTS_FILES = new ArrayList<>() ;
        }

    }




    // readUser Load users from file to the ALL_USERS ArrayList.

    public void readUser() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))) {

            String line;
            ALL_USERS.clear();

            while ((line = reader.readLine()) != null) {

                String[] userDetails = line.split(",");

                String firstName = userDetails[0];

                String lastName = userDetails[1];

                String phone = userDetails[2];

                String email = userDetails[3];

                String username = userDetails[4];

                String password = userDetails[5];


                for (User user : ALL_USERS) {
                    if (!user.getUsername().equals(username)) {
                        new User(firstName, lastName, phone, email, username, password);
                    }
                }
            }

        } catch (IOException e) {

            System.out.println("Error loading users: " + e.getMessage());

        }


    }



    // This method saves the provided user in the Users.txt file. This means, the given user is not a Messenger User!

    private void addUser(User user) {
        if (!ALL_USERS.contains(user)) {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true))) {
                writer.write(user.toString());
                writer.newLine();
            } catch (IOException e) {

                System.out.println("Error saving user: " + e.getMessage());

            }
            ALL_USERS.add(user) ;

        }

    }



    // Check if a username already exists among any other Users in the List of Users.

    public boolean usernameExists(String username) {
        readUser();
        for (User user : ALL_USERS) {

            if (user.getUsername().equals(username)) {

                return true;

            }

        }

        return false;

    }



    // Create New User

    public static User createNewUser(String firstName, String lastName, String phone, String email, String username, String password, String confirmPassword) {

        for (User user : ALL_USERS) {
            if (user.getUsername().equals(username)) {

                System.out.println("Username already exists. Please choose a different one.");

                return null;
            }

        }

        if (!password.equals(confirmPassword)) {

            System.out.println("Passwords do not match.");

            return null ;

        }



        User newUser = new User(firstName, lastName, phone, email, username, password);



        System.out.println("Account created successfully!");

        return newUser;

    }



    // Validate User

    public boolean validateUser(String username, String password) {

        if (searchUsersFile(username)) {
            if (userSearch(username) == null) {
                ALL_USERS.add(getUser(username)) ;
            }
            User found = userSearch(username) ;
            if (found.getPassword().equals(password)) {
                return true ;
            }
        } else {
            for (User user : ALL_USERS) {

                if (user.getUsername().equals(username)) {

                    if (user.getPassword().equals(password)) {

                        System.out.println("Login successful!");

                        return true;

                    } else {

                        System.out.println("Incorrect password. Please try again.");

                        return false;

                    }

                }

            }
        }

        System.out.println("Username not found. Please try again or create an account.");

        return false;

    }



    // Display user info given a username

    public String getUserInfo(String username) {

        for (User user : ALL_USERS) {

            if (user.getUsername().equals(username)) {

                String returnString = String.format("Name: %s\nUsername: %s\nPhone: %s\nemail-ID: %s\nAccount Password: %s\n" , user.getFirstName() + " " + user.getLastName() , user.getUsername() , user.getPhone() , user.getEmail() , user.getPassword() ) ;
                return returnString ;



            }

        }

        return ("User not found.") ;

    }



    // Search for a user by username and display their info if found

    public boolean searchUser(String username) {

        //System.out.println("Searching for user: " + username);

        for (User user : ALL_USERS) {

            if (user.getUsername().equals(username)) {


                return true;

            }

        }

        return false ;

    }

    // Search for a user by username and return that user

    public User userSearch(String username) {

        //System.out.println("Searching for user: " + username);

        for (User user : ALL_USERS) {

            if (user.getUsername().equals(username)) {


                return user;

            }

        }
        return null ;
    }


    // Added
    public boolean searchUsersFile(String username2) {
        String line ;
        String[] user ;
        try ( BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))) {
            while ((line = reader.readLine()) != null) {
                if (line == null || line.isEmpty()) {
                    return false ;
                } else {
                    user = line.split(",");
                    String username = user[4];
                    if (username.equals(username2)) {
                        return true;
                    }
                }
            }
        } catch (IOException ie ) {
            return false ;
        }
        return false ;
    }

    // Added
    public User getUser(String username3) {
        String line ;
        String[] user ;
        try ( BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))) {
            while ((line = reader.readLine()) != null) {
                    user = line.split(",");
                    String username = user[4];
                    if (username.equals(username3)) {
                        User newUser = new User(user[0] , user [1] , user[2] , user[3] , user[4] , user[5]) ;
                        return newUser;
                    }

            }
        } catch (IOException ie ) {
            System.out.println("error reading Users.txt");
            return null;
        }
        return null ;

    }

    //EXTRA CREDIT

    // Takes user and an image file (.jpg) as arguments and sets image as profilePicture.
    public boolean uploadProfilePicture(User user, String filePath) {
        try {
            File imageFile = new File(filePath) ;
            BufferedImage img = ImageIO.read(imageFile);  // Load the image
            user.setProfilePicture(img);  // Set the BufferedImage to the user
            return true;
        } catch (IOException e) {
            System.out.println("Error uploading profile picture: " + e.getMessage());
            return false;
        }
    }

    // Saving the uploaded Profile picture in hardDisk
    public void saveProfilePicture(User user) {
        if (user.getProfilePicture() != null) { // Check is Pfp is uploaded
            File directory = new File("C:\\hp\\CS180\\CS180 Team Project\\Profile_Pictures");
            // Creates a new Directory in Project called "Profile_Pictures"
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
            }
            File outputFile = new File(directory, user.getUsername() + "_profile.png"); // Use username for the filename
            try {
                ImageIO.write(user.getProfilePicture(), "png", outputFile); // Save the image
                System.out.println("Profile picture saved successfully!") ;
            } catch (IOException e) {
                System.out.println("Error saving profile picture: " + e.getMessage());
            }
        } else {
            System.out.println("No profile picture to save.");
        }
    }

    public void displayProfilePicture(User user) {
        if (user.getProfilePicture() != null) {
            ImageIcon imageIcon = new ImageIcon(user.getProfilePicture());
            JLabel imageLabel = new JLabel(imageIcon);
            JFrame frame = new JFrame("Profile Picture");
            frame.add(imageLabel);
            frame.setSize(new Dimension(500, 500));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        } else {
            System.out.println("No profile picture available for this user.");
        }
    }



}