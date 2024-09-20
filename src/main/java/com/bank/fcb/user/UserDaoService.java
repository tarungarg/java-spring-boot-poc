package com.bank.fcb.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<User>();

    static {
        users.add(new User("Raj", "1234", "raj@gmail.com", "1234567890", LocalDate.now()));
        users.add(new User("Raj1", "1234", "raj1@gmail.com", "1234567890", LocalDate.now()));
        users.add(new User("Raj2", "1234", "raj2@gmail.com", "1234567890", LocalDate.now()));
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUsersByName(String name) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public User createUser(User user) {
        users.add(user);
        return user;
    }

    // public static List<User> getUserById(String id) {
    //     return users.stream();
    // }

}
