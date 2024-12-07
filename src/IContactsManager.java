/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Interface for ContactsManager
 *
 * @author Nicholas Chong, Rishitha Adusumilli, Shaivi Mishra, Hiya Jha
 *
 * @version 11/27/2024
 *
 */

public interface IContactsManager {

    String displayContacts();
    String addContact(User user);
    boolean isContact(User user) ;
    String removeContact(String phoneNumber) ;

}
