package com.williamhsieh.todolist.dao;

import com.williamhsieh.todolist.model.Todo;

import java.util.List;

public interface TodoDao {

    List<Todo>  getTodosByMemberId(Integer memberId);

    Integer addTodo(Todo todo);

    void deleteTodoByTodoIdAndMemberId(Integer todoId, Integer memberId);

    void toggleTodoStatus(Integer todoId, Integer memberId);

}
