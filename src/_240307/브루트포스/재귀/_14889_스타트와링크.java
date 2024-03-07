package _240307.브루트포스.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14889_스타트와링크 {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int minCal = Integer.MAX_VALUE;  // 정수형의 최댓값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(minCal);
    }

    static void dfs(int i, int num) {
        if (num == N/2) {
            int start = 0;
            int link = 0;
            int cal;

            for (int j = 0; j < N-1; j++) {
                for (int k = j+1; k < N; k++) {
                    if (visited[j] && visited[k]) {
                        start += arr[j][k];
                        start += arr[k][j];
                    } else if (!visited[j] && !visited[k]) {
                        link += arr[j][k];
                        link += arr[k][j];
                    }
                }
            }
            cal = Math.abs(start - link);
            minCal = Math.min(cal, minCal);
            return;
        }

        for (int j = i; j < N; j++) {
            if (!visited[j]) {
                visited[j] = true;
                dfs(j+1, num+1);
                visited[j] = false;
            }
        }
    }
}
