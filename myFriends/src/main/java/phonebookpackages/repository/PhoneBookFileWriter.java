package phonebookpackages.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import phonebookpackages.model.Friend;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookFileWriter {

    private Friend friend;
    private static final String FILE_NAME = "friends.json";
    private Gson gson;

    public PhoneBookFileWriter() {
        this.gson = new Gson();
    }

    public void saveToFile(List<Friend> friends) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(friends, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Friend> loadFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type friendListType = new TypeToken<List<Friend>>() {
                }.getType();
                List<Friend> friends = gson.fromJson(reader, friendListType);
                if (friends != null) {
                    return friends;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

}
