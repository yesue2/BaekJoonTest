package _240308.브루트포스.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _15661_링크와스타트 {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int minCal = Integer.MAX_VALUE;

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
        dfs(0);
        System.out.println(minCal);
    }

    static void dfs(int num) {
        if (num == N) {
            int start = 0;
            int link = 0;
            int cal;

            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    if (visited[i] != visited[j])
                        continue;
                    if (visited[i])
                        start += arr[i][j] + arr[j][i];
                    else
                        link += arr[i][j] + arr[j][i];
                }
            }
            cal = Math.abs(start - link);
            minCal = Math.min(cal, minCal);
            return;
        }

        // num번째 사람 선택해 스타트 팀에 넣기
        visited[num] = true;
        dfs(num + 1);

        // num번째 사람 선택해 링크 팀에 넣기
        visited[num] = false;
        dfs(num + 1);
    }
}
