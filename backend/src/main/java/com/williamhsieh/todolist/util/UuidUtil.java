package com.williamhsieh.todolist.util;

import com.williamhsieh.todolist.dto.TokenData;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UuidUtil {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60;
    private static Map<Integer, TokenData> tokenMap = new HashMap<>();

    // 產生 UUID
    public static TokenData generateUuid(Integer memberId) {

        String token = UUID.randomUUID().toString();
        long exp = System.currentTimeMillis() + EXPIRATION_TIME;

        TokenData tokendata = new TokenData();
        tokendata.setToken(token);
        tokendata.setExp(exp);
        tokenMap.put(memberId, tokendata);

        return tokendata;

    }

    // 比對 token
    public static boolean checkToken(Integer memberId, String inputToken) {

        TokenData savedToken = tokenMap.get(memberId);

        return savedToken != null &&
                savedToken.getToken().equals(inputToken) &&
                System.currentTimeMillis() <= savedToken.getExp();

    }

    // 清除 token
    public static void clearToken(Integer memberId) {
        tokenMap.remove(memberId);
    }

}
