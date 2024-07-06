import java.io.*;
import java.util.*;

class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, 0, n, computers);
                answer++;
            }
        }
        
        return answer;
    }
    static void dfs(int start, int depth, int n, int[][] computers) {
        visited[start] = true;
        
        for (int i = 0; i < n; i++) {
            if(computers[start][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(i, depth+1, n, computers);
            }
        }
    }
}