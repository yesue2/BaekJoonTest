package _240402.브루트포스순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14888_연산자끼워넣기 {
    static int N;
    static int[] A;
    static int[] opeCnt = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            opeCnt[i] = Integer.parseInt(st.nextToken());
        }

        dfs(A[0], 1);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int result, int idx) {
        if (idx == N) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (opeCnt[i] > 0) {
                opeCnt[i]--;

                switch (i) {
                    case 0:
                        dfs(result + A[idx], idx+1);
                        break;
                    case 1:
                        dfs(result - A[idx], idx+1);
                        break;
                    case 2:
                        dfs(result * A[idx], idx+1);
                        break;
                    case 3:
                        dfs(result / A[idx], idx+1);
                        break;
                }
                opeCnt[i]++;
            }
        }
    }
}
