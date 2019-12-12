package com.example.demo.Utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public final class HashUtils {

    public static String hash(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        builder.append(s + Calendar.getInstance().getTimeInMillis());
        byte [] byteHash = digest.digest(builder.toString().getBytes("UTF-8"));
        digest.reset();
        return String.format("%x" + (byteHash.length * 2) + "x", new BigInteger(1, byteHash));
    }
}
