package _240712.DP;

import java.io.*;
import java.util.*;

public class _15486_퇴사2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n];
        int[] p = new int[n];
        int[] dp = new int[n + 1];  // 각 상담을 완료했을 때 받을 수 있는 금액

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n - 1; i >= 0; i--) {
            // 해당 날짜에 시작한 상담이 n일을 넘어가지 않는지 확인
            if (i + t[i] > n) {
                dp[i] = dp[i + 1];  // 다음날의 수익을 그대로 가져옴 => 다음날부터 탐색했으므로
            } else {
                // (이전에 탐색한)다음날의 수익이 큰지, 상담을 할 때의 수익이 큰지 확인
                dp[i] = Math.max(dp[i+1], p[i] + dp[i + t[i]]);
            }
        }
        System.out.println(dp[0]);
    }
}
