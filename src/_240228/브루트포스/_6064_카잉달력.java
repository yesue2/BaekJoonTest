package _240228.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _6064_카잉달력 {
    static int M;
    static int N;
    static int lcm;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            LCM();

            boolean check = false;
            for (int j = x; j < lcm; j += M) {
                if (j % N == y) {
                    System.out.println(j+1);
                    check = true;
                    break;
                }
            }
            if (!check)
                System.out.println(-1);
        }
    }

    static void LCM() {
        int max = Math.max(M, N);
        int min = Math.min(M, N);

        int mod = 1;

        while (mod != 0) {
            mod = max % min;
            max = min;
            min = mod;
        }

        lcm = M*N/max;
    }
}
