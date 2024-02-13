package _240213.큐와_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _7562 {
    static int I;
    static int[] n;  // 현재 있는 칸
    static int[] w;  // 이동하려고 하는 칸
    static int[][] board;
    static int[] dx = {1, -1, 1, -1, 2, 2, -2, -2};
    static int[] dy = {2, 2, -2, -2, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int cnt = Integer.parseInt(br.readLine());
        n = new int[2];
        w = new int[2];

        for (int i = 0; i < cnt; i++) {
            I = Integer.parseInt(br.readLine());
            board = new int[I][I];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                n[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                w[j] = Integer.parseInt(st.nextToken());
            }

            board[n[0]][n[1]] = 1;
            bfs();
            int result = board[w[0]][w[1]];
            sb.append(result - 1).append('\n');
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[I][I];

        queue.add(new int[] {n[0], n[1]});
        visited[n[0]][n[1]] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx < I && nx >= 0 && ny < I && ny >= 0 && !visited[nx][ny]) {
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    board[nx][ny] = board[xy[0]][xy[1]]+1;
                }
            }
        }

    }
}
