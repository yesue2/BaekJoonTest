package _240417.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1981_배열에서이동 {
    static class Path {
        int x, y;
        List<Integer> path;
        Path(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n, min, max, mid, minD;
    static int[][] arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new boolean[n][n];
        int tMax = 0;
        int tMin = 0;

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                tMax = Math.max(tMax, arr[i][j]);
                tMin = Math.min(tMin, arr[i][j]);
            }
        }
        mid = (tMax + tMin) / 2;
        min = arr[0][0];
        max = arr[0][0];
        minD = Integer.MAX_VALUE;
        setM();
        minD = Math.min(minD, max-min);
        System.out.println(minD);
    }

    static void setM() {
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int[] tmp = new int[4];

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + xy[0];
                int ny = dy[i] + xy[1];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && minD > arr[nx][ny]) {
                    tmp[i] = arr[nx][ny];
                } else {
                    tmp[i] = 201;
                }
            }
            int min = 201;
            int tmpI = 5;
            for (int i = 0; i < tmp.length; i++) {
                if (min > tmp[i]) {
                    min = tmp[i];

                    tmpI = i;
                }
            }
            int nx = dx[tmpI] + xy[0];
            int ny = dy[tmpI] + xy[1];
            System.out.println("tmpI : " + tmpI);
            queue.add(new int[]{nx, ny});
            System.out.println("nx, ny : " + nx + " " + ny);
            min = Math.min(min, arr[nx][ny]);
            max = Math.max(max, arr[nx][ny]);
            System.out.println("min, max : " + min + " " + max);
            visited[nx][ny] = true;
        }
    }
}
