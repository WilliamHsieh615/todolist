package com.williamhsieh.todolist.dto;

import lombok.Data;

@Data
public class TokenData {

    private String token;
    private long exp;
}
