import java.io.*;
import java.util.*;

class Solution {
    static boolean[][] visited;
    static char[][] map;
    static int[][] dis;
    static int ex, ey, result;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public int solution(String[] maps) {
        int answer = -1;
        visited = new boolean[maps.length][maps[0].length()];
        map = new char[maps.length][maps[0].length()];
        dis = new int[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            map[i] = maps[i].toCharArray();
        }
        
        ex = -1;
        ey = -1;
        result = 0;
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 'S') {
                    dfs(i, j);
                }
            }
        }
        
        if (ex != -1 && ey != -1) {
            answer = result + dis[ex][ey] + 1;
        }
        
        return answer;
    }
    
    static void dfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean flag = false;
        
        q.add(new int[]{x, y});
        visited[x][y] = true;
        dis[x][y] = 0;
        
        while(!q.isEmpty()) {
            int[] xy = q.poll();
            
            if(map[xy[0]][xy[1]] == 'E' && flag) {
                ex = xy[0];
                ey = xy[1];
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];
                
                if (nx >= map.length || ny >= map[0].length || nx < 0 || ny < 0) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] == 'X') continue;
                if (!flag && map[nx][ny] == 'L') {
                    result = dis[xy[0]][xy[1]];
                    visited = new boolean[map.length][map[0].length];
                    dis = new int[map.length][map[0].length];
                    q.clear();
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    flag = true;
                    break;
                }
                
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                dis[nx][ny] = dis[xy[0]][xy[1]] + 1;
            }
        }
    }
}