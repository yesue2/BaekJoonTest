package _240610.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _10026_적록색약 {
    static int n, noneResult, yesResult;
    static char[][] grid;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        grid = new char[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = str.charAt(j);
            }
        }

        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    getNone(i, j, grid[i][j]);
                    noneResult++;
                }
            }
        }

        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'R' || grid[i][j] == 'G') {
                    if (!visited[i][j]) {
                        getYes(i, j);
                        yesResult++;
                    }
                } else if (grid[i][j] == 'B' && !visited[i][j]) {
                    getNone(i, j, 'B');
                    yesResult++;
                }
            }
        }
        System.out.println(noneResult + " " + yesResult);

    }

    static void getNone(int i, int j, char c) {
        Queue<int[]> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = xy[0] + dx[k];
                int ny = xy[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (grid[nx][ny] != c) continue;
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    static void getYes(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = xy[0] + dx[k];
                int ny = xy[1] + dy[k];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (grid[nx][ny] == 'B') continue;
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }
}
