package _240302.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1748_수이어쓰기1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int result = 0;

        for (int i = 0; i < N; i++) {
            int tmp = i+1;
            int cnt = 0;

            for (int j = tmp; j != 0; j /= 10) {
                cnt++;
            }
            result += cnt;
        }
        System.out.println(result);
    }
}
