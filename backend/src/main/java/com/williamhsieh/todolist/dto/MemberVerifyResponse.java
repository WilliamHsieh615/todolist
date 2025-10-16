package com.williamhsieh.todolist.dto;

import lombok.Data;

@Data
public class MemberVerifyResponse {

    private String token;
    private long exp;
    private Integer memberId;
}
