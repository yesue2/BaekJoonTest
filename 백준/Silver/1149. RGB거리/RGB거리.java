import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] homes = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                homes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][3];
        // 첫 번째 집의 비용 초기화
        dp[0][0] = homes[0][0];
        dp[0][1] = homes[0][1];
        dp[0][2] = homes[0][2];

        // 두 번째 집부터 n번째 집까지의 최소 비용 계산
        for (int i = 1; i < n; i++) {
            dp[i][0] = homes[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = homes[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = homes[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        // 마지막 집의 최소 비용 중 최솟값 찾기
        int result = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
        System.out.println(result);
    }
}
