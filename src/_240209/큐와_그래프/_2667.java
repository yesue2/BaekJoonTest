package _240209.큐와_그래프;

import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2667  {
    static int N;
    static int[][] map;
    static int houseCnt;
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        visited = new boolean[map.length][map.length];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp.charAt(j)-'0';
            }

        }

        int complexCnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visited[i][j] == true)
                    continue;
                bfs(i, j);
                sb.append(houseCnt).append('\n');
                complexCnt++;
            }
        }

        System.out.println(complexCnt);
        System.out.println(sb);
    }
    static void bfs(int i, int j) {
        Queue<Integer> queue = new LinkedList<>();
        houseCnt = 1;

        queue.add(map[i][j]);
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int k = 0; k < N; k++) {
                if (!visited[v][k] && map[v][k] == 1) {
                    queue.add(k);
                    visited[v][k] = true;
                    houseCnt++;
                }
            }
        }
    }

}
