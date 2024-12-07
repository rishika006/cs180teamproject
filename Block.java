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
    private List<String> blockedUsers;


    // constructor
    public Block(String username) {
        this.username = username;
        gatekeeper = new Object();
        this.blockedUsers = new ArrayList<>();
        loadBlockedFileIntoList();
    }


    // helper method to get the file name of username's blocked list
    public String getBlockedFilename() {
        return username + "_blocked.txt";
    }


    // load "blocked" file into the ArrayList
    private void loadBlockedFileIntoList() {
        blockedUsers.clear();
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
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(getBlockedFilename()))) {
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
        if (blockedUsers.contains(username2)) {
            return String.format("%s is already blocked." , username2) ;
        }

        UserManager userManager = new UserManager();
        User blockUser = userManager.getUser(username2);
        if (blockUser == null) {
            return "User does not exist.";
        }

        // remove from contacts if on there
        User thisUser = userManager.getUser(this.username);
        System.out.println(thisUser.getContactsManager().displayContacts());
        thisUser.getContactsManager().removeContact(blockUser.getPhone());

        // add to ArrayList & update the file
        blockedUsers.add(username2);
        writeListIntoBlockedFile();

        return String.format("%s was blocked." , username2);

    }


    // CHANGED
    // unblock user (removes username2 from username1's "blocked" list)
    // true if successful, false if not
    public String unblockUser(String username2) {
        if (!blockedUsers.contains(username2)) {
            return String.format("%s is not in your blocked contacts.", username2);
        }
        blockedUsers.remove(username2);
        writeListIntoBlockedFile();
        return String.format("%s was unblocked.", username2);

    }


    // returns true/false if username2 is in username1's blocked list
    // in other words, checks if username1 blocks username2
    public boolean isBlocked(String username2) {
        return blockedUsers.contains(username2);
    }


    // prints all usernames that username1 has on their blocked list
    public String getBlocked() {
        if (blockedUsers.isEmpty()) {
            return "You have no blocked contacts.";
        }
        return String.join(";", blockedUsers);
    }

}