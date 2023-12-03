package com.jessenerio.email_service.util;

public class Utils {
    public static String toTitleCase(String str) {
        str = str.toLowerCase();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }
}
