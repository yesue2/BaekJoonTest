import java.io.*;
import java.util.*;

public class Main {
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
            if (depth == 2) {
                dfs(x, y, depth + 1, sum + paper[nx][ny]);
                visited[nx][ny] = false;
            }

            dfs(nx, ny, depth + 1, sum + paper[nx][ny]);
            visited[nx][ny] = false;
        }
    }
}
