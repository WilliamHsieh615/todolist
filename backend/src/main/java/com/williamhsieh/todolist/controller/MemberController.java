package com.williamhsieh.todolist.controller;

import com.williamhsieh.todolist.dto.*;
import com.williamhsieh.todolist.model.Member;
import com.williamhsieh.todolist.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/members/register")
    public ResponseEntity<Member> register(@RequestBody @Valid MemberRegisterRequest memberRegisterRequest) {

        Integer memberId = memberService.register(memberRegisterRequest);

        Member member = memberService.getMemberById(memberId);

        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @PostMapping("/members/login")
    public ResponseEntity<MemberLoginResponse> login(@RequestBody @Valid MemberLoginRequest memberLoginRequest) {

        MemberLoginResponse memberLoginResponse = memberService.login(memberLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(memberLoginResponse);

    }

    @PostMapping("/members/verify")
    public ResponseEntity<MemberVerifyResponse> verify(@RequestBody @Valid MemberVerifyRequest memberVerifyRequest) {

        MemberVerifyResponse memberVerifyResponse = memberService.getMemberVerify(memberVerifyRequest);

        return ResponseEntity.status(HttpStatus.OK).body(memberVerifyResponse);

    }

    @PostMapping("/members/retrieve")
    public ResponseEntity<?> retrieve(@RequestBody @Valid MemberRetrieveRequest memberRetrieveRequest) {

        memberService.resetPassword(memberRetrieveRequest);

        return ResponseEntity.status(HttpStatus.OK).body("密碼變更成功");
    }

}
