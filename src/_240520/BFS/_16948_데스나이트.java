package _240520.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _16948_데스나이트 {
    static int N;
    static int[] r;
    static int[][] map, dis;
    static int[] dx = {-2, -2, 0, 0, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        r = new int[4];
        map = new int[N][N];
        dis = new int[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            r[i] = Integer.parseInt(st.nextToken());
        }
        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.offer(new int[]{r[0], r[1]});
        visited[r[0]][r[1]] = true;
        dis[r[0]][r[1]] = 0;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();

            for (int i = 0; i < 6; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    dis[nx][ny] = dis[xy[0]][xy[1]] + 1;
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    if (nx == r[2] && ny == r[3]) {
                        return dis[nx][ny];
                    }
                }
            }
        }
        return -1;
    }
}
