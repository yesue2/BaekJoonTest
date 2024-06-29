import java.io.*;
import java.util.*;

public class Main {
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

        graph = new ArrayList[n+1];

        for (int i = 0; i < n+1; i++) {
            graph[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[x].add(new Node(y, cost));
            graph[y].add(new Node(x, cost));
        }
        result = 0;
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            visited[i] = true;
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
        if (result < depth) {
            result = depth;
        }
    }
}
