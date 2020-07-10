/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.rest;

import io.javalin.http.Context;
import org.apache.commons.lang3.RandomStringUtils;
import org.palosc.javalin.workshop.model.LoginRequest;
import org.palosc.javalin.workshop.model.LoginResponse;
import org.palosc.javalin.workshop.model.User;
import org.palosc.javalin.workshop.service.UserService;

/**
 *
 * @author tareq
 */
public class AuthenticationRestApi {

    UserService service = new UserService();

    public void signup(Context ctx) {
        User toCreate = ctx.bodyAsClass(User.class);
        service.createUser(toCreate);
    }

    public void listUsers(Context ctx) {
        ctx.json(service.getAllUsers());
    }

    public void login(Context ctx) {
        LoginRequest loginRequest = ctx.bodyAsClass(LoginRequest.class);
        boolean isAuth = service.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (isAuth) {
            LoginResponse response = new LoginResponse();
            String token = RandomStringUtils.randomAlphanumeric(30);
            service.addToken(token, service.getUserByUsername(loginRequest.getUsername()));
            response.setToken(token);
            ctx.json(response);
        } else {
            ctx.status(401);
        }
    }
}
