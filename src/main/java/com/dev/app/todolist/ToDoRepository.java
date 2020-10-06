package com.dev.app.todolist;

import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDoEntity, Integer> {
}
