package com.subhadeep.rest.webservices.restfulwebservices.service;

import com.subhadeep.rest.webservices.restfulwebservices.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;
    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    public List<User> findAll() {
        return Collections.unmodifiableList(users);
    }

    public User save(User user) {
        if(user.getId()==null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(Integer id) {
        for(User user : users) {
            if(user.getId() == id)
                return user;
        }
        return null;
    }

    public User deleteById(Integer id) {
        Iterator<User> userIterator = users.iterator();
        while(userIterator.hasNext()) {
            User user = userIterator.next();
            if(user.getId() == id) {
                userIterator.remove();
                return user;
            }
        }
        return null;
    }
}
