package _240801.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _14502_연구소 {
    static int n, m, max;
    static int[][] map;
    static int[][] tmpMap;
    static boolean[][] spreadVisited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        tmpMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = Integer.MIN_VALUE;
        dfs(0);

        System.out.println(max);
    }

    // 벽 세우기
    static void dfs(int cnt) {
        // 벽을 3개 세웠을 때
        if (cnt == 3) {
            getSafeArea();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }

    }

    // 안전 영역 구하기
    static void getSafeArea() {
        // map 배열 임시 복사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }

        // 바이러스 퍼지게 하기
        spreadVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tmpMap[i][j] == 2 && !spreadVisited[i][j]) {
                    spreadVirus(i, j);
                }
            }
        }

        // 최종 map에서 안전 영역 계산
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tmpMap[i][j] == 0) {
                    result++;
                }
            }
        }
        max = Math.max(max, result);
    }

    // 바이러스 퍼지게 하기
    static void spreadVirus(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        spreadVisited[x][y] = true;

        while (!q.isEmpty()) {
            int[] xy = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + xy[0];
                int ny = dy[i] + xy[1];

                if (nx >= n || ny >= m || nx < 0 || ny < 0) continue;
                if (spreadVisited[nx][ny]) continue;
                if (tmpMap[nx][ny] == 1) continue;

                tmpMap[nx][ny] = 2;
                spreadVisited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }
}
