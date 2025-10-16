package com.williamhsieh.todolist.dto;

import lombok.Data;

@Data
public class MemberLoginResponse {

    private Integer memberId;
    private String email;
    private String nickname;
}
