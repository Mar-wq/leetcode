package com.hdy.fileWriteInAndOut;

import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {

/*        try {
            Runtime runtime = Runtime.getRuntime();
            File f1 = new File("./1.txt");
            Scanner scanner = new Scanner(f1);

            while (scanner.hasNextLine()) {
                String next = scanner.nextLine();
                System.out.println(next);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/


//        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("./1.txt");
//        Scanner scanner = new Scanner(inputStream);
//        while (scanner.hasNextLine()) {
//            String s = scanner.nextLine();
//            System.out.println(s);
//        }

        File file = new File("./Y2023M11/src/main/java/com/hdy/fileWriteInAndOut/1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        File f2 = new File("./Y2023M11/src/main/java/com/hdy/fileWriteInAndOut/2.txt");
        FileOutputStream outputStream = new FileOutputStream(f2);
        byte[] buffer = new byte[1];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

    }
}
