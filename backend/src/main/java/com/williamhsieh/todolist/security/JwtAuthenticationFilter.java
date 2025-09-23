package com.williamhsieh.todolist.security;

import com.williamhsieh.todolist.dao.MemberDao;
import com.williamhsieh.todolist.model.Member;
import com.williamhsieh.todolist.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private MemberDao memberDao;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/members/register") || path.startsWith("/members/login") || path.startsWith("/members/verify") || path.startsWith("/members/retrieve");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;

        // 從 Cookie 讀 token
        if (request.getCookies() != null) {
            token = Arrays.stream(request.getCookies())
                    .filter(c -> "token".equals(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }

        if (token != null) {
            try {
                // 從 JWT 解析 email
//                String username = JwtUtil.parseToken(token).getBody().getSubject();

                Claims claims = JwtUtil.parseToken(token).getBody();

                String username = claims.getSubject();
                if (JwtUtil.isExpired(claims)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
                    return;
                }

                Member member = memberDao.getMemberByEmail(username);
                if (member == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Member not found");
                    return;
                }

                // 建立 Authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(member, null, null);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 放進 SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT: " + e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

}
