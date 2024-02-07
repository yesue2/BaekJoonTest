package _240207.큐와_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class _1260 {
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[][] graph;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  //node
        int M = Integer.parseInt(st.nextToken());  // edge
        int V = Integer.parseInt(st.nextToken());  // start

        graph = new int[N+1][N+1];
        visited = new boolean[graph.length];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 인접행렬 방법
            graph[x][y] = 1;
            graph[y][x] = 1;
        }

        dfs(V);
        sb.append("\n");

        visited = new boolean[graph.length];
        bfs(V);

        System.out.println(sb);

    }

    public static void dfs(int start) {
        Stack<Integer> stack = new Stack<>();

        stack.add(start);

        while (!stack.isEmpty()) {
            start = stack.pop();
            if (visited[start]) {
                continue;
            }
            visited[start] = true;
            sb.append(start + " ");

            // 인접한 정점을 스택에 넣을 때, 번호가 작은 것부터 넣도록
            for (int i = N; i >= 1; i--) {
                if (graph[start][i] == 1 && !visited[i]) {
                    stack.add(i);
                }
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            start = queue.poll();
            sb.append(start + " ");

            for (int i = 1; i <= N; i++) {
                if (graph[start][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
