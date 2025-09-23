package com.williamhsieh.todolist.dao;

import com.williamhsieh.todolist.model.Todo;
import com.williamhsieh.todolist.rowmapper.TodoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TodoDaoImpl implements TodoDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Todo> getTodosByMemberId(Integer memberId) {

        String sql = "SELECT todo_id, member_id, text, completed, created_date, last_modified_date " +
                "FROM todos WHERE member_id = :memberId";

        Map<String, Object> map = new HashMap<>();

        map.put("memberId", memberId);

        List<Todo> todos = namedParameterJdbcTemplate.query(sql, map, new TodoRowMapper());

        return todos;
    }

    @Override
    public Integer addTodo(Todo todo) {

        String sql = "INSERT INTO todos(member_id, text, completed, " +
            "created_date, last_modified_date) " +
            "VALUES (:member_id, :text, :completed, " +
            ":created_date, :last_modified_date) ";

        Map<String, Object> map = new HashMap<>();
        map.put("member_id", todo.getMemberId());
        map.put("text", todo.getText());
        map.put("completed", todo.getCompleted());

        LocalDateTime now = LocalDateTime.now();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int todoId = keyHolder.getKey().intValue();

        return todoId;
    }

    @Override
    public void deleteTodoByTodoIdAndMemberId(Integer todoId, Integer memberId) {

        String sql = "DELETE FROM todos WHERE todo_id = :todoId AND member_id = :memberId";

        Map<String, Object> map = new HashMap<>();
        map.put("todoId", todoId);
        map.put("memberId", memberId);

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void toggleTodoStatus(Integer todoId, Integer memberId) {

        String sql = "UPDATE todos SET completed = NOT completed WHERE todo_id = :todoId AND member_id = :memberId";

        Map<String, Object> map = new HashMap<>();
        map.put("todoId", todoId);
        map.put("memberId", memberId);

        namedParameterJdbcTemplate.update(sql, map);

    }
}

