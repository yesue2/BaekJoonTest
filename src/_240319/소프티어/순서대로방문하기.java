package _240319.소프티어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 순서대로방문하기 {
    static int[][] graph;
    static int[][] must;
    static int n;
    static int m;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        must = new int[m][2];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                must[i][j] =  Integer.parseInt(st.nextToken()) - 1;
            }
        }
        bfs(must[0][0], must[0][1]);
        System.out.println(result);
    }

    static void bfs(int sx, int sy) {
        boolean[][] visited = new boolean[n][n];
        boolean[] mVisited = new boolean[m];
        boolean can = true;
        Queue<int[]> queue = new LinkedList<>();
        int nm = 1;
        result = 0;

        visited[sx][sy] = true;
        mVisited[nm] = true;
        queue.add(new int[]{sx, sy});

        while(!queue.isEmpty()) {
            int[] xy = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nx = dx[i] + xy[0];
                int ny = dy[i] + xy[1];

                if (nx < n && ny < n && nx >= 0 && ny >= 0 && !visited[nx][ny] && graph[nx][ny] == 0) {
                    for(int j = 0; j < m; j++) {
                        if(must[j][0] == nx && must[j][1] == ny && !mVisited[j]) {
                            can = false;
                            break;
                        }
                    }
                    if(!can) continue;
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;

                    if(must[m-1][0] == nx && must[m-1][1] == ny) {
                        result++;
                    }else if(must[nm][0] == nx && must[nm][1] == ny) {
                        mVisited[nm] = false;
                        nm++;
                        mVisited[nm] = true;
                    }
                }
            }
        }
    }
    static class path {

    }
}
