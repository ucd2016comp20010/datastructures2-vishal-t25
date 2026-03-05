package project20280.week6;

public class Fibonacci {

    public static void main(String[] args) {
        int n = 10; // Change this value to compute Fibonacci for different n
        System.out.println("Fibonacci of " + n + " is: " + fibonacci(n));
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1; // Initialize memoization array with -1
        }
        System.out.println("Fibonacci of " + n + " with memoization is: " + fibonacciMemoisation(n, memo));
    }

    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static int fibonacciMemoisation(int n, int[] memo) {
        if (n <= 1){
            return n;
        } else if (memo[n] != -1) {
            return memo[n];
        } else {
            memo[n] = fibonacciMemoisation(n - 1, memo) + fibonacciMemoisation(n - 2, memo);
            return memo[n];
        }
    }
}
