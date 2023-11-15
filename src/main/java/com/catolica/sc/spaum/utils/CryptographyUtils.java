package com.catolica.sc.spaum.utils;

import com.catolica.sc.spaum.security.jwt.AES;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class CryptographyUtils {
    static public String encodeWithPBKDF2(String password){
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        Pbkdf2PasswordEncoder pbkdf2Encoder =
                new Pbkdf2PasswordEncoder(
                        "", 8, 185000,
                        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        return passwordEncoder.encode(password);
    }

    public static String decryptAES(String password) throws Exception {
        // Essa chave precisa bater com a chave do front-end
        String secretKey = "XkhZG4fW2t2WdasqWQEQ06dfsda12daQ"; //Length 32
        byte[] origText = AES.decrypt(password.getBytes(), secretKey.getBytes());
        return new String(origText);
    }
}
