package com.williamhsieh.todolist.service;

import com.williamhsieh.todolist.dto.*;
import com.williamhsieh.todolist.model.Member;

public interface MemberService {

    Member getMemberById(Integer memberId);

    Integer register(MemberRegisterRequest memberRegisterRequest);

    MemberLoginResponse login(MemberLoginRequest memberLoginRequest);

    MemberVerifyResponse getMemberVerify(MemberVerifyRequest  memberVerifyRequest);

    void resetPassword(MemberRetrieveRequest  memberRetrieveRequest);
}
