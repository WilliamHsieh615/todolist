package com.williamhsieh.todolist.dto;

import lombok.Data;

@Data
public class DataItem {

    private Integer itemId;
    private String text;
    private Boolean completed;
}
