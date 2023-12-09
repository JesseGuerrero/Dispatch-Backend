package com.jessenerio.email_service.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

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

    public static String generateRandomString() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            char randomChar = (char) ('a' + random.nextInt(26));
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    public static String hideEmail(String email) {
        StringBuilder result = new StringBuilder();
        int charactersUsed = 0;

        for (char c : email.toCharArray()) {
            if (c == '@' || c == '.') {
                result.append(c);
                charactersUsed = 0;
            } else {
                if (charactersUsed < 2) {
                    result.append(c);
                } else {
                    result.append('*');
                }
                charactersUsed++;
            }
        }

        return result.toString();
    }
}
