package com.tz.bms.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class MyUtil {
    public static String toUTF8(String value) {
        String newValue = "";
        if (value != null && value.length() != 0) {
            try {
                newValue = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return newValue;
    }
}
