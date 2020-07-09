/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.run;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import org.palosc.javalin.workshop.rest.TodoItemRestApi;

/**
 *
 * @author tareq
 */
public class Main {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        TodoItemRestApi todoItems = new TodoItemRestApi();
        app.routes(() -> {
            path("/todo", () -> {
                get(todoItems::get);
                post(todoItems::post);
                path(":id", () -> {
                    get(todoItems::getById);
                    put(todoItems::put);
                    delete(todoItems::delete);
                });
            });
        });
       app.config.addStaticFiles("/webapp");
    }
}
