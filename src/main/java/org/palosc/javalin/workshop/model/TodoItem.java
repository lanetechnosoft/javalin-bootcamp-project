/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.palosc.javalin.workshop.model;

import java.util.Objects;

/**
 *
 * @author tareq
 */
public class TodoItem {
    
    private long itemId;
    private long userId;
    private String title;
    private String description;
    private boolean done = false;

    public TodoItem(long itemId, String title, String description) {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
    }

    public TodoItem() {
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.itemId ^ (this.itemId >>> 32));
        hash = 59 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TodoItem other = (TodoItem) obj;
        if (this.itemId != other.itemId) {
            return false;
        }
        return true;
    }
    
    
}
