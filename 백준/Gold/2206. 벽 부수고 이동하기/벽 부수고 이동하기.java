import java.io.*;
import java.util.*;

public class Main {
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
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Person person = queue.poll();
            int x = person.x;
            int y = person.y;
            int dist = person.dis;
            boolean broken = person.isBroken;

            if (x == n - 1 && y == m - 1) return dist;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (map[nx][ny] == 0) {
                        if (!broken && !visited[nx][ny][0]) {
                            queue.offer(new Person(nx, ny, dist + 1, false));
                            visited[nx][ny][0] = true;
                        } else if (broken && !visited[nx][ny][1]) {
                            queue.offer(new Person(nx, ny, dist + 1, true));
                            visited[nx][ny][1] = true;
                        }
                    } else if (map[nx][ny] == 1 && !broken) {
                        queue.offer(new Person(nx, ny, dist + 1, true));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }
        return -1;
    }
}
