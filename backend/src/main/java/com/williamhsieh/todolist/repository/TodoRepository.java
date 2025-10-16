package com.williamhsieh.todolist.repository;

import com.williamhsieh.todolist.model.Member;
import com.williamhsieh.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> getTodoByMember(Member member);

}
