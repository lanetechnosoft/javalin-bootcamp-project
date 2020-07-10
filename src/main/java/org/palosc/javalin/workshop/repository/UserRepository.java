/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.palosc.javalin.workshop.model.User;

/**
 *
 * @author tareq
 */
public class UserRepository {
    private static final Map<Long,User> USERS = new HashMap<>();
    private static long COUNTER = 0;

    private static long getNextId(){
        COUNTER = COUNTER +1;
        return COUNTER;
    }
    
    public List<User> getAllUsers(){
        return new ArrayList<>(USERS.values());
    }
    public User getUserByUsername(String username){
        return USERS.values().stream()
                .filter(user-> StringUtils.equals(username, user.getUsername()))
                .findFirst().orElse(null);
    }
    
    public void createUser(User user){
        user.setUserId(getNextId());
        USERS.put(user.getUserId(), user);
    }
    
    
}
