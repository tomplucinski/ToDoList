package com.dev.app.todolist;

import org.springframework.lang.NonNull;

public class ToDoRequest {

    @NonNull
    private String todo;

    public String getTodo() {
        return todo;
    }

    public void setTodo(@NonNull String todo) {
        this.todo = todo;
    }
}
