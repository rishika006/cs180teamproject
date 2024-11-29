# CS 180 GOLD Team Project - Social Media Platform

**L28 Team 3**: Nicholas Chong (nchong@purdue.edu), Hiya Jha (jha61@purdue.edu), Rishitha Adusumilli (radusumi@purdue.edu), Alexander Lee (lee4922@purdue.edu),                 Shaivi Mishra (mishr235@purdue.edu)


**Main Objective:** Developing a social media platform that includes a direct messaging system.


**Team Contributions:**

      1. Nicholas Chong - Class Block, Class Messages, Interfaces
      2. Hiya Jha - Class User, Class UserManager
      3. Rishitha Adusumilli - Class Contact, Class ContactsManager
      4. Alexander Lee - Class Conversation
      5. Shaivi Mishra - Test cases, Debugging 


**Phase 1 Code Breakdown:** 

- **Class 1 - User**

Description: Class that represents a user profile with personal information, account details, and associated management functions for contacts, messages, and profile data.

1. `public User(String firstName, String lastName, String phone, String email, String username, String password)`:Initializes a new User object with the provided personal information and sets up the user’s contacts, messages, and conversation instances. If the user does not already exist, it saves the user to Users.txt and adds them to ALL_USERS.
2. `public User(String firstName, String lastName, String phone, String email, String username, String password, BufferedImage profilePicture)`: Similar to the first constructor, this version also includes a profile picture. It initializes the User with the provided details and profile picture, then adds the user to Users.txt and ALL_USERS if they do not already exist.
3. `public String displayUserInfo()`: This method returns a formatted string with the user’s full name, username, phone number, email, and password, useful for displaying user details during search or profile view operations.

    
- **Class 2 - UserManager**

Description: Class that handles operations for managing a collection of users, including adding, searching, and updating user data.

1. `public UserManager ()`: Initializes UserManager with static lists for storing user data, contact files, and conversation files.
2. `public void readUser()`: Loads users from the "Users.txt" file into the ALL_USERS list.
3. `public void addUser(User user)`: Adds a new user to ALL_USERS and saves them to "Users.txt" if they’re not already present.
4. `public boolean usernameExists(String username)`: Checks if a username already exists in the ALL_USERS list.
5. `public boolean createNewUser(String firstName, String lastName, String phone, String email, String username, String password, String confirmPassword)`: Creates a new user account if the username is unique and the passwords match.
6. `public boolean validateUser(String username, String password)`: Validates user login by matching the username and password against ALL_USERS.
7. `public boolean searchUser(String username)`: Searches for a user by username in ALL_USERS and returns true if found.
8. `public boolean searchUsersFile(String username2)`: Searches for a username directly within the "Users.txt" file, returning true if found.
9. `public boolean uploadProfilePicture(User user, String filePath)`: Uploads a profile picture from the specified file path and sets it to the user’s profile.
10. `public void saveProfilePicture(User user)`: Saves the user’s profile picture to a designated folder on disk.
11. `public void displayProfilePicture(User user)`: Displays the user’s profile picture in a new window, if available.


- **Class 3 - Contact**

Description: Class that contains methods for constructing a contact, accessing contact information, and formatting contact details.

1. `public Contact(String name, String phoneNumber)`: constructor that takes in a name and phone number to create a contact.
2. `public String getName()`: returns the contact’s name.
3. `public String getPhoneNumber()`: returns the contact’s phone number.
4. `public String toString()`: formats the contact’s details in the form Name: [name], Phone: [phone number]; returns the formatted string.


- **Class 4 - ContactsManager**

Description: Class that contains methods for managing a list of contacts, including loading from a file, displaying, adding, removing, and saving contacts.

1. `public ContactsManager()`: constructor that initializes the contacts list and loads contacts from the specified file.
2. `private void loadContacts()`: reads contacts from the file (stored in name,number format) and adds each contact to the contacts list.
3. `public void displayContacts()`: displays all contacts and prints "No contacts available." if the list is empty.
4. `public boolean addContact(String name, String phoneNumber)`: adds a new contact with the given name and phone number or returns false and prints "Phone number already taken." if the phone number already exists in the contacts list. It returns true if successful.
5. `public boolean removeContact(String phoneNumber)`: removes a contact based on the specified phone number and returns true if successful. If not, it returns false with the message "Contact not found."
6. `private void saveContacts()`: saves the current contacts list to the file (in name,number format).


- **Class 5 - Conversation**

Description: Class that contains all the methods to find contacts from a contacts list and to create a file to start a conversation with them. 

1. `public boolean getConversationFileName (String username2)`: method searches the message file for the name of the file storing the conversation with another user.
2. `public boolean startConv(User user)`: method to see if the user is in contacts, and, if that is true, creates a file to store the new conversation with the other user.
3. `public User getUser()`: method returns the user object in the class.
4. `public Conversation (User user)`: constructor initializes a Conversation object with the specified User.

    
- **Class 6 - Messages**

Description: Class that contains all methods for messages between two users.

1. `public Messages(String username)`: constructor that takes in the 1st username of a conversation
2. `public boolean hasConversation(String username2)`: checks if inputted user (username2) is in the conversation list; returns true if they are, false if username2's contact does not exist
3. `public String getConversationFileName(String username2)`: helper method to get the name of the conversation file; returns "username1_username2.txt" or "username2.username1.txt", which contain the conversation messages
4. `public boolean getMessages(String username2)`: prints all messages between username & username2; returns true if successful, false if conversation file does not exist
5. `public boolean sendMessage(String username2, String message)`: sends a message (given as argument) from username to username2 by appending it to the conversation messages file (username1/2_username1/2.txt); returns true if successful, false if unsuccessful & conversation file isn't found
6. `public boolean deleteMessage(String username2, String messageToDelete)`: deletes all instances of a message sent by Username to Username2 with the message to delete as an input; returns true if message successfully deleted, false if error
7. `public boolean deleteConversation(String username2)`; deletes the conversation file (username1/2_username1/2.txt) & deletes it from Messages_Files.txt; returns true if successful, false if unsuccessful


- **Class 7 - Block**

Description: Class that contains all methods for blocking users, unblocking users, and accessing blocked users.

1. `public Block(String username)`: constructor that takes in a username to access blocked users
2. `public String getBlockedFilename()`: helper method to get the file name of username's blocked list; returns format "username_blocked.txt"
3. `public boolean blockUser(String username2)`: adds username2 to username1's "blocked" list; returns true if successful, false if username's blocked file not found
4. `public boolean unblockUser(String username2)`: removes username2 from username1's "blocked" list; returns true if successful, false if not
5. `public boolean isBlocked(String username2)`: checks if username1 blocks username2; returns true/false if username2 is in username1's blocked list
6. `public void getBlocked()`: prints all usernames that username1 has on their blocked list

 
- **Text Files**
1.  `Users.txt`: stores names of all users, separated by line
2.  `<username>_contacts.txt`: stores all of username's contacts
3.  `Contacts_Files.txt`: contains all existing files with "username_contacts.txt" format
4.  `<username1>_<username2>.txt`: stores the messages between username1 & username2, format [username: message]
5.  `Messages_Files.txt`: contains all existing files with "username1_username2" format
6.  `<username>_blocked.txt`: stores all usernames that username blocks


- **Relation between classes**
  
The classes are interconnected to form a comprehensive user management system that supports user profiles, contact management, messaging, and blocking functionalities. The **User** class represents individual user profiles, while the **UserManager** class utilizes **User** objects to manage user data and operations, such as adding and validating users. The **Contact** and **ContactsManager** classes work together, with **ContactsManager** handling a collection of **Contact** objects to facilitate adding, removing, and displaying contacts for each user. The **Conversation** class uses **User** objects to initiate conversations, while the **Messages** class relies on **Conversation** instances to manage communication between users by sending and retrieving messages stored in conversation files. Additionally, the **Block** class interacts with **User** objects to manage blocking functionalities, allowing users to control their privacy by blocking or unblocking others.


- **Test classes**
Test cases were made for each significant method in (except getter and setter methods) in all classes and all test cases passed successfully. Test cases for each class can be found in the files under the Tests Folder.
