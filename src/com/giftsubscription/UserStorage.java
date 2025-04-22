package com.giftsubscription;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    Map<String, User> users = new HashMap<>();
    public void addUser(User user) {
        users.put(user.getMail(), user);
    }
    public User getUser(String mail) {
        return users.get(mail);
    }
    public boolean containsMail(String mail) {
        return users.containsKey(mail);
    }
    public boolean containsNumber(String number) {
        for(User u : users.values()) {
            if (u.getNumber().equals(number)) return true;
        }
        return false;
    }
    public boolean containsPassword(String mail, String password) {
        if (!users.isEmpty() && users.get(mail) != null) {
            return users.get(mail).getPassword().equals(password);
        }
        return false;
    }
}
