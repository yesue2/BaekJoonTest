package _240423.BFS;

import java.io.*;
import java.util.*;

public class _16956_늑대와양 {
    static int R, C;
    static char[][] map;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'W') {
                    if (setFence(i, j)) {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(1);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static boolean setFence(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                if (map[nx][ny] == 'S')  // 늑대 주변에 양이 붙어있는지 확인
                    return true;
                else if (map[nx][ny] == 'W')  // 늑대 주변에 늑대가 붙어있는지 확인
                    continue;
                else
                    map[nx][ny] = 'D';
            }
        }
        return false;
    }
}
