package _240612.BFS;

import java.io.*;
import java.util.*;

public class _5427_불 {
    static int tc, w, h, result;
    static boolean isExit;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<int[]> fireQueue;
    static Queue<int[]> sangQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            visited = new boolean[h][w];
            fireQueue = new LinkedList<>();
            sangQueue = new LinkedList<>();
            for (int j = 0; j < h; j++) {
                String tmp = br.readLine();
                for (int k = 0; k < w; k++) {
                    map[j][k] = tmp.charAt(k);
                    if (map[j][k] == '*') fireQueue.add(new int[]{j, k});  // 불의 위치
                    if (map[j][k] == '@') {
                        sangQueue.add(new int[]{j, k});  // 상근이의 위치
                        visited[j][k] = true;
                    }
                }
            }
            isExit = false;
            result = 0;
            getResult();
            if (isExit) {
                System.out.println(result);
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }

    static void getResult() {
        while (!sangQueue.isEmpty()) {
            // 불 bfs
            int fireSize = fireQueue.size();
            for (int i = 0; i < fireSize; i++) {
                int[] fxy = fireQueue.poll();
                for (int j = 0; j < 4; j++) {
                    int fnx = fxy[0] + dx[j];
                    int fny = fxy[1] + dy[j];

                    if (fnx >= 0 && fny >= 0 && fnx < h && fny < w && map[fnx][fny] == '.') {
                        map[fnx][fny] = '*';
                        fireQueue.add(new int[]{fnx, fny});
                    }
                }
            }

            // 상근이 bfs
            int sangSize = sangQueue.size();
            for (int i = 0; i < sangSize; i++) {
                int[] xy = sangQueue.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = xy[0] + dx[j];
                    int ny = xy[1] + dy[j];

                    // 빌딩 탈출 조건
                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
                        isExit = true;
                        result++;
                        return;
                    }

                    if (map[nx][ny] == '.' && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        sangQueue.add(new int[]{nx, ny});
                    }
                }
            }
            result++;
        }
    }
}
