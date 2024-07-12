package _240712.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10844_쉬운계단수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // dp[i][j] => 길이가 i이고 마지막 숫자가 j인 계단 수의 개수
        long[][] dp = new long[n + 1][10];

        // 길이가 1일 때의 계단 수 초기화
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                /* j가 0일 때와 9일 때 빼고 다음 식이 성립
                 => dp[i][j] = dp[i-1][j-1] + dp[i-1][i+1] */
                // j가 0일 때 => dp[i][0] = dp[i - 1][1]
                // j가 9일 때 => dp[i][9] = dp[i - 1][8]
                if (j > 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if (j < 9) {
                    dp[i][j] += dp[i - 1][j + 1];
                }
                dp[i][j] %= 1000000000;
            }
        }

        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[n][i];
        }
        result %= 1000000000;

        System.out.println(result);
    }
}
