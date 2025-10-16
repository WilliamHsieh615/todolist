package com.williamhsieh.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberRetrieveRequest {

    @NotBlank
    private String token;

    @NotNull
    private Integer memberId;

    @NotBlank
    private String password;
}
