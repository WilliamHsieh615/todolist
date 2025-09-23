package com.williamhsieh.todolist.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;

public class CookieUtil {

    public static ResponseCookie createHttpOnlyCookie(
            String name, // cookie 名稱
            String value, // cookie 值 (JWT token)
            long maxAgeSeconds, // 過期時間（秒）
            String path, // cookie 路徑
            boolean secure, // 是否 HTTPS
            String sameSite // "Strict" / "Lax" / "None"
    ) {
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(secure)
                .path(path)
                .maxAge(Duration.ofSeconds(maxAgeSeconds))
                .sameSite(sameSite)
                .build();
    }

    // 加入 cookie
    public static void addCookie(HttpServletResponse response, ResponseCookie cookie) {
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    // 清除 cookie
    public static ResponseCookie deleteCookie(String name, String path, boolean secure, String sameSite) {
        return ResponseCookie.from(name, "")
                .httpOnly(true)
                .secure(secure)
                .path(path)
                .maxAge(0)
                .sameSite(sameSite)
                .build();
    }
}

