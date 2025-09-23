package com.williamhsieh.todolist.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoRequest {

    @NotBlank
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
