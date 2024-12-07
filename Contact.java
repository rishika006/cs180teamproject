/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 *
 * This program is responsible for creating the contacts to be used in the social media platform.
 *
 * @author Rishitha Adusumilli L28 Team 3
 * @version 11/17/2024
 */

public class Contact implements IContact {
    private String name;
    private String phoneNumber;

    // Creating a contact to be stored.
    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;

    }

    // name getter
    public String getName() {
        return name;
    }

    // phone number getter
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Converts contact into a displayable form of [Name: bob, Phone: 765-767-3XXX]
    @Override
    public String toString() {

        return String.format("Name: %s, Phone: %s\n", this.getName(), this.getPhoneNumber());
    }
}

