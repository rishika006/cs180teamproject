import java.awt.image.BufferedImage;

public interface IUser {
    Messages getMessages() ;
    Conversation getConversation() ;
    Block getBlocked() ;
    String toString() ;
    String displayUserInfo() ;
    BufferedImage getProfilePicture() ;
    void setProfilePicture(BufferedImage profilePicture) ;
}
