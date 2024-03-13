package com.hdy.myio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CharStreamDemo {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("a.txt");

        int ch = 0;
        while ((ch = reader.read()) != -1) {
            System.out.println((char)ch);
        }

        reader.close();
    }
}
