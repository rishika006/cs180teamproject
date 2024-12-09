import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 *
 * This program is responsible for managing the contacts to be used in the social media platform.
 *
 * @author Rishitha Adusumilli L28 Team 3
 *
 * @version 11/3/2024
 *
 */

public class ContactsManager extends UserManager implements IContactsManager {
    List<Contact> contacts;
    private String filePath;
    private static Object gatekeeper ;
    //Constructor
    public ContactsManager(String filePath) {
        this.contacts = new ArrayList<>();
        this.filePath = filePath;
        gatekeeper = new Object() ;
        if (!contacts.isEmpty()){
            loadContacts();
        }

    }
    //Reads in the contacts from the storage file
    private void loadContacts() {
        File file = new File(filePath) ;
        if (!file.exists()) {
            return ;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            contacts.clear();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(",");

                contacts.add(new Contact(parts[0], parts[1])); // CONTACT IN FILE AS : NAME,NUMBER
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading contacts file.");
        }
    }
    //Displays the contacts in the list
    public String displayContacts() {
        loadContacts();
        String contactList = "";
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return "No Contacts available." ;
        } else {
            for (Contact contact : contacts) {
                contactList += contact.toString().replace("\n" , ";") ;
            }
            return contactList.substring(0,contactList.length()-1);
        }



    }
    // Creates a new contact and adds it to the contact list
    public String addContact(User user) {

        synchronized (gatekeeper) {
            loadContacts();

            if (user == null) {
                return "User does not exist. Cannot add to contacts." ;
            }
            String[] filename = filePath.split("_") ;

            Block block = new Block(filename[0]) ;
            if (block.isBlocked(user.getUsername())) {
                return "This user has been blocked. Unblock user to add to contacts." ;
            }
            if (searchUsersFile(user.getUsername())) {
                for (Contact contact : contacts) {
                    if (contact.getPhoneNumber().equals(user.getPhone())) {
                        System.out.println("Contact already exists.");
                        return "Contact already exists.";
                    }
                }

                contacts.add(new Contact(user.getUsername(), user.getPhone()));
                saveContacts();
                return String.format("%s was added to contacts" , user.getUsername());
            }

            System.out.println("ERROR! Cannot Add to contacts - This User does not exist.");
            return "User does not exist. Cannot add to contacts.";
        }

    }
    //Checks if a userame is on the contact list
    public boolean isContact(User user) {
        if (user == null ) {
            return false ;
        }
        loadContacts();
        for ( Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(user.getPhone())) {
                return true ;
            }
        }
        return false ;
    }

    // remove contact base on inputted phone number
    public String removeContact(String phoneNumber) {
        loadContacts();
        synchronized (gatekeeper) {

            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                    contacts.remove(i);

                    saveContacts();
                    return "Contact removed successfully.";
                }
            }
            saveContacts();
            System.out.println("Contact not found.");
            return "Contact not found.";
        }
    }
    //public boolean getContactFromFile(String phoneNumber) {
       // try(BufferedReader reader = new BufferedReader(new FileReader(this.)))
  //  }




    //Adds a contact to the contact storage file
    private void saveContacts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhoneNumber());

            }
        } catch (IOException e) {
            System.out.println("Error saving contacts.");
        }
    }
}
