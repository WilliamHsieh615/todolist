package com.williamhsieh.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TodoRequest {

    @NotBlank
    private String text;
}
