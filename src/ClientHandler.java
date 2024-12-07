// CLIENT HANDLER for handling client requests and multi-threadding.

class ClientHandler extends ServerManager implements Runnable {
    private Socket clientSocket;


    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    // -----------------------------------------------------
    public void run() {
        try (InputStream input = clientSocket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = clientSocket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            String message;
            User user = null;
            while ((message = reader.readLine()) != null) {


                switch (message) {
                    case "1":
                        String firstName = reader.readLine();
                        String lastName = reader.readLine();
                        String phone = reader.readLine();
                        String email = reader.readLine();
                        String username = reader.readLine();
                        String password = reader.readLine();
                        String confirmPassword = reader.readLine();
                        user = user_createNewUser(firstName, lastName, phone, email, username, password, confirmPassword);
                        if (user != null) {
                            writer.println("true");
                        } else {
                            writer.println("false");
                        }
                        break;
                    case "2":
                        String username2 = reader.readLine();
                        String pass = reader.readLine();
                        boolean result2 = user_validateUser(username2, pass);
                        if (result2) {
                            user = helper.getUser(username2);
                            // Retrieve the full user object
                            System.out.println(user.displayUserInfo());
                            writer.println("true");
                        } else {
                            writer.println("false");
                        }
                        break;

                    case "3":
                        user_displayProfilePicture(user);
                        break;
                    case "4":
                        String username3 = reader.readLine();
                        boolean result3 = user_searchUserFile(username3);
                        writer.println(result3);
                        break;
                    case "5":
                        String returnString = user_displayContacts(user);
                        writer.println(returnString);
                        break;
                    case "6":
                        String contactUsername = reader.readLine();
                        User conatct = helper.getUser(contactUsername);
                        String result6 = user_addContact(user, conatct);
                        System.out.println(result6);
                        writer.println(result6);
                        break;
                    case "7":
                        String username4 = reader.readLine();
                        String result4 = user_blockUser(user, username4);
                        writer.println(result4);
                        break;
                    case "8":
                        String username5 = reader.readLine();
                        String result5 = user_unblockUser(user, username5);
                        writer.println(result5);
                        break;
                    case "9":
                        writer.println(user_getBlockedList(user)) ;
                        break;
                    case "10":
                        String phoneNumber = reader.readLine();
                        String result10 = user_removeContact(user, phoneNumber);
                        writer.println(result10);
                        break;
                    case "11":
                        String usernameToConvo = reader.readLine();
                        User userToConvo = helper.getUser(usernameToConvo);
                        writer.println(user_startConversation(user, userToConvo));
                        break;
                    case "12":
                        String username12 = reader.readLine();
                        writer.println(user_deleteConversation(user, username12));
                        break;
                    case "13":
                        String username13 = reader.readLine();
                        System.out.println(username13);
                        String messegeLog = user_getMessages(user, username13);
                        writer.println(messegeLog);
                        break;
                    case "14":
                        String username14 = reader.readLine();
                        String message14 = reader.readLine();
                        writer.println(user_sendMessage(user, username14, message14));
                        break;
                    case "15":
                        User friend = getUser(reader.readLine());
                        String messageToDelete = reader.readLine();
                        String result15 = user_deleteMessage(user, friend, messageToDelete) ;
                        writer.println(result15);
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
