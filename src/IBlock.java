/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Interface for Block
 *
 * @author Nicholas Chong, Rishitha Adusumilli, Shaivi Mishra, Hiya Jha
 *
 * @version 11/27/2024
 *
 */

public interface IBlock {
    String getBlockedFilename() ;
    String blockUser(String username2) ;
    String unblockUser(String username2) ;
    boolean isBlocked(String username2) ;
    void getBlocked() ;
}
