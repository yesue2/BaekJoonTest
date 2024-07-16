import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];  // dp[i] => i의 최소 항의 개수

        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 최악의 경우(i번) => 1^2의 합으로 이루어진 경우
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}
