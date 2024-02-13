package _240213.큐와_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _7576 {
    static int M;
    static int N;
    static int[][] box;
    static boolean[][] visited;
    static int result;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<tomato> queue = new LinkedList<>();
    static int day;

    static class tomato {
        int x;
        int y;
        int day;

        public tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        int ok = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());

                // 익은 토마토 큐에 삽입
                if (box[i][j] == 1)
                    queue.add(new tomato(i, j, 0));

                // 모든 토마토가 익었는지 확인
                if (box[i][j] == 1 || box[i][j] == -1)
                    ok++;
            }
        }

        if (ok == N * M) {  // 모든 토마토가 이미 익어있을 때
            System.out.println(0);
            return;
        }

        bfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 방문하지 못한 칸(0이 -1에 둘러쌓여있어 접근하지 못한 경우)이 남아있을 때
                if (box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(day);
    }

    static void bfs() {
        day = 0;

        while (!queue.isEmpty()) {
            tomato t = queue.poll();
            day = t.day;

            for (int i = 0; i < 4; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && box[nx][ny] == 0) {
                    // 방문한 칸은 1로 바꿔줌(방문처리)
                    box[nx][ny] = 1;
                    queue.add(new tomato(nx, ny, day + 1));
                }
            }
        }
    }
}
