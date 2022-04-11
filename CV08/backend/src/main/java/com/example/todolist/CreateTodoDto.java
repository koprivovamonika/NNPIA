package com.example.todolist;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateTodoDto {
    @NotBlank(message = "Name is mandatory.")
    private String name;
}
