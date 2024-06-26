package _240626.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _2644_촌수계산 {
    public static int n, p1, p2, m, result;
    public static ArrayList<Integer>[] map;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

        map = new ArrayList[n+1];
        visited = new boolean[n+1];

        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 노드의 수가 많고, 간선의 수가 적음 => 인접리스트 사용
            map[x].add(y);
            map[y].add(x);
        }

        result = -1;
        dfs(p1, 0);
        System.out.println(result);
    }

    public static void dfs(int start, int depth) {
        visited[start] = true;

        if (start == p2) {
            result = depth;
            return;
        }

        for (int n : map[start]){
            if (!visited[n]) {
                dfs(n, depth+1);
            }
        }
    }
}
