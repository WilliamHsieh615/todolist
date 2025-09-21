package com.williamhsieh.todolist.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

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

    // 取得過期時間
    public static long getExpiration(String token) {
        return parseToken(token).getBody().getExpiration().getTime() / 1000; // 秒
    }

}
