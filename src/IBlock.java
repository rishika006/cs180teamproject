public interface IBlock {
    String getBlockedFilename() ;
    String blockUser(String username2) ;
    String unblockUser(String username2) ;
    boolean isBlocked(String username2) ;
    void getBlocked() ;
}
