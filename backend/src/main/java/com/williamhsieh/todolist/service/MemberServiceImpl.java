package com.williamhsieh.todolist.service;

import com.williamhsieh.todolist.dao.MemberDao;
import com.williamhsieh.todolist.dto.*;
import com.williamhsieh.todolist.model.Member;
import com.williamhsieh.todolist.util.JwtUtil;
import com.williamhsieh.todolist.util.UuidUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Member getMemberById(Integer memberId) {
        return memberDao.getMemberById(memberId);
    }

    @Override
    public Integer register(MemberRegisterRequest memberRegisterRequest) {
        // 判斷 email 是否被註冊過
        Member member = memberDao.getMemberByEmail(memberRegisterRequest.getEmail());

        if (member != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email 已存在");
        }

        // 使用統一的 PasswordEncoder 進行加密
        String hashedPassword = passwordEncoder.encode(memberRegisterRequest.getPassword());
        memberRegisterRequest.setPassword(hashedPassword);

        // 創建帳號
        return memberDao.createMember(memberRegisterRequest);
    }

    @Override
    public MemberLoginResponse login(MemberLoginRequest memberLoginRequest) {

        Member member = memberDao.getMemberByEmail(memberLoginRequest.getEmail());

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
        Member member = memberDao.getMemberByEmail(memberVerifyRequest.getEmail());

        if (member != null && member.getBirthday().equals(memberVerifyRequest.getBirthday())) {

            MemberVerifyResponse memberVerifyResponse = new MemberVerifyResponse();
            memberVerifyResponse.setMemberId(member.getMemberId());
            TokenData tokendata = UuidUtil.generateUuid(member.getMemberId());
            memberVerifyResponse.setToken(tokendata.getToken());
            memberVerifyResponse.setExp(tokendata.getExp());

            return memberVerifyResponse;

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email 或 生日 不正確");
        }

    }

    @Override
    public void resetPassword(MemberRetrieveRequest memberRetrieveRequest) {

        // 取得會員
        Member member = memberDao.getMemberById(memberRetrieveRequest.getMemberId());
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "會員不存在");
        }

        // 驗證 UUID token
        if (!UuidUtil.checkToken(
                memberRetrieveRequest.getMemberId(),
                memberRetrieveRequest.getToken())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token 無效或已過期");
        }

        // 更新密碼
        String hashedPassword = passwordEncoder.encode(memberRetrieveRequest.getPassword());
        memberDao.updateMemberPassword(memberRetrieveRequest.getMemberId(), hashedPassword);

        // 清除 token
        UuidUtil.clearToken(memberRetrieveRequest.getMemberId());

    }
}
