package _240209.큐와_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2667  {
    static int N;
    static int[][] map;
    static int houseCnt;  // 각 단지에 속하는 집의 수
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp.charAt(j)-'0';
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();
        int complexCnt = 0;  // 단지 개수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    houseCnt = 0;
                    dfs(i, j);
                    arr.add(houseCnt);
                    complexCnt++;
                }
            }
        }

        Collections.sort(arr);
        System.out.println(complexCnt);
        for (int i : arr) {
            System.out.println(i);
        }
    }
    static void dfs(int x, int y) {
        map[x][y] = 0;
        houseCnt++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 1)
                dfs(nx, ny);
        }
    }
}
