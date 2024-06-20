import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
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
        visited = new boolean[n][n];
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
        for (int k = 1; k <= landCnt; k++) {
            visited = new boolean[n][n];
            min = Math.min(min, getBridge(k));
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

    static int getBridge(int islandNum) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == islandNum) {
                    queue.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int cx = xy[0];
            int cy = xy[1];
            int dist = xy[2];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    if (map[nx][ny] != 0 && map[nx][ny] != islandNum) {
                        return dist;
                    }
                    if (map[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny, dist + 1});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return Integer.MAX_VALUE; // 연결할 수 없는 경우
    }
}
