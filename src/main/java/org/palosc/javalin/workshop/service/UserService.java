/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.palosc.javalin.workshop.model.User;
import org.palosc.javalin.workshop.repository.UserRepository;

/**
 *
 * @author tareq
 */
public class UserService {

    UserRepository repository = new UserRepository();
    private static final Map<String, User> TOKENS = new HashMap<>();

    public User getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }
    
    public long getUserIdForToken(String token){
        User found = TOKENS.get(token);
        if(found!=null){
            return found.getUserId();
        }
        return -1l;
    }

    public boolean authenticate(String username, String password) {
        User u = repository.getUserByUsername(username);
        if (u != null) {
            return StringUtils.equals(u.getPassword(), password);
        }
        return false;
    }

    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    public void createUser(User user) {
        repository.createUser(user);
    }

    public void addToken(String token, User user) {
        TOKENS.put(token, user);
    }

    public User validateToken(String token) {
        return TOKENS.getOrDefault(token, null);
    }
}
