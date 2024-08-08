import java.util.*;

class Solution {
    static char[][] room;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int i = 0; i < 5; i++) {
            room = new char[5][5];
            for (int j = 0; j < 5; j++) {
                room[j] = places[i][j].toCharArray();
            }
            if (checkRoom()) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        
        return answer;
    }
    
    static boolean checkRoom() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (room[i][j] == 'P') {
                    if (!bfs(i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    static boolean bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            int[] xy = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];
                int dist = Math.abs(nx - x) + Math.abs(ny - y);
                
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                
                if (room[nx][ny] == 'P' && dist <= 2) {
                    return false;
                }
                
                if (room[nx][ny] == 'O' && dist < 2) {
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return true;
    }
}
