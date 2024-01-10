package _240110.수학1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1934 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        int[] A = new int[T];
        int[] B = new int[T];
        int[] result = new int[T];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());

            int gcd;
            int mod = 1;

            int x = A[i]*B[i];
            int max = Math.max(A[i], B[i]);
            int min = Math.min(A[i], B[i]);

            while (mod != 0) {
                mod = max % min;
                max = min;
                min = mod;
            }

            gcd = max;
            result[i] = x/gcd;

            sb.append(result[i]).append('\n');
        }
        System.out.println(sb);
    }
}
