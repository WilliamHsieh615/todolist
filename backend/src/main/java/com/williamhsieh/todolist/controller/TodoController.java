package com.williamhsieh.todolist.controller;

import com.williamhsieh.todolist.dto.TodoAddResponse;
import com.williamhsieh.todolist.dto.TodoRequest;
import com.williamhsieh.todolist.dto.TodoResponse;
import com.williamhsieh.todolist.model.Member;
import com.williamhsieh.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<TodoResponse> getTodos(@AuthenticationPrincipal Member member) {

        TodoResponse todoResponse = todoService.getTodos(member.getMemberId());

        return ResponseEntity.status(HttpStatus.OK).body(todoResponse);
    }

    @PostMapping("/todos")
    public ResponseEntity<TodoAddResponse> addTodo(@AuthenticationPrincipal Member member, @RequestBody @Valid TodoRequest todoRequest) {

        TodoAddResponse todoAddResponse = todoService.addTodo(member.getMemberId(), todoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(todoAddResponse);
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<?> removeTodo(@AuthenticationPrincipal Member member, @PathVariable Integer todoId) {

        todoService.deleteTodoByTodoIdAndMemberId(todoId, member.getMemberId());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/todos/{todoId}/toggle")
    public ResponseEntity<?> toggleTodo(@AuthenticationPrincipal Member member, @PathVariable Integer todoId) {

        todoService.toggleTodoStatus(todoId, member.getMemberId());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

