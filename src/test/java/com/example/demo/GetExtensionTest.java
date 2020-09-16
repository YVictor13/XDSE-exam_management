package com.example.demo;

import com.example.demo.util.FilePathUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetExtensionTest {
    @Test
    public void test1(){
        String s = "D:\\user.ini";
        String extension = FilePathUtil.getExtension(s);
        assertEquals(".ini", extension);
    }
    @Test
    public void test2(){
        String s = "user";
        String extension = FilePathUtil.getExtension(s);
        assertEquals("", extension);
    }
    @Test
    public void test3(){
        String s = "/usr/hello.txt";
        String extension = FilePathUtil.getExtension(s);
        assertEquals(".txt", extension);
    }
}
