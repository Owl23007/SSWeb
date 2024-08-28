package com.SSweb.blog.utils;


import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtil {

    public static String getRSA(String password){
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4D5SEexg+uQJn2xd4KeV5F4wQ" +
                "YVFsrrWkBWRptmw6LkBHK5UvBjUZpz/yK4sya/SSi71YvWswXGfAAfCWd8gXRQM/" +
                "NPU7CdOUMg6ot+nsLZyjxdfCsMWIvaJCdyQZ7sJFbFTUhxUE1/2LgBpD+czjo+is" +
                "QLwDVYBem3BcEfsv6QIDAQAB";
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
        try {
            // 生成公钥
            PublicKey publickey = KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);
            // 加密
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, publickey);
            byte[] result = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(result);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
