package com.williamhsieh.todolist.service;

import com.williamhsieh.todolist.Repository.MemberRepository;
import com.williamhsieh.todolist.dto.*;
import com.williamhsieh.todolist.model.Member;
import com.williamhsieh.todolist.util.JwtUtil;
import com.williamhsieh.todolist.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Component
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Member getMemberById(Integer memberId) {

        Member member = memberRepository.findById(memberId).orElse(null);

        return member;
    }

    @Override
    public Integer register(MemberRegisterRequest memberRegisterRequest) {

        // 判斷 email 是否被註冊過
        Member existing = memberRepository.findByEmail(memberRegisterRequest.getEmail());

        if (existing != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email 已存在");
        }

        Member member = new Member();
        member.setEmail(memberRegisterRequest.getEmail());

        // 使用統一的 PasswordEncoder 進行加密
        String hashedPassword = passwordEncoder.encode(memberRegisterRequest.getPassword());
        member.setPassword(hashedPassword);

        member.setNickname(memberRegisterRequest.getNickname());
        member.setBirthday(memberRegisterRequest.getBirthday());

        LocalDateTime now = LocalDateTime.now();
        member.setCreatedDate(now);
        member.setLastModifiedDate(now);

        Integer memberId = memberRepository.save(member).getMemberId();

        return memberId;
    }

    @Override
    public MemberLoginResponse login(MemberLoginRequest memberLoginRequest) {

        Member member = memberRepository.findByEmail(memberLoginRequest.getEmail());

        // 檢查 member 是否存在
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email 不存在");
        }

        // 比較密碼
        if (!passwordEncoder.matches(memberLoginRequest.getPassword(), member.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "密碼錯誤");
        }

        // 產生 JWT
        String token = JwtUtil.generateToken(member.getEmail());

        MemberLoginResponse memberLoginResponse = new MemberLoginResponse();
        memberLoginResponse.setMemberId(member.getMemberId());
        memberLoginResponse.setEmail(member.getEmail());
        memberLoginResponse.setNickname(member.getNickname());

        return memberLoginResponse;
    }


    @Override
    public MemberVerifyResponse getMemberVerify( MemberVerifyRequest memberVerifyRequest ) {

        // 判斷 email 是否被註冊過
        Member member = memberRepository.findByEmail(memberVerifyRequest.getEmail());

        if (member == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email 不存在");
        }

        if (!member.getBirthday().equals(memberVerifyRequest.getBirthday())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "生日不正確");
        }

        MemberVerifyResponse memberVerifyResponse = new MemberVerifyResponse();
        memberVerifyResponse.setMemberId(member.getMemberId());
        TokenData tokendata = UuidUtil.generateUuid(member.getMemberId());
        memberVerifyResponse.setToken(tokendata.getToken());
        memberVerifyResponse.setExp(tokendata.getExp());

        return memberVerifyResponse;
    }

    @Override
    public void resetPassword(MemberRetrieveRequest memberRetrieveRequest) {

        // 取得會員
        Member member = memberRepository.findById(memberRetrieveRequest.getMemberId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "會員不存在"));

        // 驗證 UUID token
        if (!UuidUtil.checkToken(
                memberRetrieveRequest.getMemberId(),
                memberRetrieveRequest.getToken())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token 無效或已過期");
        }

        // 更新密碼
        String hashedPassword = passwordEncoder.encode(memberRetrieveRequest.getPassword());
        member.setPassword(hashedPassword);
        memberRepository.save(member);

        // 清除 token
        UuidUtil.clearToken(memberRetrieveRequest.getMemberId());

    }
}
