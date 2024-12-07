/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Interface for Messages
 *
 * @author Nicholas Chong, Rishitha Adusumilli, Shaivi Mishra, Hiya Jha
 *
 * @version 11/27/2024
 *
 */

public interface IMessages {

    boolean hasConversation(String username2);
    String getConversationFileName(String username2);
    String getMessages(String username2);
    String sendMessage(String username2, String message);
    String deleteConversation(String username2);
    String deleteMessage(User user, String messageToDelete) ;

}
