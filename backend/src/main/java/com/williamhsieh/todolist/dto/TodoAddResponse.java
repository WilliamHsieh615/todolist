package com.williamhsieh.todolist.dto;

import lombok.Data;

@Data
public class TodoAddResponse {

    private Integer todoId;
    private String text;
    private Boolean completed;
}
