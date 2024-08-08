import java.io.*;
import java.util.*;

class Solution {
    static int answer;
    static Map<Integer, List<Integer>> graph;
    static boolean[] visited;
    public int solution(int n, int[][] wires) {
        answer = n;
        graph = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            graph.put(i+1, new ArrayList<>());
        }
        
        for (int i = 0; i < wires.length; i++) {
            graph.get(wires[i][0]).add(wires[i][1]);
            graph.get(wires[i][1]).add(wires[i][0]);
        }
        
        visited = new boolean[n+1];
        dfs(1, n);
        
        return answer;
    }
    
    static int dfs(int cur, int n) {
        int cnt = 1;
        visited[cur] = true;
        
        for (int next : graph.get(cur)) {
            if (!visited[next]) cnt += dfs(next, n);
        }
        
        answer = Math.min(answer, Math.abs(n - cnt * 2));
        
        return cnt;
    }
}