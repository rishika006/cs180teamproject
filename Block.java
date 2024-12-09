import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<String> blockedUsers = new ArrayList<>() ;


    // constructor
    public Block(String username) {
        this.username = username;
        gatekeeper = new Object();


    }


    // helper method to get the file name of username's blocked list
    public String getBlockedFilename() {
        return username + "_blocked.txt";
    }

    // load "blocked" file into the ArrayList
    private void loadBlockedFileIntoList() {

        if (blockedUsers != null) {
            blockedUsers.clear();
        }
        try (BufferedReader bfr = new BufferedReader(new FileReader(getBlockedFilename()))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                blockedUsers.add(line.trim());
            }
        } catch (FileNotFoundException fe) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // write ArrayList into "blocked" file (overwrite)
    private void writeListIntoBlockedFile() {

        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(getBlockedFilename() , false))) {
            for (String user : blockedUsers) {
                bfw.write(user);
                bfw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // block user (adds username2 to username1's "blocked" list)
    // true if successful, false if not
    public String blockUser(String username2) {
        loadBlockedFileIntoList();
        UserManager userManager = new UserManager() ;
        User blockUser = userManager.getUser(username2) ;

        User thisUser = userManager.getUser(username) ;
        if (blockUser == null ) {
            return "User does not exist." ;
        }
        if (isBlocked(username2)) {
            return String.format("%s is already blocked." , username2) ;
        }

        if (!thisUser.getContactsManager().isContact(blockUser)) {
            return String.format("%s is not in your contacts" , blockUser.getUsername()) ;
        }

        System.out.println(blockUser);


        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(getBlockedFilename(), true))) {
            bfw.write(username2);
            bfw.newLine();
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

        loadBlockedFileIntoList();
        if (blockedUsers == null || blockedUsers.isEmpty()) {
            return "You have no blocked users." ;
        }
        UserManager userManager = new UserManager() ;
        User thisUser = userManager.getUser(this.username) ;
        User unblockedUser = userManager.getUser(username2) ;
        if (unblockedUser == null) {
            return "User does not exist." ;
        }
        blockedUsers.remove(unblockedUser.getUsername()) ;

        writeListIntoBlockedFile();
        System.out.println(thisUser.getContactsManager().addContact(unblockedUser)) ;

        return "User unblocked successfully" ;

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

        System.out.println(getBlockedFilename());
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