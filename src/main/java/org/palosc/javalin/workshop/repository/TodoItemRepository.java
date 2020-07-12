/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.palosc.javalin.workshop.model.TodoItem;

/**
 *
 * @author tareq
 */
public class TodoItemRepository {
 
    private static final Map<Long,TodoItem> MAP = new HashMap<>();
    private static long COUNTER = 0;

    private static long getNextId(){
        COUNTER = COUNTER +1;
        return COUNTER;
    }
    
    public List<TodoItem> getAllItems(){
        return new ArrayList<>(MAP.values());
    }
    
    public TodoItem getItemById(long id){
        return MAP.getOrDefault(id, null);
    }
    
    public void createTodoItem(TodoItem todo){
        todo.setItemId(getNextId());
        MAP.put(todo.getItemId(), todo);
    }
    
    public void updateTodoItem(TodoItem todo, long id){
        todo.setItemId(id);
        MAP.put(id, todo);
    }
    
    public void deleteTodoItem(long id){
        MAP.remove(id);
    }

    public List<TodoItem> findUserItems(long userId) {
        return MAP.values().stream().filter(item-> item.getUserId() == userId).collect(Collectors.toList());
    }

    public TodoItem getUserItemById(Long itemId, long userId) {
        return MAP.values().stream().filter(item -> item.getUserId() == userId && item.getItemId() == itemId).findFirst().orElse(null);
    }

    public void updateUserItemById(Long itemId, TodoItem item, long userId) {
        if(getUserItemById(itemId,userId) !=null){
            item.setUserId(userId);
            this.updateTodoItem(item,itemId);
        }
    }

    public void deleteUserItemById(Long itemId, long userId) {
        if(getUserItemById(itemId,userId)!=null){
            deleteTodoItem(itemId);
        }
    }
}
