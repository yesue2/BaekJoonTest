package _240619.BFS;

import java.io.*;
import java.util.*;

public class _2573_빙산 {
    static int n, m;
    static int[][] arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visitedCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;  // 걸린 년도
        while (true) {
            result++;

            // 빙산 녹이기
            loop1:
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0) {
                        setMelting(i, j);
                        break loop1;
                    }
                }
            }

            // 빙산 덩어리 세기
            int cnt = 0;
            visitedCnt = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0 && !visitedCnt[i][j]) {
                        getCnt(i, j);
                        cnt++;
                        // 빙산이 두 덩어리 이상일 때
                        if (cnt > 1) {
                            System.out.println(result);
                            return;
                        }
                    }
                }
            }

            // 모두 바다일 때
            boolean isMelting = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0) {
                        isMelting = false;
                        break;
                    }
                }
                if (!isMelting) break;
            }
            if (isMelting) {
                System.out.println(0);
                return;
            }
        }
    }

    static void setMelting(int x, int y) {
        int[][] tmpArr = new int[n][m];  // 빙산이 녹은 상태를 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmpArr[i][j] = arr[i][j];
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                // 바다를 만나면 빙산의 값 -1
                if (arr[nx][ny] <= 0) {
                    tmpArr[xy[0]][xy[1]]--;
                }

                // 빙산을 만나면 탐색 진행
                if (arr[nx][ny] > 0 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = tmpArr[i][j];
            }
        }
    }

    static void getCnt(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visitedCnt[x][y] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (!visitedCnt[nx][ny] && arr[nx][ny] > 0) {
                    queue.offer(new int[]{nx, ny});
                    visitedCnt[nx][ny] = true;
                }
            }
        }
    }
}
