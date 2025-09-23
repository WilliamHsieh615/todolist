package com.williamhsieh.todolist.service;

import com.williamhsieh.todolist.dto.TodoAddResponse;
import com.williamhsieh.todolist.dto.TodoRequest;
import com.williamhsieh.todolist.dto.TodoResponse;
import com.williamhsieh.todolist.model.Member;

public interface TodoService {

    TodoResponse getTodos(Integer memberId);

    TodoAddResponse addTodo(Integer memberId, TodoRequest todoRequest);

    void deleteTodoByTodoIdAndMemberId(Integer todoId, Integer memberId);

    void toggleTodoStatus(Integer todoId, Integer memberId);

}
