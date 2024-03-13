package com.hdy.myio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ByteStreamDemo1 {
    public static void main(String[] args) throws IOException {
/*        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);*/

//        FileOutputStream stream = new FileOutputStream("a.txt");
//        stream.write(2555);
//        stream.close();

        FileOutputStream stream = new FileOutputStream("a.txt");
        String str = "haoduoyuhaohaoyo";
        byte[] arr = str.getBytes();
        System.out.println(Arrays.toString(arr));
        stream.write(arr);


/*        FileInputStream stream = new FileInputStream("a.txt");
        int result = stream.read();
        System.out.println(result);*/

    }
}
