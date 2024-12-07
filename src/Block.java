import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CS180 Team Project -- Direct Messaging Platform
 * <p>
 * All methods for blocking users. Includes methods to block/unblock,
 * checking if a user blocks another user, and printing all users that
 * a username blocks
 *
 * @author Nicholas Chong, L28
 * @version 11/17/2024
 */

public class Block {

    private String username;
    private static Object gatekeeper;


    // constructor
    public Block(String username) {
        this.username = username;
        gatekeeper = new Object();
    }


    // helper method to get the file name of username's blocked list
    public String getBlockedFilename() {
        return username + "_blocked.txt";
    }


    // block user (adds username2 to username1's "blocked" list)
    // true if successful, false if not
    public String blockUser(String username2) {
        if (isBlocked(username2)) {
            return String.format("%s is already blocked." , username2) ;
        }
        UserManager userManager = new UserManager() ;
        User blockUser = userManager.getUser(username2) ;
        System.out.println(blockUser);
        if (blockUser == null ) {
            return "User does not exist." ;
        }

        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(getBlockedFilename(), true))) {
            bfw.write(username2);
            bfw.newLine();
            User thisUser = userManager.getUser(this.username) ;
            System.out.println(thisUser.getContactsManager().displayContacts()) ;
            thisUser.getContactsManager().removeContact(blockUser.getPhone()) ; // remove from contacts
            return String.format("%s was blocked." , username2); // successfully added to blocked list
        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown ERROR in blocking contact."; // username's blocked file is not found
        }

    }


    // CHANGED
    // unblock user (removes username2 from username1's "blocked" list)
    // true if successful, false if not
    public String unblockUser(String username2) {

        // create list of blocked users (so we can add all except that to the updated file)
        List<String> blockedUsersList = new ArrayList<>();
        // put all blocked users from file to the list
        int counter = 0;
        try (BufferedReader bfr = new BufferedReader(new FileReader(getBlockedFilename()))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line == null) {
                    return "You have no blocked users." ;
                }
                if (line.equals(username2)) {
                    counter++ ;
                }

                if (!line.equals(username2)) {
                    blockedUsersList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown Error in accessing blocked contacts."; // error reading file
        }

        // rewrite blocked file but without username2
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(getBlockedFilename()))) {
            for (String user : blockedUsersList) {
                bfw.write(user);
                bfw.newLine();
            }
            UserManager userManager = new UserManager() ;
            User thisUser = userManager.getUser(this.username) ;
            User unblockedUser = userManager.getUser(username2) ;
            if (unblockedUser == null) {
                return "User does not exist." ;
            }
            thisUser.getContactsManager().addContact(unblockedUser) ;
            if (counter == 0) {
                return String.format("%s is not in your blocked conatacts." , username2) ;
            } else {
                return String.format("%s was unblocked.", username2); // username2 successfully unblocked
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown Error in accessing blocked contacts."; // error reading file
        }

    }


    // returns true/false if username2 is in username1's blocked list
    // in other words, checks if username1 blocks username2
    public boolean isBlocked(String username2) {
        try (BufferedReader bfr = new BufferedReader(new FileReader(getBlockedFilename()))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line.equals(username2)) {
                    return true; // yes, username2 is in username1's blocked list
                }
            }

        } catch (FileNotFoundException fnfe) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // no, username2 is not in username1's blocked list
    }


    // prints all usernames that username1 has on their blocked list
    public String getBlocked() {
        try (BufferedReader bfr = new BufferedReader(new FileReader(getBlockedFilename()))) {
            String line;
            String blocked = "" ;
            while ((line = bfr.readLine()) != null) {
                blocked += line + ";" ;

            }
            if (blocked.isEmpty()) {
                return "You have no blocked contacts." ;
            }
            return blocked.substring(0,blocked.length()-1) ;
        } catch (FileNotFoundException fnfe) {
            return "You have no blocked contacts." ;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error accessing blocked contacts " ;
        }
    }

}
