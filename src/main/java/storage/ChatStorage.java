package storage;

import beans.User;
import beans.Message;
import java.util.*;

public class ChatStorage {
    private static final Set<String> users = Collections.synchronizedSet(new HashSet<String>());
    private static final List<Message> messages = Collections.synchronizedList(new ArrayList<Message>());

    public static void addUser(String username) {
        users.add(username);
    }
    public static Set<String> getUsers() {
        return users;
    }

    public static void addMessage(Message msg) {
        messages.add(msg);
    }
    public static List<Message> getMessages() {
        return messages;
    }
}