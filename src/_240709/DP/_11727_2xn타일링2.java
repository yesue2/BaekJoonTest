package _240709.DP;

import java.io.*;

public class _11727_2xn타일링2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[1] = 1;
        if (n > 1) {
            dp[2] = 3;
        }

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 2]) % 10007;
        }
        System.out.println(dp[n]);
    }
}
