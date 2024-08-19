package com.xueyao;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGetToken() {
        Map<String,Object> map = new HashMap<>();

        map.put("id",1);
        map.put("username","xueyao");
        //生成token
        String token = JWT.create().withClaim("user",map)
                // 设置过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                // 指定算法和密钥
                .sign(Algorithm.HMAC256("xueyao"));
        System.out.println(token);
    }

    @Test
    public void verifyToken() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"+
                       ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6Inh1ZXlhbyJ9LCJleHAiOjE3MjQwODUyMTF9"+
                       ".5hM41P7MjvT-aUaUcHQ82RJTYPsvQCQhi2d8aAYLz6Q";
        //创建JWT验证器
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("xueyao")).build();
        //验证token，并生成一个解析后的JWT对象
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        Map<String, Claim> map = decodedJWT.getClaims();
        System.out.println(map.get("user"));
    }
}
