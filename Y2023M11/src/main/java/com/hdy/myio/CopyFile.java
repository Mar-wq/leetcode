package com.hdy.myio;

import java.io.*;

public class CopyFile {
    private final static int MB  = 1024 * 1024;


    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\ASUS\\Pictures\\Camera Roll\\copy.png");
            outputStream = new FileOutputStream("copyPictrue.png");
            byte[] arr = new byte[5 * MB];
            int len = 0;
            while ((len =  inputStream.read(arr)) > 0 ) {
                outputStream.write(arr);
            }
        } finally {
            inputStream.close();
            outputStream.close();
        }

    }
}
