import java.io.*;
import java.util.*;

class Solution {
    static int answer, maxDist;
    static Map<Integer, List<Integer>> graph;
    public int solution(int n, int[][] edge) {
        graph = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            graph.put(i+1, new ArrayList<>());
        }
        
        // 인접 리스트
        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        answer = 0;
        maxDist = 0;
        
        bfs(n);
        
        return answer;
    }
    static void bfs(int n) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        q.add(new int[]{1, 0});
        visited[1] = true;
        
        while(!q.isEmpty()) {
            int[] xy = q.poll();
            
            if (maxDist < xy[1]) {
                maxDist = xy[1];
                answer = 1;
            } else if (maxDist == xy[1]) {
                answer++;
            }
            
            for (int next : graph.get(xy[0])) {
                if (visited[next]) continue;
                visited[next] = true;
                q.add(new int[]{next, xy[1] + 1});
            }
        }
    }
}