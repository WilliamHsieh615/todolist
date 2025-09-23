package com.williamhsieh.todolist.service;

import com.williamhsieh.todolist.dao.TodoDao;
import com.williamhsieh.todolist.dto.DataItem;
import com.williamhsieh.todolist.dto.TodoAddResponse;
import com.williamhsieh.todolist.dto.TodoRequest;
import com.williamhsieh.todolist.dto.TodoResponse;
import com.williamhsieh.todolist.model.Member;
import com.williamhsieh.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    @Override
    public TodoResponse getTodos(Integer memberId) {

        List<Todo> todos = todoDao.getTodosByMemberId(memberId);

        List<DataItem> dataItems = todos.stream().map(todo -> {
            DataItem item = new DataItem();
            item.setItemId(todo.getTodoId());
            item.setText(todo.getText());
            item.setCompleted(todo.getCompleted());
            return item;
        }).collect(Collectors.toList());

        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setData(dataItems);

        return todoResponse;
    }

    @Override
    public TodoAddResponse addTodo(Integer memberId, TodoRequest todoRequest) {

        Todo todo = new Todo();
        todo.setMemberId(memberId);
        todo.setText(todoRequest.getText());
        todo.setCompleted(false);

        Integer todoId = todoDao.addTodo(todo);

        TodoAddResponse todoAddResponse = new TodoAddResponse();
        todoAddResponse.setTodoId(todoId);
        todoAddResponse.setText(todo.getText());
        todoAddResponse.setCompleted(todo.getCompleted());

        return todoAddResponse;
    }

    @Override
    public void deleteTodoByTodoIdAndMemberId(Integer todoId, Integer memberId) {
        todoDao.deleteTodoByTodoIdAndMemberId(todoId, memberId);
    }

    @Override
    public void toggleTodoStatus(Integer todoId, Integer memberId) {
        todoDao.toggleTodoStatus(todoId, memberId);
    }
}
