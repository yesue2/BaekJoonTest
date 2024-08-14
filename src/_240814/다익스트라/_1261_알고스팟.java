package _240814.다익스트라;

import java.io.*;
import java.util.*;

public class _1261_알고스팟 {
    static int m, n;
    static List<int[]>[] miro;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            char[] tmpCh = tmp.toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmpCh[j] - '0';
            }
        }

    }
}
