package _240405.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11047_동전0 {
    static int N, K;
    static int[] worth;
    static int min = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        worth = new int[N];

        for (int i = 0; i < N; i++) {
            worth[i] = Integer.parseInt(br.readLine());
        }
        min();
        System.out.println(min);
    }

    static void min() {
        for (int i = N - 1; i >= 0; i--) {
            if (worth[i] <= K) {
                min += (K / worth[i]);
                K = K % worth[i];
            }
        }
    }
}
