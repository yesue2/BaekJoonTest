package _240620.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2146_다리만들기 {
    static int n;
    static int[][] map;
    static boolean[][] visitedLand;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호 매기기
        visitedLand = new boolean[n][n];
        int landCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0 && !visitedLand[i][j]) {
                    landCnt++;
                    setMap(i, j, landCnt);
                }
            }
        }

        // 다리 최소길이 구하기
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= landCnt; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    // 각 섬마다 시작점 정해서 탐색 => 0이 아닐 경우만 조건에 걸어놓으면 메모리초과
                    if (map[j][k] == i) {
                        visited = new boolean[n][n];
                        min = Math.min(min, getBridge(j, k));
                    }
                }
            }
        }
        System.out.println(min);
    }

    static void setMap(int x, int y, int landCnt) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visitedLand[x][y] = true;
        map[x][y] = landCnt;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx >= n || ny >= n || nx < 0 || ny < 0) continue;
                if (map[nx][ny] != 0 && !visitedLand[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visitedLand[nx][ny] = true;
                    map[nx][ny] = landCnt;
                }
            }
        }
    }

    static int getBridge(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 0});  // 큐에 현재까지의 거리도 저장
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx >= n || ny >= n || nx < 0 || ny < 0) continue;
                if (!visited[nx][ny]) {
                    if (map[nx][ny] != 0 && map[nx][ny] != map[x][y]) {
                        return xy[2];
                    }
                    if (map[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny, xy[2] + 1});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
