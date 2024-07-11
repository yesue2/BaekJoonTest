package _240711.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _9461_파도반수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            Long[] dp = new Long[12 + n];
            dp[0] = dp[1] = dp[2] = 1L;
            dp[3] = dp[4] = 2L;
            dp[5] = 3L;
            dp[6] = 4L;
            dp[7] = 5L;
            dp[8] = 7L;
            dp[9] = 9L;
            dp[10] = 12L;
            for (int j = 0; j < n; j++) {
                dp[11 + j] = dp[6 + j] + dp[10 + j];
            }
            System.out.println(dp[n - 1]);
        }
    }
}
