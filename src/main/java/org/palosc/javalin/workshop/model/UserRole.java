/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.model;

import io.javalin.core.security.Role;

/**
 *
 * @author tareq
 */
public enum UserRole implements Role {
    NONE,USER,ADMIN
}
