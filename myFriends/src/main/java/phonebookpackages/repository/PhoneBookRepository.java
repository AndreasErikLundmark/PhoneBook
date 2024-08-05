//PhoneBook adds or remove friend to a list and .json file

package phonebookpackages.repository;

import com.google.gson.Gson;
import org.springframework.stereotype.Repository;
import phonebookpackages.model.Friend;

import java.util.List;
import java.util.Iterator;

@Repository
public class PhoneBookRepository {
    Friend friend;
    private PhoneBookFileWriter phoneBookFileWriter;
    private static final String FILE_NAME = "friends.json";
    private List<Friend> friends;
    private Gson gson;

    public PhoneBookRepository() {
        this.phoneBookFileWriter = new PhoneBookFileWriter();
        this.friends = phoneBookFileWriter.loadFromFile();
    }

    public void add(String name, long phoneNumber) {
        Friend friend = new Friend(name, phoneNumber);
        for (Friend f : friends) {
            if (name.equals(f.getName()) && phoneNumber == f.getPhoneNumber()) {
                System.out.println("Friend already exists");
                return;
            }
        }
        friends.add(friend);
        phoneBookFileWriter.saveToFile(friends);
        System.out.println("Friend added: " + friend.toString());
    }

    public void remove(String name) {
        Iterator<Friend> iterator = friends.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Friend friend = iterator.next();
            if (friend.getName().equals(name)) {
                iterator.remove();
                found = true;
                System.out.println("Friend removed: " + friend.toString());
                phoneBookFileWriter.saveToFile(friends);
                break;
            }
        }
        if (!found) {
            System.out.println("Friend not found: " + name);
        }
    }

    public String findByName(String name) {
        for (Friend f : friends) {
            String fname= f.getName().toLowerCase();
            if (fname.equals(name)) {
                return f.toString();
            }
        }
        return null;
    }

    public void update(String name, long phoneNumber) {
        for (Friend f : friends) {
            if (name.equals(f.getName())) {
                f.setPhoneNumber(phoneNumber);
                phoneBookFileWriter.saveToFile(friends);
                System.out.println("Friend updated: " + f.toString());
                return;
            }
        }
        System.out.println("Friend not found: " + name);
    }

    public List<Friend> getFriends() {
        return friends;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        for (Friend f : friends) {
            result.append(f.toString()).append("<br>");
        }


        return result.toString();
    }
}