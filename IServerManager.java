public interface IServerManager {

    boolean user_validateUser(String username, String password);
    void user_displayProfilePicture(User user);
    boolean user_searchUserFile(String username);
    User user_GetUser(String username);
    String user_displayContacts(User user);
    String user_addContact(User user, User contact);
    String user_blockUser(User user, String username2);
    String user_unblockUser(User user, String username2);
    String user_getBlockedList(User user);
    String user_removeContact(User user, String phoneNumber);
    String user_startConversation(User user, User userToConvo);
    String user_deleteConversation(User user, String username2);
    String user_getMessages(User user, String username2);
    String user_sendMessage(User user, String username2, String message);
    String user_deleteMessage(User user, User friend, String messageToDelete);
}
