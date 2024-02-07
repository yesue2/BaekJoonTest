package _240206.DFS;

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
    public static void main(String[] args) {
        System.out.println(dfs(1));
    }
    static String dfs(int start) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.length];

        stack.add(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            sb.append(node + " ");

            for (int i = 0; i < graph[node].length; i++) {
                int tmp = graph[node][i];
                if (!visited[tmp]) {
                    stack.add(i);
                    visited[tmp] = true;
                }
            }
        }
        return sb.toString();
    }
}
