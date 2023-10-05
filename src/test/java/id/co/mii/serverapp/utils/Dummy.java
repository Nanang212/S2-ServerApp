package id.co.mii.serverapp.utils;

import java.util.ArrayList;
import java.util.List;

import id.co.mii.serverapp.models.User;

public class Dummy {
    
    public static List<User> createUser(){
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setUsername("test1");
        user1.setPassword("test1");

        User user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("test2");

        users.add(user1);
        users.add(user2);
        return users;
    }
}
