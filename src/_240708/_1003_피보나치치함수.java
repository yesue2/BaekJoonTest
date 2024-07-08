package _240708;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1003_피보나치치함수 {
    static int t;
    static int[] n, cnt0, cnt1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());
        n = new int[t];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < t; i++) {
            n[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, n[i]);
        }

        cnt0 = new int[max + 1];
        cnt1 = new int[max + 1];

        cnt0[0] = 1;
        cnt1[0] = 0;
        // if (max > 0) 조건을 걸지 않고 cnt0[1] = 0; cnt1[1] = 1;을 바로 넣어두면 런타임 에러 발생
        if (max > 0) {
            cnt0[1] = 0;
            cnt1[1] = 1;
        }

        for (int i = 2; i <= max; i++) {
            cnt0[i] = cnt0[i - 1] + cnt0[i - 2];
            cnt1[i] = cnt1[i - 1] + cnt1[i - 2];
        }
        for (int i = 0; i < t; i++) {
            sb.append(cnt0[n[i]]).append(" ").append(cnt1[n[i]]).append('\n');
        }
        System.out.println(sb);
    }
}
