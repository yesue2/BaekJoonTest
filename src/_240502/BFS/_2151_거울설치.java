package _240502.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _2151_거울설치 {
    static int N;
    static char[][] map;
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};
    static boolean[][] visited;
    static int[] doorXY;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        doorXY = new int[2];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        doorXY[0] = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '#') {
                    doorXY[0] = i;
                    doorXY[1] = j;
                    break;
                }
            }
            if (doorXY[0] == i) break;
        }
        bfs();
    }

    static void bfs() {
        queue = new LinkedList<>();
        visited = new boolean[N][N];
        int x = doorXY[0];
        int y = doorXY[1];
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();

            if (map[xy[0]][xy[1]] == '#') return;  // 또 다른 문을 만나면 종료

            if (map[xy[0]][xy[1]] == '/') {
                for (int i = 0; i < 2; i++) {
                    int nx = xy[0] + dx[i];
                    int ny = xy[0] + dy[i];
                }
            } else if (map[xy[0]][xy[1]] == '\\') {
                for (int i = 2; i < 4; i++) {
                    int nx = xy[0] + dx[i];
                    int ny = xy[0] + dy[i];
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    int nx = xy[0] + dx[i];
                    int ny = xy[0] + dy[i];

                }
            }
        }
    }
    static void isCheck(int nx, int ny) {
        if (nx < N && ny < N && nx >= 0 && ny >= 0 && map[nx][ny] != '*') {
            visited[nx][ny] = true;
            if (map[nx][ny] == '!') {
                map[nx][ny] = ''
            }
            queue.offer(new int[]{nx, ny});

        }
    }
}
