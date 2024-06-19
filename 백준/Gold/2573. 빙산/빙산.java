import java.io.*;
import java.util.*;

public class Main {
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

        int result = 0;
        while (true) {
            result++;

            // 빙산 녹이기
            int[][] nextArr = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0) {
                        int seaCount = 0;
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] == 0) {
                                seaCount++;
                            }
                        }
                        nextArr[i][j] = Math.max(arr[i][j] - seaCount, 0);
                    }
                }
            }
            arr = nextArr;

            // 빙산 덩어리 세기
            int cnt = 0;
            visitedCnt = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0 && !visitedCnt[i][j]) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }

            if (cnt > 1) {
                System.out.println(result);
                return;
            }

            boolean allMelted = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0) {
                        allMelted = false;
                        break;
                    }
                }
                if (!allMelted) break;
            }
            if (allMelted) {
                System.out.println(0);
                return;
            }
        }
    }

    static void bfs(int x, int y) {
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
