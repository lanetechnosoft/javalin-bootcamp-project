/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.rest;

import io.javalin.http.Context;
import org.palosc.javalin.workshop.model.TodoItem;
import org.palosc.javalin.workshop.rest.middleware.ContextUtils;
import org.palosc.javalin.workshop.service.TodoItemService;
import org.palosc.javalin.workshop.service.UserService;

/**
 *
 * @author tareq
 */
public class TodoItemRestApi {

    private final TodoItemService service = new TodoItemService();
    public void get(Context ctx) {
        ctx.json(service.getAllItemsForUser(ContextUtils.getUserIdFromContext(ctx)));
    }

    public void getById(Context ctx) {
        TodoItem found = service.getItemByIdForUser(ctx.pathParam("id", Long.class).get(),ContextUtils.getUserIdFromContext(ctx));
        if (found != null) {
            ctx.json(found);
        } else {
            ctx.status(404);
        }
    }

    public void post(Context ctx) {
        service.createTodoItemForUser(ctx.bodyAsClass(TodoItem.class),ContextUtils.getUserIdFromContext(ctx));
        ctx.status(200);
    }

    public void put(Context ctx) {
        service.updateTodoItemForUser(ctx.bodyAsClass(TodoItem.class), ctx.pathParam("id", Long.class).get(),ContextUtils.getUserIdFromContext(ctx));
        ctx.status(200);
    }

    public void delete(Context ctx) {
        service.deleteTodoItemForUser(ctx.pathParam("id", Long.class).get(),ContextUtils.getUserIdFromContext(ctx));
        ctx.status(200);
    }
}
