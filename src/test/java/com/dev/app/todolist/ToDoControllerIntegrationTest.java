package com.dev.app.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ToDoControllerIntegrationTest {

    @LocalServerPort
    private int port;
    private String host = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ToDoRepository toDoRepository;

    @Test
    public void addTodoTest() throws Exception {
        ToDoRequest request = new ToDoRequest();
        request.setTodo("A new todo item");

        assertThat(this.restTemplate.postForObject(this.host + this.port + "/api/todo",
                request, String.class))
                .isEqualTo("{\"id\":1,\"todo\":\"A new todo item\"}");
    }

    @Test
    public void getAllTodosTest() throws Exception {
        ToDoEntity request1 = new ToDoEntity();
        request1.setTodo("First todo");
        ToDoEntity request2 = new ToDoEntity();
        request2.setTodo("Second todo");
        ToDoEntity request3 = new ToDoEntity();
        request3.setTodo("Third todo");

        toDoRepository.save(request1);
        toDoRepository.save(request2);
        toDoRepository.save(request3);

        assertThat(this.restTemplate.getForObject(this.host + this.port + "/api/todos",
                String.class))
                .isEqualTo("[{\"id\":1,\"todo\":\"First todo\"},{\"id\":2,\"todo\":\"Second todo\"},{\"id\":3,\"todo\":\"Third todo\"}]");
    }

    @Test
    public void updateTodoTest() throws Exception {
        ToDoEntity request1 = new ToDoEntity();
        request1.setTodo("First one");
        ToDoEntity firstSaved = toDoRepository.save(request1);

        ToDoEntity request2 = new ToDoEntity();
        request2.setTodo("Second one");
        ToDoEntity secondSaved = toDoRepository.save(request2);

        ToDoRequest updateRequest = new ToDoRequest();
        updateRequest.setTodo("this is my updated todo");

        this.restTemplate.put(this.host + this.port + "/api/todos/1", updateRequest);

        assertThat(this.restTemplate.getForObject(this.host + this.port + "/api/todos",
                String.class))
                .isEqualTo("[{\"id\":1,\"todo\":\"this is my updated todo\"},{\"id\":2,\"todo\":\"Second one\"}]");
    }

    @Test
    public void deleteTodoTest() throws Exception {
        ToDoEntity todo  = new ToDoEntity();
        todo.setTodo("this is my todo");
        toDoRepository.save(todo);

        this.restTemplate.delete(this.host + this.port + "/api/todos/1");

        assertThat(this.restTemplate.getForObject(this.host + this.port + "/api/todos",
                String.class)).isEqualTo("[]");
    }

    @Test
    public void getTodoByIdTest() throws Exception {
        ToDoEntity todo = new ToDoEntity();
        todo.setTodo("first todo");
        toDoRepository.save(todo);

        ToDoEntity todo2 = new ToDoEntity();
        todo2.setTodo("second todo");
        toDoRepository.save(todo2);

        assertThat(this.restTemplate.getForObject(this.host + this.port + "/api/todos/2",
                String.class)).isEqualTo("{\"id\":2,\"todo\":\"second todo\"}");
    }
}
