import java.io.*;
import java.util.*;

public class Main {
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
        System.out.println(bfs());
    }

    static int bfs() {
        Deque<int[]> dq = new ArrayDeque<>();
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dq.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!dq.isEmpty()) {
            int[] xy = dq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx >= n || ny >= m || nx < 0 || ny < 0) continue;

                int nD = dist[xy[0]][xy[1]] + map[nx][ny];

                if (nD < dist[nx][ny]) {
                    dist[nx][ny] = nD;
                    if (map[nx][ny] == 0) dq.offer(new int[]{nx, ny});
                    else dq.offerLast(new int[]{nx, ny});
                }
            }
        }
        return dist[n - 1][m - 1];
    }
}
