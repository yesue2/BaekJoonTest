import java.io.*;
import java.util.*;

public class Main {
    static int n, m, max, count;
    static int[][] paper;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        paper = new int[n][m];
        visited = new boolean[n][m];
        max = 0;
        count = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && paper[i][j] == 1) {
                    setPicture(i, j);
                    count++;
                }
            }
        }
        System.out.println(count);
        System.out.println(max);
    }
    static void setPicture(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        int cnt = 1;

        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = xy[0] + dx[k];
                int ny = xy[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (paper[nx][ny] == 0) continue;
                if (visited[nx][ny]) continue;
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                cnt++;
            }
        }
        max = Math.max(max, cnt);
    }
}
