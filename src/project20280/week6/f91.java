package project20280.week6;

public class f91 {

    public static void main(String[] args) {
        int n = -91;
        System.out.println("f91 of " + n + " is: " + f91(n));
    }

    public static int f91(int n) {
        if (n <= 100) {
            return f91(f91(n + 11));
        } else {
            return n - 10;
        }
    }
}
