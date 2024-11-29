/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * All of the methods for conversation. Methods for finding contacts
 * starting and continuing conversations.
 *
 * @author Alexander Lee
 *
 * @version 11/2/2024
 *
 */
import java.io.*;

public class Conversation extends UserManager implements IConversation {
	private User user;
	private static Object gatekeeper ;


	public Conversation(User user) {
		this.user = user;
		gatekeeper = new Object() ;
	}

	/*
	//Asks the user for a username and determines if the username is in their contacts
	public boolean isContact(String username) {
		boolean userFound = false;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Who do you want to start a conversation with?");
		System.out.println("Enter their username below:");
		String usernameInput = scanner.next();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"));
			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.equals(username)) {
					userFound = true;
					reader.close();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		scanner.close();
		return userFound;

	}

	 */
//Checks if the user has a conversation with another user if true,
	//gets the file name storing the conversation
	public boolean getConversationFileName(String username2) {
		try (BufferedReader bfr = new BufferedReader(new FileReader("Messages_Files.txt"))) {
			String line;
			while ((line = bfr.readLine()) != null) {
				if (line.equals(String.format("%s_%s.txt", user.getUsername(), username2))) {
					return true ;
				} else if (line.equals(String.format("%s_%s.txt", username2, user.getUsername()))) {
					return true ;
				}
			}
		} catch (IOException e) {
			return false ;
		}
		return false ;
	}


	// Checks if User is in contacts if yes,
	// Creates a file to store a new conversation with a user
	public String startConv(User user) {

		if (this.getUser().getContactsManager().isContact(user)) {
			// Make a Conversation if User is in contacts
			if (getConversationFileName(user.getUsername())) {
				System.out.printf("Your chat with %s already exists.\n", user.getUsername());
				return String.format("Your chat with %s already exists.", user.getUsername());
			} else {
				synchronized (gatekeeper) {
					try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s_%s.txt", this.user.getUsername(), user.getUsername())))) {
						System.out.println("Starting a conversation with " + user.getUsername() + "...");
						PrintWriter pw = new PrintWriter(new FileWriter("Messages_Files.txt"));
						pw.printf("%s_%s.txt\n", this.getUser().getUsername(), user.getUsername());
						pw.close();

						System.out.printf("Chat created! Send %s a hi!\n", user.getUsername());

						return String.format("Chat created! Send %s a hi!", user.getUsername());
					} catch (IOException e) {
						return "Unknown ERROR in starting conversation. Try Again!";
					}
				}
			}
		}
		else {
			if (user == null) {
				return "User does not exist." ;
			}
			System.out.printf("%s is not in your contacts\n" , user.getUsername());
			return String.format("%s is not in your contacts. Add to contacts" , user.getUsername()) ;
		}
	}




	public User getUser() {
		return user;
	}
}
