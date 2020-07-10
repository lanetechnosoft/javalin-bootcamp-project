/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.rest;

import io.javalin.http.Context;
import org.palosc.javalin.workshop.model.TodoItem;
import org.palosc.javalin.workshop.service.TodoItemService;

/**
 *
 * @author tareq
 */
public class TodoItemRestApi {

    private final TodoItemService service = new TodoItemService();

    public void get(Context ctx) {
        ctx.json(service.getAllItems());
    }

    public void getById(Context ctx) {
        TodoItem found = service.getItemById(ctx.pathParam("id", Long.class).get());
        if (found != null) {
            ctx.json(found);
        } else {
            ctx.status(404);
        }

    }

    public void post(Context ctx) {
        service.createTodoItem(ctx.bodyAsClass(TodoItem.class));
        ctx.status(200);
    }

    public void put(Context ctx) {
        service.updateTodoItem(ctx.bodyAsClass(TodoItem.class), ctx.pathParam("id", Long.class).get());
        ctx.status(200);
    }

    public void delete(Context ctx) {
        service.deleteTodoItem(ctx.pathParam("id", Long.class).get());
        ctx.status(200);
    }
}
