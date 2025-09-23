package com.williamhsieh.todolist.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

/* JWT 格式
    {
        "alg": "RS256",
        "type": "JWT"
    },
    {
        "username": "(使用者名稱)",
        "issuedAt": "(現在時間)",
        "expiration": "(現在時間+1小時)"
    }
 */

public class JwtUtil {

    // 固定金鑰(重啟程式 JWT 不會變，不需重新登入，但較不安全)
//    private static final String SECRET = "my-super-secret-key-in-my-todo-list";
//    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    // 隨機金鑰(重啟程式 JWT 會變，需重新登入)
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1小時

    // 產生 Token
    public static String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    // 解析 Token
    public static Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    // 驗證是否過期
    public static boolean isExpired(String token) {
        Date expiration = parseToken(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
    public static boolean isExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    // 嘗試驗證，回傳 Claims
    public static Claims validateToken(String token) {
        try {
            return parseToken(token).getBody();
        } catch (JwtException e) {
            return null;
        }
    }

    // 取得過期時間
    public static long getExpiration(String token) {
        return parseToken(token).getBody().getExpiration().getTime() / 1000; // 秒
    }

}
