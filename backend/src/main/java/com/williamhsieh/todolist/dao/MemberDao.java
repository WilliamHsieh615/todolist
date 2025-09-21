package com.williamhsieh.todolist.dao;

import com.williamhsieh.todolist.dto.MemberRegisterRequest;
import com.williamhsieh.todolist.model.Member;

public interface MemberDao {

    Member getMemberById(Integer memberId);

    Member getMemberByEmail(String email);

    Integer createMember(MemberRegisterRequest memberRegisterRequest);

    void updateMemberPassword(Integer memberId, String hashedPassword);

}
