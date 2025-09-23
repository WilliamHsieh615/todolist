package com.williamhsieh.todolist.rowmapper;

import com.williamhsieh.todolist.model.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TodoRowMapper implements RowMapper<Todo> {

    @Override
    public Todo mapRow(ResultSet resultSet, int i) throws SQLException {

        Todo todo = new Todo();
        todo.setTodoId(resultSet.getInt("todo_id"));
        todo.setMemberId(resultSet.getInt("member_id"));
        todo.setText(resultSet.getString("text"));
        todo.setCompleted(resultSet.getBoolean("completed"));
        todo.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
        todo.setLastModifiedDate(resultSet.getTimestamp("last_modified_date").toLocalDateTime());

        return todo;
    }
}
