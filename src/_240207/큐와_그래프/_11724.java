package _240207.큐와_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _11724 {
    static int[][] graph;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 노드
        int M = Integer.parseInt(st.nextToken());  // 간선

        graph = new int[N + 1][N + 1];
        visited = new boolean[graph.length];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 인접 행렬 구현
            graph[x][y] = 1;
            graph[y][x] = 1;
        }

        int cnt = 0;
        // 노드 0은 없으므로 1부터 시작해서 N까지 확인
        for (int i = 1; i <= N; i++) {
            if (visited[i] == true) {
                continue;
            }
            // 방문하지 않은 노드는 bfs 수행
            bfs(i);
            cnt++;
        }
        System.out.println(cnt);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            start = queue.poll();

            for (int i = 0; i <= N; i++) {
                if (graph[start][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
