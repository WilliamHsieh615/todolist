package com.williamhsieh.todolist.repository;

import com.williamhsieh.todolist.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Integer> {

    Member findByEmail(String email);

}
