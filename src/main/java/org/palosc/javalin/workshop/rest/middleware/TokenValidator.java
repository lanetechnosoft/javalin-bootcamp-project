/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.rest.middleware;

import io.javalin.core.security.Role;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.palosc.javalin.workshop.model.User;
import org.palosc.javalin.workshop.model.UserRole;
import org.palosc.javalin.workshop.service.UserService;

/**
 *
 * @author tareq
 */
public class TokenValidator {

    UserService service = new UserService();

    public void validate(Handler handler, Context ctx, Set<Role> permittedRoles) throws Exception {
        if (permittedRoles.isEmpty() || permittedRoles.contains(UserRole.NONE)) {// empty and none role, dont validate
            handler.handle(ctx);//continue request
        } else {
            String token = getTokenFromContext(ctx);// get token from Header
            User foundUser = service.validateToken(token);//validate token
            if (foundUser == null) {
                throw new UnauthorizedResponse();//if token isnt valid, throw error
            } else {
                if (permittedRoles.contains(foundUser.getRole())) {//check if user has role in permitted roles for endpoint
                    handler.handle(ctx);//continue request
                } else {
                    throw new ForbiddenResponse();//throw 403 if user has worng role
                }
            }
        }
    }

    private String getTokenFromContext(Context ctx) {
        String authHeader = ctx.header("Authorization");// get header
        if (StringUtils.isBlank(authHeader) || !StringUtils.contains(authHeader, "Bearer")) {//chech format
            throw new UnauthorizedResponse();
        } else {
            return authHeader.substring(6).trim().strip();// extract token
        }
    }
}
