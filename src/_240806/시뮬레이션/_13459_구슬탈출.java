package _240806.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _13459_구슬탈출 {
    static class State {
        int rx, ry, bx, by, depth;

        State(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.depth = depth;
        }
    }

    static int n, m;
    static char[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];

        State startState = null;
        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            board[i] = tmp.toCharArray();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (board[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        startState = new State(rx, ry, bx, by, 1);
        System.out.println(bfs(startState));
    }

    static int bfs(State start) {
        Queue<State> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][m][n][m];
        q.offer(start);
        visited[start.rx][start.ry][start.bx][start.by] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            // 종료 조건: 기울이는 동작 10번 초과일 때
            if (cur.depth > 10) return 0;

            for (int i = 0; i < 4; i++) {
                int[] nR = move(cur.rx, cur.ry, dx[i], dy[i]);
                int[] nB = move(cur.bx, cur.by, dx[i], dy[i]);

                // 움직인 구슬의 위치
                int nRx = nR[0], nRy = nR[1];
                int nBx = nB[0], nBy = nB[1];
                // 구슬의 움직인 거리
                int rDist = nR[2], bDist = nB[2];

                // 파란 구슬이 구멍에 빠질 때 실패
                if (board[nBx][nBy] == 'O') continue;
                // 빨간 구슬이 구멍에 빠질 때 성공
                if (board[nRx][nRy] == 'O') return 1;

                // 두 구슬이 같은 위치에 있을 때, 더 멀리 이동한 구슬을 한 칸 뒤로 이동
                if (nRx == nBx && nRy == nBy) {
                    if (rDist > bDist) {
                        nRx -= dx[i];
                        nRy -= dy[i];
                    } else {
                        nBx -= dx[i];
                        nBy -= dy[i];
                    }
                }

                if (!visited[nRx][nRy][nBx][nBy]) {
                    visited[nRx][nRy][nBx][nBy] = true;
                    q.offer(new State(nRx, nRy, nBx, nBy, cur.depth + 1));
                }
            }
        }

        return 0;
    }

    // 구슬이 직선으로 갈 수 있는 만큼 움직이기 return new int[]{움직인 구슬의 x, 움직인 구슬의 y, 움직인 거리}
    static int[] move(int x, int y, int dx, int dy) {
        int dist = 0;

        while (board[x + dx][y + dy] != '#' && board[x][y] != 'O') {
            x += dx;
            y += dy;
            dist++;
        }

        return new int[]{x, y, dist};
    }
}
