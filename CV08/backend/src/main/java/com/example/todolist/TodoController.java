package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TodoController {
    @Autowired
    TodoService todoService;

    @GetMapping("/todos/all")
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }

    @PostMapping("/todos")
    public TodoDto createTodo(@RequestBody @Valid CreateTodoDto createTodoDto){
        return todoService.createTodo(createTodoDto);
    }

    @GetMapping("/todos")
    public Page<Todo> getTasksByPage(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "3") Integer sizeOfPage, @RequestParam(defaultValue = "id") String sortBy) {
        return todoService.getTodosByPage(pageNumber, sizeOfPage, sortBy);
    }

    @DeleteMapping("/todos/{taskId}")
    public void removeTask(@RequestBody @Valid @PathVariable(required = false) Long taskId) {
        todoService.deleteTodo(taskId);
    }

}
