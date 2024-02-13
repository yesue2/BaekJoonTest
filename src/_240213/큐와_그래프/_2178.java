package _240213.큐와_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2178 {
    static int N;
    static int M;
    static int[][] miro;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};  // x방향 배열 (좌우)
    static int[] dy = {1, -1, 0, 0};  // y방향 배열 (상하)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                miro[i][j] = tmp.charAt(j)-'0';
            }
        }
        bfs(0);
        System.out.println(miro[N-1][M-1]);
    }

    static void bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{start, start});
        visited[start][start] = true;

        while (!queue.isEmpty()) {
            // 큐에서 해당 위치 꺼내기
            int xy[] = queue.poll();
            // 해당 위치에서 상하좌우로 이동
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];  // 다음 x 좌표 계산
                int ny = xy[1] + dy[i];  // 다음 y 좌표 계산

                // 이동한 위치가 미로의 범위를 벗어나거나, 이미 방문한 위치이거나, 값이 0일 때
                if (nx < 0 || ny < 0 || nx > N || ny > M || visited[nx][ny] || miro[nx][ny] == 0)
                    continue;
                else {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    miro[nx][ny] = miro[xy[0]][xy[1]]+1;  // 이동한 위치의 값에 현재 위치까지의 거리 +1 대입
                }
            }
        }
    }
}
