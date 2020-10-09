package com.dev.app.todolist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ToDoController {

    private final ToDoRepository toDoRepository;

    public ToDoController(ToDoRepository repository) {
        this.toDoRepository = repository;
    }

    @PostMapping("/todo")
    public ResponseEntity<ToDoEntity> addTodo(@RequestBody ToDoRequest todo) {

        ToDoEntity n = new ToDoEntity();
        n.setTodo(todo.getTodo());
        ToDoEntity savedTodo = toDoRepository.save(n);
        return ResponseEntity.ok(savedTodo);
    }

    @GetMapping("/todos")
    public ResponseEntity<Iterable<ToDoEntity>> getAllUsers() {
        Iterable<ToDoEntity> todos = toDoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<ToDoEntity> updateTodo(@PathVariable Integer id, @RequestBody ToDoRequest request) {
        Optional<ToDoEntity> todo = toDoRepository.findById(id);
        todo.get().setTodo(request.getTodo());
        ToDoEntity updatedTodo = toDoRepository.save(todo.get());
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id) {
        Optional<ToDoEntity> todo = toDoRepository.findById(id);
        toDoRepository.delete(todo.get());
        return ResponseEntity.ok("Successfully deleted todo");
    }
}
