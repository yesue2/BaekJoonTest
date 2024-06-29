import java.io.*;
import java.util.*;

class Solution {
    
    public static int n, m, count;
    public static boolean[][] visited;
    public static boolean isSuccess;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] maps) {
        int answer = -1;
        
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        count = 1;
        
        maps = bfs(maps);
        if(isSuccess) answer = maps[n-1][m-1];
        
        return answer;
    }
    
    public static int[][] bfs(int[][] maps) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            int[] xy = queue.poll();
                        
            if(xy[0] == n-1 && xy[1] == m-1) {
                isSuccess = true;
                return maps;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];
                
                if(nx >= n || ny >= m || nx < 0 || ny < 0) continue;
                if(visited[nx][ny]) continue;
                if(maps[nx][ny] == 0) continue;
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                maps[nx][ny] = maps[xy[0]][xy[1]] + 1;
            }
        }
        return maps;
    }
}