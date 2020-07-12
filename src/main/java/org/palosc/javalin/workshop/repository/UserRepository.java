/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.repository;

import java.util.List;
import org.palosc.javalin.workshop.model.User;

/**
 *
 * @author tareq
 */
public class UserRepository {

    private static final String LIST_USERS = "SELECT * FROM TodoUser";
    private static final String GET_USER_BY_USERNAME = "SELECT * From TodoUser WHERE username=:username";
    private static final String INSERT_USER = "INSERT INTO TodoUser (username,password,role) VALUES (:username,:password,:role)";


    public List<User> getAllUsers() {
        return JdbiManager.get().withHandle(handle -> handle.createQuery(LIST_USERS)
                .mapToBean(User.class)
                .list());
    }

    public User getUserByUsername(String username) {
        return JdbiManager.get().withHandle(handle -> handle.createQuery(GET_USER_BY_USERNAME)
                .bind("username", username)
                .mapToBean(User.class)
                .one());
    }

    public void createUser(User user) {
        JdbiManager.get().useHandle(handle -> handle.createUpdate(INSERT_USER)
                .bind("username", user.getUsername())
                .bind("password", user.getPassword())
                .bind("role", user.getRole())
                .execute());
    }

}
