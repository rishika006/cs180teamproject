public class ServerManager extends UserManager implements IServerManager {
    public final User helper = new User() ;



    public User user_createNewUser(String firstName, String lastName, String phone, String email, String username, String password, String confirmPassword) {
        return createNewUser(firstName, lastName, phone, email, username,
                password, confirmPassword);
    }

    public boolean user_validateUser(String username, String password) {
        boolean isValid = helper.validateUser(username, password);
        if (isValid) {
            // Fetch the actual user object and return true
            return true;
        }
        return false;
    }



    public void user_displayProfilePicture(User user) {
        displayProfilePicture(user);
    }

    public boolean user_searchUserFile(String username) {
        return helper.searchUsersFile(username);
    }

    public User user_GetUser(String username) {
        return (helper).getUser(username) ;
    }

    public String user_displayContacts(User user) {
        return (user.getContactsManager().displayContacts());
    }

    public String user_addContact(User user , User contact) {
        return user.getContactsManager().addContact(contact);
    }

    public String user_blockUser(User user, String username2) {
        return user.getBlocked().blockUser(username2);
    }

    public String user_unblockUser(User user, String username2) {
        return user.getBlocked().unblockUser(username2);
    }

    public String user_getBlockedList(User user) {
       return user.getBlocked().getBlocked();
    }

    public String user_removeContact(User user, String phoneNumber) {
        return user.getContactsManager().removeContact(phoneNumber);
    }

    public String user_startConversation(User user , User userToConvo) {
        return user.getConversation().startConv(userToConvo);
    }

    public String user_deleteConversation(User user, String username2) {
        return user.getMessages().deleteConversation(username2);
    }

    public String user_getMessages(User user, String username2) {
        return user.getMessages().getMessages(username2);
    }

    public String user_sendMessage(User user, String username2, String message) {
        return user.getMessages().sendMessage(username2, message);
    }

    public String user_deleteMessage(User user, User friend, String messageToDelete) {
        return user.getMessages().deleteMessage(friend, messageToDelete);
    }
}
