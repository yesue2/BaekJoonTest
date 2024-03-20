package _240319.소프티어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 순서대로방문하기 {
    static int[][] graph;
    static int[][] must;
    static int n;
    static int m;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    static List<int[]> mustVisit = new ArrayList<>();
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        must = new int[m][2];
        visited = new boolean[n][n];
        result = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            mustVisit.add(new int[]{x, y});
        }
        visited[mustVisit.get(0)[0]][mustVisit.get(0)[1]] = true;
        dfs(mustVisit.get(0)[0], mustVisit.get(0)[1], 0);
        System.out.println(result);
    }

    static void dfs(int x, int y, int nm) {
        if (nm == m) {
            result++;
            return;
        }

        if (mustVisit.get(nm)[0] == x && mustVisit.get(nm)[1] == y) {
            dfs(x, y, nm + 1);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < n && ny < n && nx >= 0 && ny >= 0 && !visited[nx][ny] && graph[nx][ny] == 0) {
                visited[nx][ny] = true;
                dfs(nx, ny, nm);
                visited[nx][ny] = false;
            }
        }
    }
}
