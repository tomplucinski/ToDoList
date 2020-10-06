package com.dev.app.todolist;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ToDoEntityTest {

    @Test
    public void toDoEntityIsCreated() throws Exception {
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setTodo("This is a new todo");

        assertThat(toDoEntity.getTodo()).isEqualTo("This is a new todo");
    }

    @Test
    public void toDoRequestIsValid() throws Exception {
        ToDoRequest toDoRequest = new ToDoRequest();
        toDoRequest.setTodo("Incoming todo");

        assertThat(toDoRequest.getTodo()).isEqualTo("Incoming todo");
    }
}
