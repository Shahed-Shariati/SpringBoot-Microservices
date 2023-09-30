package com.webservices.restfulwebservices.service.impl;

import com.webservices.restfulwebservices.model.User;
import com.webservices.restfulwebservices.service.IUserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component

public class UserService implements IUserService {
    private static List<User> users = new ArrayList<>();
    private static Integer userCounts = 0;

    static {
        users.add(new User(++userCounts, "Shahed", LocalDate.now().minusYears(36)));
        users.add(new User(++userCounts, "Raha", LocalDate.now().minusYears(7)));
        users.add(new User(++userCounts, "Samrand", LocalDate.now().minusYears(3)));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findUserById(Integer id) {
       return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User save(User user) {
        user.setId(++userCounts);
        users.add(user);
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }


}
