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
    
    public List<TodoItem> getAllItems(){
        return repo.getAllItems();
    }
    
    public TodoItem getItemById(long id){
        return repo.getItemById(id);
    }

    public void createTodoItem(TodoItem item){
        repo.createTodoItem(item);
    }

    public void updateTodoItem(TodoItem item, long itemId){
        repo.updateTodoItem(item, itemId);
    }

    public void deleteTodoItem(long id){
        repo.deleteTodoItem(id);
    }
}
