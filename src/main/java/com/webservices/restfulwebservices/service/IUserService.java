package com.webservices.restfulwebservices.service;

import com.webservices.restfulwebservices.model.User;

import java.util.List;

public interface IUserService {
     List<User> findAll();
     User findUserById(Integer id);
     User save(User user);
     void deleteById(Integer id);
}
