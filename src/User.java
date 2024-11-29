import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * CS 180 Team Project -- Social Media Platform with Direct Messaging
 *
 * This program is responsible for creating a new user and assigning their details.
 *
 * @author Hiya Jha, L28 Team 3
 *
 * @version 11/3/2024
 *
 */

public class User extends UserManager{

    private BufferedImage profilePicture;
    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String username;

    private String password;

    private ContactsManager contactsManager ;
    private Block blocked ;
    private Messages messages ;
    private Conversation conversation ;
    private UserManager UserManager ;



    // Constructor

    // CreateNewUSer or SignUp method - Creates a new User using the mentioned arguments and adds it to the list of all Users.
    // Since, this class extends UserManager, an object of UserManager class s created with the User object,
    // which intializes all the Data ArrayLists.
    public User(String firstName, String lastName, String phone, String email, String username, String password) {

        this.firstName = firstName;

        this.lastName = lastName;

        this.phone = phone;

        this.email = email;

        this.username = username;

        this.password = password;

        this.contactsManager = new ContactsManager(String.format("%s_Contacts.txt" , username)) ;
        this.messages = new Messages(username) ;
        this.conversation = new Conversation(this) ;
        this.blocked = new Block(username) ;
        if (ALL_USERS.isEmpty()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("Users.txt", true))) {
                writer.print(this);
            } catch (IOException ie) {
                System.out.println("Error loading to Users.txt");
            }
            ALL_USERS.add(this) ;
        } else if (!searchUsersFile(this.username)) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("Users.txt", true))) {
                writer.print(this);
            } catch (IOException ie) {
                System.out.println("Error loading to Users.txt");
            }
            ALL_USERS.add(this) ;
        }


    }

    public User(String firstName, String lastName, String phone, String email, String username, String password, BufferedImage profilePicture) {
        // ... existing constructor code
        this.profilePicture = profilePicture;

        this.firstName = firstName;

        this.lastName = lastName;

        this.phone = phone;

        this.email = email;

        this.username = username;

        this.password = password;

        this.contactsManager = new ContactsManager(String.format("%s_Contacts.txt" , username)) ;
        this.messages = new Messages(username) ;
        this.conversation = new Conversation(this) ;
        if (!searchUsersFile(this.username)) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("Users.txt", true))) {
                writer.print(this);
            } catch (IOException ie) {
                System.out.println("Error loading to Users.txt");
            }
            ALL_USERS.add(this) ;
        }

    }

    public User() {
        this.firstName = "#";

        this.lastName = "#";

        this.phone = "#";

        this.email = "#";

        this.username = "#";

        this.password = "#";

        this.contactsManager = new ContactsManager(String.format("TestUser.txt" , username)) ;
        this.messages = new Messages(username) ;
        this.conversation = new Conversation(this) ;


    }




    // Getters and Setters

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }



    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }



    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }



    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }



    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }



    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public ContactsManager getContactsManager() {
        return contactsManager;
    }


    public Messages getMessages() {
        return messages;
    }


    public Conversation getConversation() {
        return conversation;
    }

    public Block getBlocked() {
        return blocked;
    }

    // The toString methods converts the User object into a String form, every parameter seperated by a comma
    public String toString() {
       String returnString = String.format("%s,%s,%s,%s,%s,%s\n" , this.firstName , this.lastName , this.phone , this.email , this.username , this.password);
       return returnString ;
    }

    // This method displays the information of the User object that calls this method. Useful when searching for a user.
    public String displayUserInfo() {
        String returnString = String.format("Name: %s\nUsername: %s\nPhone: %s\nemail-ID: %s\nAccount Password: %s\n" , this.firstName + " " + this.lastName , this.username , this.phone , this.email , this.password ) ;
        return returnString ;
    }

    public BufferedImage getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(BufferedImage profilePicture) {
        this.profilePicture = profilePicture;
    }
}