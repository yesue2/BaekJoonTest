package _240730.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _14500_테트로미노 {
    static int n, m, max;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m];

        paper = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, paper[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(max);
    }

    static void dfs(int x, int y, int depth, int sum) {
        // 4번 탐색 시 max값 반환
        if (depth == 4) {
            max = Math.max(max, sum);
            visited[x][y] = false;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= n || ny >= m || nx < 0 || ny < 0) continue;
            if (visited[nx][ny]) continue;

            visited[nx][ny] = true;

            // ㅜ모양의 테트로미노일 때
            if (depth == 2) {
                dfs(x, y, depth + 1, sum + paper[nx][ny]);
                visited[nx][ny] = false;
            }

            dfs(nx, ny, depth + 1, sum + paper[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}
