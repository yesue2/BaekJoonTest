package _240408.SW역량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _23288_주사위굴리기2 {
    static int N, M, K, cx, cy, d, B, C, score, result, tcx, tcy;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] dice;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cx = 0;  // 현재 x좌표
        cy = 0;  // 현재 y좌표
        d = 0;  // 방향(시작은 0(동쪽)방향으로)
        tcx = 0;  // 임시 현재 x좌표
        tcy = 0;  // 임시 현재 y좌표
        result = 0;
        map = new int[N][M];
        visited = new boolean[N][M];
        dice = new int[4][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice[0][1] = 2;
        dice[1][0] = 4;
        dice[1][1] = 1;
        dice[1][2] = 3;
        dice[2][1] = 5;
        dice[3][1] = 6;

        for (int i = 0; i < K; i++) {
            int ii = i+1;
            System.out.println("----------i :" + ii);
            move();
        }
        System.out.println(result);
    }
    static void move() {
        curLocation();  // 주사위 이동(cx, cy 설정)

        setScore(cx, cy);  // C 구해 점수 계산
        result += score;

        setDirection();  // 다음 방향 세팅
    }
    static void curLocation() {
        tcx += dx[d];
        tcy += dy[d];

        if (tcx >= M || tcy >= N || tcx < 0 || tcy < 0) {  // 길이 없으면 반대방향으로
            switch (d) {
                case 0:
                    d = 2;
                    break;
                case 1:
                    d = 3;
                    break;
                case 2:
                    d = 0;
                    break;
                case 3:
                    d = 1;
                    break;
            }
        }
        cx += dx[d];
        cy += dy[d];

        System.out.println("d : " + d);
        System.out.println("cx : " + cx);
        System.out.println("cy : " + cy);
    }
    static void setScore(int cx, int cy) {
        B = map[cx][cy];
        C = 1;

        dfs(cx, cy);  // C 개수를 세기 위해 현재 위치 주변에서 갈 수 있는 칸 탐색
        System.out.println("C : " + C);
        score = C * B;
        System.out.println("score : " + score);
    }
    static void dfs(int cx, int cy) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + cx;
            int ny = dy[i] + cy;
            if (nx < M && ny < N && nx > 0 && ny > 0 && map[nx][ny] == map[cx][cy] && !visited[nx][ny]) {
                visited[nx][ny] = true;
                C++;
                dfs(nx, ny);
                visited[nx][ny] = false;
            }
        }
    }

    static void setDirection() {
        setA();  // 이동한 방향대로 주사위 셋팅

        // A와 B 비교해 다음 이동 방향 세팅
        int A = dice[3][1];
        if (A > B) {
            if (d == 3)
                d = 1;
            else
                d += 1;
        } else if (A < B) {
            if (d == 0)
                d = 3;
            else
                d -= 1;
        }
        System.out.println("set d : " + d);
    }

    static void setA() {
        switch (d) {
            case 0:  // 동쪽 이동 시
                int tmp0 = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = dice[3][1];
                dice[3][1] = tmp0;
                break;
            case 1:  // 남쪽 이동 시
                int tmp1 = dice[0][1];
                dice[0][1] = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = tmp1;
                break;
            case 2:  // 서쪽 이동 시
                int tmp2 = dice[1][2];
                dice[1][2] = dice[3][1];
                dice[3][1] = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = tmp2;
                break;
            case 3:  // 북쪽 이동 시
                int tmp3 = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = tmp3;
                break;
        }
    }
}
