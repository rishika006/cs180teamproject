/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Interface for UserManager
 *
 * @author Shaivi Mishra and Hiya Jha
 *
 * @version 11/27/2024
 *
 */

public interface IUserManager {
    void readUser() ;
    void addUser(User user) ;
    boolean usernameExists(String username) ;
    boolean createNewUser(String firstName, String lastName, String phone, String email, String username, String password, String confirmPassword) ;
    boolean validateUser(String username, String password) ;
    String getUserInfo(String username) ;
    boolean searchUser(String username) ;
    boolean searchUsersFile(String username2) ;
    boolean uploadProfilePicture(User user, String filePath) ;
    void saveProfilePicture(User user) ;
    void displayProfilePicture(User user) ;

}
