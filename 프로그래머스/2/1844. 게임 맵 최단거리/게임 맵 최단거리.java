import java.io.*;
import java.util.*;

class Solution {
    static int[][] resultMap;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public int solution(int[][] maps) {
        int answer = -1;
        resultMap = new int[maps.length][maps[0].length];
        
        bfs(maps);
        if (resultMap[maps.length-1][maps[0].length-1] != 0) 
            answer = resultMap[maps.length-1][maps[0].length-1] + 1;
        return answer;
    }
    static void bfs(int[][] maps) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] xy = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + xy[0];
                int ny = dy[i] + xy[1];
                
                if (nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length) continue;
                if (visited[nx][ny]) continue;
                if (maps[nx][ny] == 0) continue;
                
                resultMap[nx][ny] = resultMap[xy[0]][xy[1]] + 1;
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }
}