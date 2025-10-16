package com.williamhsieh.todolist.security;

import com.williamhsieh.todolist.repository.MemberRepository;
import com.williamhsieh.todolist.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username);

        if (member == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        } else {
            String memberEmail = member.getEmail();
            String memberPassword = member.getPassword();

            List<GrantedAuthority> authorities = new ArrayList<>();

            return new User(memberEmail, memberPassword, authorities);
        }
    }

}
