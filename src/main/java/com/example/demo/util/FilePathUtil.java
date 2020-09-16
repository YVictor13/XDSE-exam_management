package com.example.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilePathUtil {
    public static String getExtension(String path){
        String pattern = "(\\..+)?$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(path);
        if(m.find()){
            return m.group();
        }
        return "";
    }
}
