package com.jessenerio.email_service.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Utils {
    public static String toFirstLetterUpperCase(String str) {
        str = str.toLowerCase();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str.trim();
    }

    public static String toTitleCase(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(toFirstLetterUpperCase(word));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public static String decodeBase64(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
