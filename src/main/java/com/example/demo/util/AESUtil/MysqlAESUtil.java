package com.example.demo.util.AESUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

@Slf4j
public class MysqlAESUtil {

    public static String aes_encrypt(String password, String strKey) throws Exception {
        SecretKeySpec key = generateMySQLAESKey(strKey, "ASCII");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cleartext = password.getBytes("UTF-8");
        byte[] ciphertextBytes = cipher.doFinal(cleartext);
        return new String(Hex.encodeHex(ciphertextBytes));


    }

    public static String aes_decrypt(String content, String aesKey) throws Exception {

        SecretKey key = generateMySQLAESKey(aesKey, "ASCII");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cleartext = Hex.decodeHex(content.toCharArray());
        byte[] ciphertextBytes = cipher.doFinal(cleartext);
        return new String(ciphertextBytes, "UTF-8");


    }


    public static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) throws UnsupportedEncodingException {

        final byte[] finalKey = new byte[16];
        int i = 0;
        for (byte b : key.getBytes(encoding)) {
            finalKey[i++ % 16] ^= b;
        }
        return new SecretKeySpec(finalKey, "AES");

    }

    /**
     * 手机加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String setMobile(String key) {
        try {
            String xMttkynVxzN1Dwq4JVmHizkBsTMa = Sha256.getSHA256("xMttkynVxzN1Dwq4JVmHizkBsTMa");
            return aes_encrypt(key, xMttkynVxzN1Dwq4JVmHizkBsTMa).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }

    }

    public static String getMobile(String key) {
        try {
            String xMttkynVxzN1Dwq4JVmHizkBsTMa = Sha256.getSHA256("xMttkynVxzN1Dwq4JVmHizkBsTMa");
            return aes_decrypt(key, xMttkynVxzN1Dwq4JVmHizkBsTMa).toUpperCase();

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }

}
