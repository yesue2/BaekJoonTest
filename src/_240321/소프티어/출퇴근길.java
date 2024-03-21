package _240321.소프티어;

import java.io.*;
import java.util.*;

public class 출퇴근길 {
    static int n, m, S, T;
    static int[][] road;
    static int[] go;
    static int[] back;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        road = new int[n][n];
        go = new int[n];
        back = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            road[x - 1][y - 1] = 1;
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        dfs(S, 0);

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (go[i] == i && back[i] == i)
                result++;
        }
        System.out.println(result);
    }

    static void dfs(int start, int depth) {
        if (depth == 2) {
            for (int i = 0; i < n; i++) {
                if (visited[i] && i != S) {
                    back[i] = i;
                }
            }
            return;
        }
        if (start == T) {
            for (int i = 0; i < n; i++) {
                if (visited[i] && i != S) {
                    go[i] = i;
                }
            }
            int tmp = T;
            T = S;
            S = tmp;
            dfs(S, depth + 1);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (road[start][i] == 1) {
                dfs(i, depth);
                visited[i] = true;
            }
        }
    }
}
