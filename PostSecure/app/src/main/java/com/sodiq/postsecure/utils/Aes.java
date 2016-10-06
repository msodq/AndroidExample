package com.sodiq.postsecure.utils;

/**
 * Created by mohamadsodiq on 10/16/15.
 */
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Aes {

    public static String encrypt(String kunci, String plaintext, int tipe) throws Exception {
        byte[] rawKey = rawKey(kunci.getBytes(),tipe);
        byte[] hasil = encrypt(rawKey,plaintext.getBytes());
        return k_hexa(hasil);
    }

    public static String decrypt(String kunci, String plaintext, int tipe) throws Exception {
        byte[] rawKey = rawKey(kunci.getBytes(), tipe);
        byte[] enc = k_byte(plaintext);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec s_kunci_rahasia = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, s_kunci_rahasia);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }
    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec s_kunci_rahasia = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, s_kunci_rahasia);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    private static byte[] rawKey(byte[] kunci,int tipe) throws Exception {
        KeyGenerator kunci_gen = KeyGenerator.getInstance("AES");
        SecureRandom acak = SecureRandom.getInstance("SHA1PRNG");
        acak.setSeed(kunci);
        kunci_gen.init(tipe, acak);
        SecretKey kunci_rahasia = kunci_gen.generateKey();
        byte[] raw = kunci_rahasia.getEncoded();
        return raw;
    }

    public static String d_hexa(String hexa) {
        return new String(k_byte(hexa));
    }

    public static String k_hexa(byte[] buffer) {
        if (buffer == null)
        {
            return "";
        }
        StringBuffer hasil = new StringBuffer(2 * buffer.length);
        for (int i = 0; i < buffer.length; i++)
        {
            appendHex(hasil, buffer[i]);
        }
        return hasil.toString();

    }

    public static byte[] k_byte(String hexa_string) {
        int panjang = hexa_string.length() / 2;
        byte[] hasil = new byte[panjang];
        for (int i = 0; i < panjang; i++)
        {
            hasil[i] = Integer.valueOf(hexa_string.substring(2 * i,2 * i + 2),16).byteValue();
        }
        return hasil;

    }

    private final static String hexa = "0123456789ABCDEF";

    private static void appendHex(StringBuffer str_buffer, byte b) {
        str_buffer.append(hexa.charAt((b >> 4) & 0x0f)).append(hexa.charAt(b & 0x0f));
    }

}

