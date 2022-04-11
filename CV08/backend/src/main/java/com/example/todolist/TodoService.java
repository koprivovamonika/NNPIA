package com.example.todolist;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    private final ObjectMapper objectMapper;

    public TodoService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    public TodoDto createTodo(CreateTodoDto createTodoDto) {
        Todo todo = objectMapper.convertValue(createTodoDto, Todo.class);

        Todo save = todoRepository.save(todo);
        return objectMapper.convertValue(save, TodoDto.class);
    }

    public void deleteTodo(Long taskId) {
        todoRepository.deleteById(taskId);
    }

    public Page<Todo> getTodosByPage(Integer pageNumber, Integer sizeOfPage, String sortBy) {
        PageRequest of = PageRequest.of(pageNumber, sizeOfPage, Sort.Direction.ASC, sortBy);
        return todoRepository.findAll(of);
    }

}
