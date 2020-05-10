package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    User insertUser(User user);
}
