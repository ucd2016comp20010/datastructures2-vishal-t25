package project20280.week6;

public class Tribonacci {

    public static void main(String[] args) {
        int n = 10; // Change this value to compute Tribonacci for different n
        System.out.println("Tribonacci of " + n + " is: " + tribonacci(n));
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1; // Initialize memoization array with -1
        }
        System.out.println("Tribonacci of " + n + " with memoization is: " + tribonacciMemoisation(n, memo));
    }

    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        }
    }

    public static int tribonacciMemoisation(int n, int[] memo) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else if (memo[n] != -1) {
            return memo[n];
        } else {
            memo[n] = tribonacciMemoisation(n - 1, memo) + tribonacciMemoisation(n - 2, memo) + tribonacciMemoisation(n - 3, memo);
            return memo[n];
        }
    }
}
