package com.williamhsieh.todolist.service;

import com.williamhsieh.todolist.Repository.MemberRepository;
import com.williamhsieh.todolist.Repository.TodoRepository;
import com.williamhsieh.todolist.dto.DataItem;
import com.williamhsieh.todolist.dto.TodoAddResponse;
import com.williamhsieh.todolist.dto.TodoRequest;
import com.williamhsieh.todolist.dto.TodoResponse;
import com.williamhsieh.todolist.model.Member;
import com.williamhsieh.todolist.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public TodoResponse getTodos(Integer memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member 不存在"));

        List<Todo> todos = todoRepository.getTodoByMember(member);

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

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "會員不存在"));

        Todo todo = new Todo();
        todo.setMember(member);
        todo.setText(todoRequest.getText());
        todo.setCompleted(false);
        LocalDateTime now = LocalDateTime.now();
        todo.setCreatedDate(now);
        todo.setLastModifiedDate(now);

        Integer todoId = todoRepository.save(todo).getTodoId();

        TodoAddResponse todoAddResponse = new TodoAddResponse();
        todoAddResponse.setTodoId(todoId);
        todoAddResponse.setText(todo.getText());
        todoAddResponse.setCompleted(todo.getCompleted());

        return todoAddResponse;
    }

    @Override
    public void deleteTodoByTodoIdAndMemberId(Integer todoId, Integer memberId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo 不存在"));

        if (!todo.getMember().getMemberId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "無權限操作");
        }

        todoRepository.deleteById(todoId);

    }

    @Override
    public void toggleTodoStatus(Integer todoId, Integer memberId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo 不存在"));

        if (!todo.getMember().getMemberId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "無權限操作");
        }

        todo.setCompleted(!todo.getCompleted());

        todoRepository.save(todo);
    }
}
