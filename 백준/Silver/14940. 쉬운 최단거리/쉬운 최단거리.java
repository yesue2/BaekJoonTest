import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        start = new int[2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) {
                    map[i][j] = -1;
                } else if (tmp == 2) {
                    start[0] = i;
                    start[1] = j;
                    map[i][j] = -1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        bfs(start);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        visited[start[0]][start[1]] = true;
        queue.offer(start);
        map[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + xy[0];
                int ny = dy[i] + xy[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;

                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                map[nx][ny] = map[xy[0]][xy[1]] + 1;
            }
        }
    }
}
