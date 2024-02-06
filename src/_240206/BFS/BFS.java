package _240206.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    static int[][] graph = {
            {},
            {2,3,7},
            {1,3,5},
            {1,2},
            {6,8},
            {2},
            {4,7,8},
            {1,6},
            {4,6}
    };
    public static void main(String[] args) {
        System.out.printf(bfs(1));
    }
    static String bfs(int start) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];

        // 루트 노드 큐에 삽입
        queue.add(start);

        // 루트 노드 방문
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            sb.append(node + " ");

            // 꺼낸 노드의 인접 노드 모두 확인
            for (int i = 0; i < graph[node].length; i++) {
                int tmp = graph[node][i];
                // 인접 노드가 방문한 적 없는 노드면
                if (!visited[tmp]) {
                    // 큐에 삽입
                    queue.add(tmp);
                    // 해당 노드 방문
                    visited[tmp] = true;
                }
            }
        }
        // 1, 2, 3, 7, 5, 6, 4, 8
        return sb.toString();
    }
}
