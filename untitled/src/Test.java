import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;





public class Test {
    public static void main(String[] args) {

/*
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("当前工作目录是: " + currentDirectory);
*/

        try {
            Runtime runtime = Runtime.getRuntime();
            File f1 = new File("./untitled/src/1.txt");
            Scanner scanner = new Scanner(f1);

            while (scanner.hasNextLine()) {
                String next = scanner.nextLine();
                System.out.println(next);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
