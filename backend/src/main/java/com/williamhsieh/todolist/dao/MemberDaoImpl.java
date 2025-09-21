package com.williamhsieh.todolist.dao;

import com.williamhsieh.todolist.dto.MemberRegisterRequest;
import com.williamhsieh.todolist.model.Member;
import com.williamhsieh.todolist.rowmapper.MemberRowMapper;
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
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Member getMemberById(Integer memberId) {

        String sql = "SELECT member_id, email, password, " +
                "nickname, birthday, created_date, last_modified_date " +
                "FROM member WHERE member_id = :memberId";

        Map<String,Object> map = new HashMap<>();
        map.put("memberId", memberId);

        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());

        if(memberList.isEmpty()){
            return null;
        } else {
            return memberList.get(0);
        }

    }

    @Override
    public Member getMemberByEmail(String email) {

        String sql = "SELECT member_id, email, password, " +
                "nickname, birthday, created_date, last_modified_date " +
                "FROM member WHERE email = :email";

        Map<String,Object> map = new HashMap<>();
        map.put("email", email);

        List<Member> memberList = namedParameterJdbcTemplate.query(sql, map, new MemberRowMapper());

        if(memberList.isEmpty()){
            return null;
        } else {
            return memberList.get(0);
        }

    }

    @Override
    public Integer createMember(MemberRegisterRequest memberRegisterRequest) {

        String sql = "INSERT INTO member(email, password, nickname, birthday, " +
                "created_date, last_modified_date) " +
                "VALUES (:email, :password, :nickname, :birthday, " +
                ":created_date, :last_modified_date) ";

        Map<String,Object> map = new HashMap<>();
        map.put("email", memberRegisterRequest.getEmail());
        map.put("password", memberRegisterRequest.getPassword());
        map.put("nickname", memberRegisterRequest.getNickname());
        map.put("birthday", memberRegisterRequest.getBirthday());

        LocalDateTime now = LocalDateTime.now();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        // 取得與儲存資料庫自動生成的 id
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int memberId = keyHolder.getKey().intValue();

        return memberId;

    }

    @Override
    public void updateMemberPassword(Integer memberId, String hashedPassword) {

        String sql = "UPDATE member SET password = :password, last_modified_date = :lastModifiedDate " +
                "WHERE member_id = :memberId";

        Map<String,Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("password", hashedPassword);
        map.put("lastModifiedDate", LocalDateTime.now());

        namedParameterJdbcTemplate.update(sql, map);

    }
}
