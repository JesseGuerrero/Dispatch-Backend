package com.jessenerio.email_service.util;

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
}
