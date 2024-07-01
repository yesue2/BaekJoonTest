package _240629.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class _1967_트리의지름 {
    static class Node {
        int num, cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    static int n, result;
    static List<Node>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[x].add(new Node(y, cost));
            graph[y].add(new Node(x, cost));
        }

        result = 0;
        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            visited[i + 1] = true;
            dfs(i, 0);
        }
        System.out.println(result);
    }

    static void dfs(int start, int depth) {
        for (Node node : graph[start]) {
            if (!visited[node.num]) {
                visited[node.num] = true;
                dfs(node.num, depth + node.cost);
            }
        }
        if (result < depth) result = depth;
    }
}
