/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.repository;

/**
 *
 * @author tareq
 */
import org.jdbi.v3.core.Jdbi;

public class JdbiManager {

    private static Jdbi jdbi;

    public static Jdbi get() {
        return JdbiManager.jdbi;
    }

    public static void set(Jdbi jdbi) {
        JdbiManager.jdbi = jdbi;
    }
}
