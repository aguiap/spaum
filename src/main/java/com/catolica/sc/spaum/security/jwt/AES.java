package com.catolica.sc.spaum.security.jwt;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static byte[] decrypt(byte[] cipherTextData, byte[] secretKey) throws Exception {
        try {
            String iv = new String(secretKey).substring(0, 16);

            byte[] encrypted = Base64.getDecoder().decode(cipherTextData);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec keyspec = new SecretKeySpec(secretKey, "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original);
            return originalString.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
