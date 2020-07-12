/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.rest.middleware;

import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import org.apache.commons.lang3.StringUtils;
import org.palosc.javalin.workshop.service.UserService;

/**
 *
 * @author tareq
 */
public class ContextUtils {

    private static final UserService userService = new UserService();

    public static String getTokenFromContext(Context ctx) {
        String authHeader = ctx.header("Authorization");// get header
        if (StringUtils.isBlank(authHeader) || !StringUtils.contains(authHeader, "Bearer")) {//chech format
            throw new UnauthorizedResponse();
        } else {
            return authHeader.substring(6).trim().strip();// extract token
        }
    }

    public static long getUserIdFromContext(Context ctx) {
        return userService.getUserIdForToken(ContextUtils.getTokenFromContext(ctx));
    }
}
