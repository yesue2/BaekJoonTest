package _240814.다익스트라;

import java.io.*;
import java.util.*;

public class _1261_알고스팟 {
    static int m, n;
    static int[][] miro;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        miro = new int[n][m];
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            char[] tmpCh = tmp.toCharArray();
            for (int j = 0; j < m; j++) {
                miro[i][j] = tmpCh[j] - '0';
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Deque<int[]> dq = new ArrayDeque<>();
        int[][] dist = new int[n][m];  // 출발점에서 각 위치까지 최소 벽 부수기 횟수 저장
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

                int nDist = dist[xy[0]][xy[1]] + miro[nx][ny];

                if (nDist < dist[nx][ny]) {
                    dist[nx][ny] = nDist;
                    // 빈 방(0)은 덱의 앞쪽에 삽입하고, 벽(1)은 덱의 뒤쪽에 삽입 => 벽을 적게 부순 경로가 우선적으로 탐색됨
                    if (miro[nx][ny] == 0) dq.offer(new int[]{nx, ny});
                    else dq.offerLast(new int[]{nx, ny});
                }
            }
        }
        return dist[n - 1][m - 1];
    }
}
