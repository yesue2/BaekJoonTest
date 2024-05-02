package _240502.BFS;

import java.io.*;
import java.util.*;

public class _4991_로봇청소기 {
    public static class Node {
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

    }
    public static int h, w, minDis;
    public static ArrayList<Node> dirty;
    public static int startX, startY;
    public static char[][] map;
    public static boolean[] visited;
    public static int[][][][] dis;  //  x, y 지점에서 nx, ny 지점까지의 최단거리 저장
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(h == 0 && w == 0) break;  // 입력 종료 조건

            dis = new int[h][w][h][w];
            map = new char[h][w];
            dirty = new ArrayList<>();

            for(int i = 0; i< h; i++){
                map[i] = br.readLine().toCharArray();  // 문자열을 char 배열로 변환
                for(int j = 0; j< w; j++){
                    if(map[i][j] == '*'){
                        dirty.add(new Node(i, j));
                    } else if(map[i][j] == 'o'){
                        startX = i;
                        startY = j;
                    }
                }
            }

            visited = new boolean[dirty.size()];
            minDis = Integer.MAX_VALUE;

            for(int i = 0; i< h; i++){
                for(int j = 0; j< w; j++){
                    bfs(i, j);  // i, j 부터 모든 접근 가능 지점까지의 최단거리 저장
                }
            }

            dfs(0, new int[dirty.size()]);  // 더러운 칸으로 만들 수 있는 모든 순열을 조합해 최단거리 구하기

            if(minDis == Integer.MAX_VALUE)
                System.out.println(-1);
            else
                System.out.println(minDis);

        }


    }
    public static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));

        while (!q.isEmpty()){
            Node node = q.poll();
            for(int i=0;i<4;i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(nx < 0 || ny < 0 | nx >= h || ny >= w) continue;
                if(map[nx][ny] == 'x') continue;
                if(dis[x][y][nx][ny] != 0) continue;
                q.add(new Node(nx, ny));
                dis[x][y][nx][ny] = dis[x][y][node.x][node.y]+1;
            }
        }
    }
    public static void dfs(int depth, int[] perm){
        if(depth == dirty.size()){
            int disDFS = 0;
            int nextX = startX;
            int nextY = startY;

            for(int i=0;i<perm.length;i++){
                Node d = dirty.get(perm[i]);
                int distance = dis[nextX][nextY][d.x][d.y];
                if(distance == 0) return;
                disDFS += distance;
                nextX = d.x;
                nextY = d.y;
            }

            minDis = Math.min(minDis, disDFS);
            return;
        }

        for(int i=0;i<dirty.size();i++){
            if(visited[i]) continue;
            visited[i] = true;
            perm[depth] = i;
            dfs(depth+1, perm);
            visited[i] = false;
        }
    }
}
