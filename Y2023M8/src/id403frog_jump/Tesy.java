package id403frog_jump;

public class Tesy {
    public static void main(String[] args) {
        int[] a = new int[2], b = new int[2];
        a[0] = 0; b[0] = 0;

        System.out.println(a.hashCode() == b.hashCode());
    }
}
