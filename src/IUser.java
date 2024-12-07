import java.awt.image.BufferedImage;

/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Interface for UserManager
 *
 * @author Nicholas Chong, Rishitha Adusumilli, Shaivi Mishra, Hiya Jha
 *
 * @version 11/27/2024
 *
 */

public interface IUser {
    Messages getMessages() ;
    Conversation getConversation() ;
    Block getBlocked() ;
    String toString() ;
    String displayUserInfo() ;
    BufferedImage getProfilePicture() ;
    void setProfilePicture(BufferedImage profilePicture) ;
}
