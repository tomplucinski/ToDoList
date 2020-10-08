package com.dev.app.todolist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ToDoController {

    private final ToDoRepository todoRepository;

    public ToDoController(ToDoRepository repository) {
        this.todoRepository = repository;
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDoEntity> addTodo(@RequestBody ToDoRequest todo) {

        ToDoEntity n = new ToDoEntity();
        n.setTodo(todo.getTodo());
        ToDoEntity savedTodo = todoRepository.save(n);
        return ResponseEntity.ok(savedTodo);
    }

    @GetMapping("/todos")
    public ResponseEntity<Iterable<ToDoEntity>> getAllUsers() {
        Iterable<ToDoEntity> todos = todoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<ToDoEntity> updateTodo(@PathVariable String todoId, @RequestBody ToDoRequest todo) {
        //finish tomorrow
    }
}
