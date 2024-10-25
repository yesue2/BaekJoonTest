import java.io.*;
import java.util.*;

class Solution {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] visited;
    public int[] solution(String[] maps) {
        map = new int[maps.length][maps[0].length()];
        visited = new boolean[map.length][map[0].length];
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < maps.length; i++) {
            String[] stTmp = maps[i].split("");
            for (int j = 0; j < maps[i].length(); j++) {
                if (stTmp[j].equals("X")) {
                    map[i][j] = 0;
                } else {
                    map[i][j] = stTmp[j].charAt(0) - '0';
                }
            }
        }
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(!visited[i][j] && map[i][j] != 0) {
                    int sum = bfs(i, j, map);
                    result.add(sum);
                }
            }
        }
        
        int[] answer = {};
        
        if(result.size() == 0) {
            answer = new int[]{-1};
        } else {
            Collections.sort(result);
            answer = result.stream().mapToInt(Integer::intValue).toArray();
        }
        
        return answer;
    }
    
    static int bfs(int x, int y, int[][] map) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        int sum = map[x][y];
        
        while(!q.isEmpty()) {
            int[] xy = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + xy[0];
                int ny = dy[i] + xy[1];
                
                if(nx >= map.length || ny >= map[0].length || nx < 0 || ny < 0 || visited[nx][ny]) continue;
                if(map[nx][ny] == 0) continue;
                
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                sum += map[nx][ny];
            }
        }
        return sum;
    }
}