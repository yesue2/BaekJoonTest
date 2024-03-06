package _240306.브루트포스.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _9095_123더하기 {
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            cnt = 0;
            int n = Integer.parseInt(br.readLine());
            backTracking(n);
            System.out.println(cnt);
        }
    }

    static void backTracking(int n) {
        if (n == 0)
            cnt++;

        for (int i = 1; i < 4; i++) {
            int check = n-i;
            if (check >= 0)
                backTracking(check);
        }
    }
}
