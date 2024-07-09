import java.io.*;
import java.util.*;

import java.util.Scanner;
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.close();

        System.out.println(countPinaryNumbers(N));
    }

    public static long countPinaryNumbers(int N) {
        if (N == 1) {
            return 1;
        }

        long[][] dp = new long[N + 1][2];

        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }

        return dp[N][0] + dp[N][1];
    }
}
