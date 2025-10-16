package com.williamhsieh.todolist.dto;

import lombok.Data;

import java.util.List;

@Data
public class TodoResponse {

    private List<DataItem> data;
}
