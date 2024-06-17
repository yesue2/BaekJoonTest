package _240615;

import java.io.*;
import java.util.*;

public class _2206_벽부수고이동하기 {
    static int n, m;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Person {
        int x, y, dis;
        boolean isBroken;

        public Person(int x, int y, int dis, boolean isBroken) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.isBroken = isBroken;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String tmpStr = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmpStr.charAt(j) - '0';
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        boolean[][][] visited = new boolean[n][m][2];
        Queue<Person> queue = new LinkedList<>();
        queue.offer(new Person(0, 0, 1, false));
        // visited[i][j][0] => [i][j]위치의 벽을 부수지 않고 방문한 경우
        // visited[i][j][1] => [i][j]위치의 벽을 부수고 방문한 경우
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Person person = queue.poll();
            int x = person.x;
            int y = person.y;
            int dis = person.dis;
            boolean isBroken = person.isBroken;

            if (x == n - 1 && y == m - 1) return dis;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (map[nx][ny] == 0) {
                    // nx, ny가 벽일 때
                    if (!isBroken && !visited[nx][ny][0]) {
                        // 벽을 부순적이 없고, nx, ny의 벽을 부수지 않고 방문한 적이 없을 때
                        queue.offer(new Person(nx, ny, dis + 1, false));
                        visited[nx][ny][0] = true;
                    } else if (isBroken && !visited[nx][ny][1]) {
                        // 벽을 부순적이 있고, nx, ny의 벽을 부수고 방문한 적이 없을 때
                        queue.offer(new Person(nx, ny, dis + 1, true));
                        visited[nx][ny][1] = true;
                    }
                } else if (map[nx][ny] == 1 && !isBroken) {
                    // nx, ny가 벽이고, 벽을 부순 적이 없을 떄
                    queue.offer(new Person(nx, ny, dis + 1, true));
                    visited[nx][ny][1] = true;
                }
            }

        }
        return -1;
    }
}
