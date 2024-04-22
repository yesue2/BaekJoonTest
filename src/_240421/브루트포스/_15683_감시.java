package _240421.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _15683_감시 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        search();
    }

    static void search() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && map[i][j] != 6) {
                    switch (map[i][j]) {
                        case 1:
                            watch1(i, j);
                            break;
                        case 2:
                            watch2(i, j);
                            break;
                        case 3:
                            watch3(i, j);
                            break;
                        case 4:



                    }
                }
            }
        }
    }

    static void watch1(int cx, int cy) {
        for (int i = 0; i < 4; i++) {
            search(i, cx, cy);
        }
    }

    static void watch2(int cx, int cy) {
        for (int i = 0; i < 2; i++) {
            for (int j = i; j <= i + 2; j += 2) {
                search(j, cx, cy);
            }
        }
    }

    static void watch3(int cx, int cy) {
        for (int i = 0; i < 3; i++) {
            for (int j = i; j <= i + 2; j++) {
                search(j, cx, cy);
            }
        }
    }

    static void search(int d, int cx, int cy) {
        int nx = dx[d] + cx;
        int ny = dy[d] + cy;

        if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != 6) {
            return;
        } else {
            visited[nx][ny] = true;
            search(d, nx, ny);
        }
    }
}
