package _240206.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
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
    static boolean[] visited;
    static StringBuilder sb;
    public static void main(String[] args) {
        sb = new StringBuilder();

        visited = new boolean[graph.length];

        // 1 7 6 8 4 3 2 5
        dfs(1);
        sb.append('\n');

        visited = new boolean[graph.length];
        dfsStack(1);

        System.out.println(sb);
    }

    // 재귀 사용
    static void dfs(int start) {
        if (visited[start]) return;

        visited[start] = true;
        sb.append(start + " ");

        for (int i = 0; i < graph[start].length; i++) {
            int adjacentNode = graph[start][i];
            dfs(adjacentNode); // 재귀 호출
        }
    }


    // 스택 사용
    static String dfsStack(int start) {
        Stack<Integer> stack = new Stack<>();
        visited = new boolean[graph.length];

        stack.add(start);

        while (!stack.isEmpty()) {
            start = stack.pop();
            if (visited[start]) {
                continue;
            }
            visited[start] = true;
            sb.append(start + " ");

            // 현재 노드와 연결된 모든 인접 노드 탐색  => 현재 노드가 1일 때
            // graph[start]: 현재 노드와 연결된 노드들의 배열  => 인접한 노드는 2, 3, 7
            // graph[start].length: 현재 노드와 연결된 노드들의 개수
            for (int i = 0; i < graph[start].length; i++) {
                // 현재 노드와 인접한 모든 노드 가져옴  => 처음에는 인접 노드 2가 가져와짐
                int adjacentNode = graph[start][i];
                // 인접 노드가 아직 방문하지 않았으면 스택에 추가
                if (!visited[adjacentNode]) {
                    stack.add(adjacentNode);
                }
            }

        }
        return sb.toString();
    }
}