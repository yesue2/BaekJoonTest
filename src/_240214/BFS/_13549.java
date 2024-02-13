package _240214.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _13549 {
    static int N;
    static int K;
    static int[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[100001];

        bfs();

        int result = graph[K];
        System.out.println(result);
    }
    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];

        queue.add(N);
        visited[N] = true;

        while (!queue.isEmpty()) {
            int dis = queue.poll();
            // 수빈이가 갈 수 있는 위치 변수 구현
            int[] dx = {1, -1, dis*2 - dis};

            for (int i = 0; i < 3; i++) {
                int nx = dis + dx[i];

                if (nx >= 0 && nx < 100001 && !visited[nx]) {
                    queue.add(nx);
                    visited[nx] = true;
                    if (i == 2)
                        graph[nx] = graph[dis];
                    else
                        graph[nx] = graph[dis] + 1;
                }
            }
        }
    }
}
