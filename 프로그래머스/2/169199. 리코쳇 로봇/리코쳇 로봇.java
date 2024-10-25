import java.io.*;
import java.util.*;

class Solution {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int sx, sy, ex, ey;
    public int solution(String[] board) {
        char[][] map = new char[board.length][board[0].length()];
        
        for (int i = 0; i < board.length; i++) {
            map[i] = board[i].toCharArray();
        }
        
        sx = -1;
        sy = -1;
        ex = -1;
        ey = -1;
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 'R') {
                    sx = i;
                    sy = j;
                } else if (map[i][j] == 'G') {
                    ex = i;
                    ey = j;
                }
            }
        }
        int answer = bfs(map);
        return answer;
    }
    
    static int bfs(char[][] map) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;
        
        while(!q.isEmpty()) {
            int[] xy = q.poll();
            
            if (xy[0] == ex && xy[1] == ey) {
                return xy[2];
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + xy[0];
                int ny = dy[i] + xy[1];
                
                if (nx >= map.length || ny >= map[0].length || nx < 0 || ny < 0) continue;
                if (map[nx][ny] == 'D') continue;
                
                while(true) {
                    int nX = nx + dx[i];
                    int nY = ny + dy[i];
                    
                    if (nX < 0 || nY < 0 || nX >= map.length || nY >= map[0].length || map[nX][nY] == 'D') {
                        break;
                    }
                    
                    nx = nX;
                    ny = nY;
                }
                
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, xy[2] + 1});
                }
            }
        }
        
        return -1;
    }
}