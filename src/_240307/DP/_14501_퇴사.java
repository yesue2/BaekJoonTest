package _240307.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14501_퇴사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N];
        int[] P = new int[N];
        int[] DP = new int[N+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N-1; i >= 0; i--) {
            if (i+T[i] > N)  // i에 상담할 수 없을 때
                DP[i] = DP[i+1];
            else  // i에 상담할 수 있을 때
                DP[i] = Math.max(DP[i+1], P[i]+DP[i+T[i]]);  // max(i에 상담하지 않았을 때, i에 상담했을 때)
        }
        System.out.println(DP[0]);
    }
}
