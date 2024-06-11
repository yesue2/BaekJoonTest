package _240611.BFS;

import java.util.*;
import java.io.*;

public class _7569_토마토 {
    static int M, N, H, day;
    static List<int[][]> box;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static Queue<Tomato> queue = new LinkedList<>();

    static class Tomato {
        int x, y, z, day;

        public Tomato(int x, int y, int z, int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new ArrayList<>();
        visited = new boolean[H][N][M];

        for (int i = 0; i < H; i++) {
            int[][] floor = new int[N][M];
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    floor[j][k] = tmp;

                    if (tmp == 1) {
                        // 익은 토마토 큐에 삽입
                        queue.add(new Tomato(j, k, i, 0));
                        visited[i][j][k] = true;
                    }
                }
            }
            box.add(floor);
        }

        bfs();

        if (!isAllRipe()) {
            System.out.println(-1);
        } else {
            System.out.println(day);
        }
    }

    static void bfs() {
        day = 0;

        while (!queue.isEmpty()) {
            Tomato t = queue.poll();
            day = t.day;

            for (int i = 0; i < 6; i++) {
                int nx = t.x + dx[i];
                int ny = t.y + dy[i];
                int nz = t.z + dz[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && nz >= 0 && nz < H && box.get(nz)[nx][ny] == 0 && !visited[nz][nx][ny]) {
                    visited[nz][nx][ny] = true;
                    box.get(nz)[nx][ny] = 1;
                    queue.add(new Tomato(nx, ny, nz, day + 1));
                }
            }
        }
    }

    // 방문하지 못한 칸(0이 -1에 둘러쌓여있어 접근하지 못한 경우)이 남아있는지 확인
    static boolean isAllRipe() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box.get(i)[j][k] == 0 && !visited[i][j][k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
