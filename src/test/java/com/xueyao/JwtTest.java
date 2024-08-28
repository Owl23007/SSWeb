// src/test/java/com/xueyao/JwtUtilTest.java
package com.xueyao;

import org.junit.jupiter.api.Test;

import static com.SSweb.blog.utils.RsaUtil.getRSA;
import static org.assertj.core.api.Assertions.assertThat;
import com.SSweb.blog.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenToken() {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 4);
        claims.put("username", "OWL1234");


        String token = JwtUtil.genToken(claims);


        assertThat(token).isNotNull().isNotEmpty();
    }

    @Test
    public void testParseToken() {
        // Prepare claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 4);
        claims.put("username", "123qwe");

        // Generate token
        String token = JwtUtil.genToken(claims);
       System.out.println(token);

        // Parse token
        Map<String, Object> parsedClaims = JwtUtil.parseToken(token);
        System.out.println(parsedClaims);
        // Assert parsed claims match original claims
        assertThat(parsedClaims).isNotNull();
        assertThat(parsedClaims.get("id")).isEqualTo(4);
        assertThat(parsedClaims.get("username")).isEqualTo("123qwe");
    }

    @Test
    public void jiami() throws Exception {
        System.out.println(getRSA("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"));
    }

}