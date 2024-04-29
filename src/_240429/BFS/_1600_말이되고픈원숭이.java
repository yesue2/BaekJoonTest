package _240429.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1600_말이되고픈원숭이 {
    static int[] kdx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] kdy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int K, W, H, result;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[W][H];
        visited = new boolean[W][H];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (bfs()) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    static boolean bfs() {
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.add(new int[]{0, 0});
        int cnt = 0;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();

            if (xy[0] == H - 1 && xy[1] == W - 1) {
                result = map[H-1][W-1];
                return true;
            }

            for (int i = 0; i < 8; i++) {
                int nx = xy[0] + kdx[i];
                int ny = xy[1] + kdy[i];

                if (cnt == K) {
                    for (int j = 0; j < 4; j++) {
                        nx = xy[0] + dx[j];
                        ny = xy[1] + dy[j];
                    }
                    if (nx >= 0 && ny >= 0 && nx < H && ny < W && map[nx][ny] != 1 && !visited[nx][ny]) {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        map[nx][ny] = map[xy[0]][xy[1]]+1;
                    }
                }
                if (nx >= 0 && ny >= 0 && nx < H && ny < W && map[nx][ny] != 1 && !visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    cnt++;
                    visited[nx][ny] = true;
                    map[nx][ny] = map[xy[0]][xy[1]]+1;
                }
            }
        }
        return false;
    }
}
