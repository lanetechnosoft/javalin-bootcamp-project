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
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.palosc.javalin.workshop.model.TodoItem;

/**
 *
 * @author tareq
 */
public class TodoItemRepository {

    public static String GET_ALL_ITEMS = "SELECT * FROM TodoItem";
    public static String GET_ITEM_BY_ID = "SELECT * from TodoItem WHERE itemId = :itemId";
    public static String CREATE_ITEM = "INSERT INTO TodoItem (title,description,done,userId) VALUES (:title,:description,:done,:userId)";
    public static String UPDATE_ITEM = "UPDATE TodoItem SET title=:title, description=:description,done=:done WHERE itemId= :itemId";
    public static String DELETE_ITEM = "DELETE TodoItem WHERE itemId=:itemId";
    public static String GET_USER_ITEMS = "SELECT * from TodoItem WHERE userId=:userId";
    public static String GET_USER_ITEM_BY_ID = "SELECT * FROM Todoitem WHERE itemId=:itemId AND userId=:userId";

    static {
        JdbiManager.get().registerRowMapper(BeanMapper.factory(TodoItem.class));
    }

    public List<TodoItem> getAllItems() {
        return JdbiManager.get().withHandle((handle)
                -> handle.createQuery(GET_ALL_ITEMS)
                        .mapTo(TodoItem.class)
                        .list());
    }

    public TodoItem getItemById(long id) {
        return JdbiManager.get().withHandle(handle -> handle.createQuery(GET_ITEM_BY_ID)
                .bind("itemId", id)
                .mapTo(TodoItem.class)
                .one());
    }

    public void createTodoItem(TodoItem todo) {
        JdbiManager.get().useHandle(handle -> handle.createUpdate(CREATE_ITEM)
                .bind("title", todo.getTitle())
                .bind("description", todo.getDescription())
                .bind("done", todo.isDone())
                .bind("userId",todo.getUserId())
                .execute());
    }

    public void updateTodoItem(TodoItem todo, long id) {
        JdbiManager.get().useHandle(handle -> handle.createUpdate(UPDATE_ITEM)
                .bind("title", todo.getTitle())
                .bind("description", todo.getDescription())
                .bind("done", todo.isDone())
                .bind("itemId", todo.getItemId())
                .execute());
    }

    public void deleteTodoItem(long id) {
        JdbiManager.get().useHandle(handle -> handle.createUpdate(DELETE_ITEM)
                .bind("itemId", id)
                .execute());
    }

    public List<TodoItem> findUserItems(long userId) {
        return JdbiManager.get().withHandle(handle
                -> handle.createQuery(GET_USER_ITEMS)
                        .bind("userId", userId)
                        .mapTo(TodoItem.class)
                        .list());
    }

    public TodoItem getUserItemById(Long itemId, long userId) {
        return JdbiManager.get().withHandle(handle -> handle.createQuery(GET_USER_ITEM_BY_ID)
                .bind("itemId", itemId)
                .bind("userId", userId)
                .mapTo(TodoItem.class)
                .one());
    }

    public void updateUserItemById(Long itemId, TodoItem item, long userId) {
        if (getUserItemById(itemId, userId) != null) {
            item.setUserId(userId);
            this.updateTodoItem(item, itemId);
        }
    }

    public void deleteUserItemById(Long itemId, long userId) {
        if (getUserItemById(itemId, userId) != null) {
            deleteTodoItem(itemId);
        }
    }
}
