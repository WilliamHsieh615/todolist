package com.williamhsieh.todolist.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String nickname;
    private LocalDate birthday;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
