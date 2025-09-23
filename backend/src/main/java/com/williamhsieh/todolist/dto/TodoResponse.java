package com.williamhsieh.todolist.dto;

import java.util.List;

public class TodoResponse {

    private List<DataItem> data;

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }
}
