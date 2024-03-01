package _240229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1520_내리막길 {
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int M;
    static int N;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cnt = 0;
        dfs(0,0);
        System.out.println(cnt);
    }

    static void dfs(int x, int y) {
        boolean[][] visited = new boolean[M+1][N+1];
        visited[x][y] = true;
        System.out.println(map[x][y]);
        cnt++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            System.out.println(map[nx][ny]);

            if (nx < 0 || ny < 0 || nx > M || ny > N || visited[nx][ny]) {
                continue;
            } else if (map[nx][ny] < map[x][y]) {
                System.out.println(nx);
                System.out.println(ny);
                dfs(nx, ny);
            }
        }
    }
}
