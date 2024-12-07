/**
 * CS180 Team Project -- Direct Messaging Platform
 * <p>
 * All methods for messages. Methods for checking if user2 is
 * in conversation list, to get/print conversation messages,
 * sending messages, and deleting a conversation
 *
 * @author Nicholas Chong, L28
 * @version 11/1/2024
 */

import java.io.*;
import java.util.ArrayList;

public class Messages implements IMessages {

    private String username;
    private Object gatekeeper;


    // constructor for messages of Username
    // methods will manage messages between Username & another user
    public Messages(String username) {
        this.username = username;
        gatekeeper = new Object();
    }


    // checks if inputted user (username2) is in the conversation list, returns true/false
    public boolean hasConversation(String username2) {
        try (BufferedReader bfr = new BufferedReader(new FileReader("Messages_Files.txt"))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line.contains(username2)) {
                    return true;
                }
            }
            return false; // this means user2's contact does not exist
        } catch (IOException e) {
            return false; // error reading "Contacts_Files.txt"
        }
    }


    // HELPER METHOD: get the name of the conversation file
    // returns "username1_username2.txt" or "username2_username1.txt"
    public String getConversationFileName(String username2) {
        try (BufferedReader bfr = new BufferedReader(new FileReader("Messages_Files.txt"))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line.equals(String.format("%s_%s.txt", username, username2))) {
                    return String.format("%s_%s.txt", username, username2);
                } else if (line.equals(String.format("%s_%s.txt", username2, username))) {
                    return String.format("%s_%s.txt", username2, username);
                }
            }
        } catch (IOException e) {
            return "Error reading Messages_Files"; // error reading Messages_Files.txt
        }
        return "Conversation not found";
    }


    // print all messages between username & username2 (to console)
    public String getMessages(String username2) {
        // set the name of the file to read (either username1_username2.txt or username2_username1.txt)
        String conversationFileName = getConversationFileName(username2);
        System.out.println(conversationFileName);

        if (conversationFileName.equals("Conversation not found")) {
            return "ERROR: conversation file does not exist" ;
        }
        // print all messages in the conversation to the console
        String messegeLog = "";
        try (BufferedReader bfr = new BufferedReader(new FileReader(conversationFileName))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                messegeLog += line.replace("\n", " ") + ",";
            }
            return messegeLog;
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR: conversation file does not exist"; // conversation file does not exist
        }

    }


    // CHANGED
    // send a message (given as argument) by appending it to conversation username_username.txt file
    public String sendMessage(String username2, String message) {

        // set conversation file name
        if (this.hasConversation(username2)) {

            String conversationFileName = getConversationFileName(username2);
            synchronized (gatekeeper) {
                if (conversationFileName.equals("Conversation not found")) {
                    return "Conversation Not Found." ;
                }
                try (BufferedWriter bfw = new BufferedWriter(new FileWriter(conversationFileName, true))) { // Shcould it be true ?
                    // message/texting format: [username: message]
                    String addedMessage = String.format("%s: %s", this.username, message);
                    bfw.write(addedMessage);
                    bfw.newLine();
                    return "Message sent!" ;
                } catch (IOException e) {
                    System.out.println("Error sending message"); // conversation file not found, couldn't send message

                    return String.format("ERROR! could not send messge to %s" , username2) ;
                }
            }
        } else {
            System.out.printf("Your conversation with %s does not exist. Create a conversation!\n", username2);

            return String.format("Your conversation with %s does not exist. Create a conversation!" , username2);

        }
    }


    // delete a conversation (deletes conversation file username_username.txt,
    // also deletes the conversation username_username.txt from Messages_Files.txt)
    // returns true if successful, false if unsuccessful
    public String deleteConversation(String username2) {

        // set conversation file name (username_username.txt)
        String conversationFileName = getConversationFileName(username2);

        // delete conversation file (username_username.txt)
        File conversationFile = new File(conversationFileName);
        if (!conversationFile.exists() || !conversationFile.delete()) {
            return "No file to delete.";
        }

        // rewrite the Messages_Files.txt with everything except the deleted conversation
        File originalMessageFile = new File("Messages_Files.txt");
        File tempMessageFile = new File("Messages_Files_TEMP.txt");
        synchronized (gatekeeper) {
            try (BufferedReader bfr = new BufferedReader(new FileReader(originalMessageFile));
                 BufferedWriter bfw = new BufferedWriter(new FileWriter(tempMessageFile))) {
                String line;
                while ((line = bfr.readLine()) != null) {
                    if (!line.equals(conversationFileName)) {
                        bfw.write(line);
                        bfw.newLine();
                    }
                }
            } catch (IOException e) {
                return "Unknown ERROR in deleting file.";
            }
        }

        // make tempUpdatedFile the main file (replace Messages_Files.txt with updated version)
        if (originalMessageFile.delete()) { // delete message file, boolean
            tempMessageFile.renameTo(originalMessageFile);
            return String.format("Your conversation with %s was deleted." , username2) ;
        } else {
            return "Unknown ERROR in deleting file.";
        }
    }


    // CHANGED!!
    // delete a specific message sent by Username to Username2 (case-insensitive)
    public String deleteMessage(User user, String messageToDelete) {
        if (user == null) {
            return "User does not exist. Cannot delete message." ;
        }
        ArrayList<String> messages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getConversationFileName(user.getUsername())))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messages.add(line);
            }
        } catch (FileNotFoundException fnfe) {
            return "Conversation file does not exist. Create new conversation.";
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        String delete = this.username + ": " + messageToDelete;
        String textToDelete = "";
        for (String text : messages) {
            if (text.contains(messageToDelete)) {
                textToDelete = text;
                break;
            }
        }
        messages.remove(textToDelete);

        try (PrintWriter writer = new PrintWriter(new FileWriter(getConversationFileName(user.getUsername())), false)) {
            for (String text : messages) {
                writer.println(text);
            }
            return "Message deleted.";

        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return "ERROR deleting message. Try Again!";
    }


}
