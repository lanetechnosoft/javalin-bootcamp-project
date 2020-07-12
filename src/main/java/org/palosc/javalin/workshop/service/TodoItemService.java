/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.service;

import java.util.List;
import org.palosc.javalin.workshop.model.TodoItem;
import org.palosc.javalin.workshop.repository.TodoItemRepository;

/**
 *
 * @author tareq
 */
public class TodoItemService {

    private final TodoItemRepository repo = new TodoItemRepository();

    public List<TodoItem> getAllItems() {
        return repo.getAllItems();
    }

    public TodoItem getItemById(long id) {
        return repo.getItemById(id);
    }

    public void createTodoItem(TodoItem item) {
        repo.createTodoItem(item);
    }

    public void updateTodoItem(TodoItem item, long itemId) {
        repo.updateTodoItem(item, itemId);
    }

    public void deleteTodoItem(long id) {
        repo.deleteTodoItem(id);
    }

    public Object getAllItemsForUser(long userIdForToken) {
        return repo.findUserItems(userIdForToken);
    }

    public TodoItem getItemByIdForUser(Long itemId, long userId) {
        return repo.getUserItemById(itemId,userId);
    }

    public void createTodoItemForUser(TodoItem item, long userId) {
        item.setUserId(userId);
        createTodoItem(item);
    }

    public void updateTodoItemForUser(TodoItem item, Long itemId, long userId) {
        repo.updateUserItemById(itemId, item ,userId);
    }

    public void deleteTodoItemForUser(Long itemId, long userId) {
        repo.deleteUserItemById(itemId,userId);
    }
}
