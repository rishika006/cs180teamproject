/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Interface for Conversation
 *
 * @author Nicholas Chong, Rishitha Adusumilli, Shaivi Mishra, Hiya Jha
 *
 * @version 11/27/2024
 *
 */

public interface IConversation {
     boolean getConversationFileName(String username2) ;
     String startConv(User user) ;
     User getUser() ;
}
