public interface IContactsManager {

    String displayContacts();
    String addContact(User user);
    boolean isContact(User user) ;
    String removeContact(String phoneNumber) ;

}
