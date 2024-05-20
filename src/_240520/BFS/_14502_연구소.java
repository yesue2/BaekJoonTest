package _240520.BFS;

import java.io.*;
import java.util.*;

// 0 => 빈 칸, 1 => 벽, 2 => 바이러스
// 벽 3개 세우기 필수
public class _14502_연구소 {
    static int N, M, result, max;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map, tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;
        dfs(0);
        System.out.println(max);
    }

    static void dfs(int cnt) {  // 아무 곳이나 벽을 3개 세우기
        if (cnt == 3) {
            countArea();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(cnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static void countArea() {  // 안전 구역 구하기
        bfs();

        result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp[i][j] == 0) {
                    result++;
                }
            }
        }
        max = Math.max(max, result);
    }

    static void bfs() {  // 바이러스 퍼지기
        Queue<int[]> queue = new LinkedList<>();

        // 바이러스를 퍼지게 할 임시 map 생성
        tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp[i][j] == 2) {
                    queue.offer(new int[]{i, j});  // 모든 바이러스를 큐에 추가
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && tmp[nx][ny] == 0) {
                    tmp[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
