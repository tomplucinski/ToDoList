package com.dev.app.todolist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class ToDoController {

    private final ToDoRepository todoRepository;

    public ToDoController(ToDoRepository repository) {
        this.todoRepository = repository;
    }

    @PostMapping(path = "/todo")
    public ResponseEntity<ToDoEntity> addTodo(@RequestBody ToDoRequest todo) {

        ToDoEntity n = new ToDoEntity();
        n.setTodo(todo.getTodo());
        ToDoEntity savedTodo = todoRepository.save(n);
        return ResponseEntity.ok(savedTodo);
    }

    @GetMapping(path = "/todos")
    public ResponseEntity<Iterable<ToDoEntity>> getAllUsers() {
        Iterable<ToDoEntity> todos = todoRepository.findAll();
        return ResponseEntity.ok(todos);
    }
}
