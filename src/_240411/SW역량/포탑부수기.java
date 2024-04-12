package _240411.SW역량;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// class turret을 정의해 관리합니다.
class Turret implements Comparable<Turret> {
    int x, y, r, p;

    public Turret(int x, int y, int r, int p) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.p = p;
    }

    // turret의 약함, 강함 우선순위에 맞게 정렬함수를 만들어줍니다.
    public int compareTo(Turret t) {
        if(this.p != t.p) return this.p - t.p;
        if(this.r != t.r) return t.r - this.r;
        if(this.x + this.y != t.x + t.y) return (t.x + t.y) - (this.x + this.y);
        return t.y - this.y;
    }
};

public class 포탑부수기 {
    public static final int MAX_N = 10;

    public static int[] dx = new int[]{0, 1, 0, -1};
    public static int[] dy = new int[]{1, 0, -1, 0};
    public static int[] dx2 = new int[]{0, 0, 0, -1, -1, -1, 1, 1, 1};
    public static int[] dy2 = new int[]{0, -1, 1, 0, -1, 1, 0, -1, 1};

    public static int n, m, k;
    public static int turn;

    // 현재 포탑들이 가진 힘과 언제 각성했는지 기록해줍니다.
    public static int[][] board = new int[MAX_N][MAX_N];
    public static int[][] rec = new int[MAX_N][MAX_N];

    // 빛의 공격을 할 때 방문 여부와 경로 방향을 기록해줍니다.
    public static boolean[][] vis = new boolean[MAX_N][MAX_N];
    public static int[][] backX = new int[MAX_N][MAX_N];
    public static int[][] backY = new int[MAX_N][MAX_N];

    // 공격과 무관했는지 여부를 저장합니다.
    public static boolean[][] isActive = new boolean[MAX_N][MAX_N];

    // 살아있는 포탑들을 관리합니다.
    public static ArrayList<Turret> liveTurret = new ArrayList<>();

    // 턴을 진행하기 전 필요한 전처리를 정리해줍니다.
    public static void init() {
        turn++;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                vis[i][j] = false;
                isActive[i][j] = false;
            }
    }

    // 각성을 진행합니다.
    // 각성을 하면 가장 약한 포탑이 n + m만큼 강해집니다.
    public static void awake() {
        // 우선순위에 맞게 현재 살아있는 포탑들을 정렬해줍니다.
        Collections.sort(liveTurret);

        // 가장 약한 포탑을 찾아 n + m만큼 더해주고,
        // isActive와 liveTurret 배열도 갱신해줍니다.
        Turret weakTurret = liveTurret.get(0);
        int x = weakTurret.x;
        int y = weakTurret.y;

        board[x][y] += n + m;
        rec[x][y] = turn;
        weakTurret.p = board[x][y];
        weakTurret.r = rec[x][y];
        isActive[x][y] = true;

        liveTurret.set(0, weakTurret);
    }

    // 레이저 공격을 진행합니다.
    public static boolean laserAttack() {
        // 기존에 정렬된 가장 앞선 포탑이
        // 각성한 포탑입니다.
        Turret weakTurret = liveTurret.get(0);
        int sx = weakTurret.x;
        int sy = weakTurret.y;
        int pow = weakTurret.p;

        // 기존에 정렬된 가장 뒤 포탑이
        // 각성한 포탑을 제외한 포탑 중 가장 강한 포탑입니다.
        Turret strongTurret = liveTurret.get(liveTurret.size() - 1);
        int ex = strongTurret.x;
        int ey = strongTurret.y;

        // bfs를 통해 최단경로를 관리해줍니다.
        Queue<Pair> q = new LinkedList<>();
        vis[sx][sy] = true;
        q.add(new Pair(sx, sy));

        // 가장 강한 포탑에게 도달 가능한지 여부를 canAttack에 관리해줍니다.
        boolean canAttack = false;

        while(!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();

            // 가장 강한 포탑에게 도달할 수 있다면
            // 바로 멈춥니다.
            if(x == ex && y == ey) {
                canAttack = true;
                break;
            }

            // 각각 우, 하, 좌, 상 순서대로 방문하며 방문 가능한 포탑들을 찾고
            // queue에 저장해줍니다.
            for(int dir = 0; dir < 4; dir++) {
                int nx = (x + dx[dir] + n) % n;
                int ny = (y + dy[dir] + m) % m;

                // 이미 방문한 포탑이라면 넘어갑니다.
                if(vis[nx][ny])
                    continue;

                // 벽이라면 넘어갑니다.
                if(board[nx][ny] == 0)
                    continue;

                vis[nx][ny] = true;
                backX[nx][ny] = x;
                backY[nx][ny] = y;
                q.add(new Pair(nx, ny));
            }
        }

        // 만약 도달 가능하다면 공격을 진행합니다.
        if(canAttack) {
            // 우선 가장 강한 포탑에게는 pow만큼의 공격을 진행합니다.
            board[ex][ey] -= pow;
            if(board[ex][ey] < 0)
                board[ex][ey] = 0;
            isActive[ex][ey] = true;

            // 기존의 경로를 역추적하며
            // 경로 상에 있는 모든 포탑에게 pow / 2만큼의 공격을 진행합니다.
            int cx = backX[ex][ey];
            int cy = backY[ex][ey];

            while(!(cx == sx && cy == sy)) {
                board[cx][cy] -= pow / 2;
                if(board[cx][cy] < 0)
                    board[cx][cy] = 0;
                isActive[cx][cy] = true;

                int nextCx = backX[cx][cy];
                int nextCy = backY[cx][cy];

                cx = nextCx;
                cy = nextCy;
            }
        }

        // 공격을 성공했는지 여부를 반환합니다.
        return canAttack;
    }

    // 레이저 공격을 하지 못했다면 폭탄 공격을 진행합니다.
    public static void bombAttack() {
        // 기존에 정렬된 가장 앞선 포탑이
        // 각성한 포탑입니다.
        Turret weakTurret = liveTurret.get(0);
        int sx = weakTurret.x;
        int sy = weakTurret.y;
        int pow = weakTurret.p;

        // 기존에 정렬된 가장 뒤 포탑이
        // 각성한 포탑을 제외한 포탑 중 가장 강한 포탑입니다.
        Turret strongTurret = liveTurret.get(liveTurret.size() - 1);
        int ex = strongTurret.x;
        int ey = strongTurret.y;

        // 가장 강한 포탑의 3 * 3 범위를 모두 탐색하며
        // 각각에 맞는 공격을 진행합니다.
        for(int dir = 0; dir < 9; dir++) {
            int nx = (ex + dx2[dir] + n) % n;
            int ny = (ey + dy2[dir] + m) % m;

            // 각성한 포탑 자기 자신일 경우 넘어갑니다.
            if(nx == sx && ny == sy)
                continue;

            // 가장 강한 포탑일 경우 pow만큼의 공격을 진행합니다.
            if(nx == ex && ny == ey) {
                board[nx][ny] -= pow;
                if(board[nx][ny] < 0)
                    board[nx][ny] = 0;
                isActive[nx][ny] = true;
            }
            // 그 외의 경우 pow / 2만큼의 공격을 진행합니다.
            else {
                board[nx][ny] -= pow / 2;
                if(board[nx][ny] < 0)
                    board[nx][ny] = 0;
                isActive[nx][ny] = true;
            }
        }
    }

    // 공격에 관여하지 않은 모든 살아있는 포탑의 힘을 1 증가시킵니다.
    public static void reserve() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(isActive[i][j])
                    continue;
                if(board[i][j] == 0)
                    continue;
                board[i][j]++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                board[i][j] = sc.nextInt();
        int K = k;

        // k턴 동안 진행됩니다.
        while(k-- > 0) {
            // 턴을 진행하기 전 살아있는 포탑을 정리합니다.
            liveTurret = new ArrayList<>();
            for(int i = 0; i < n; i++)
                for(int j = 0; j < m; j++)
                    if(board[i][j] > 0) {
                        Turret newTurret = new Turret(i, j, rec[i][j], board[i][j]);
                        liveTurret.add(newTurret);
                    }

            // 살아있는 포탑이 1개 이하라면 바로 종료합니다.
            if(liveTurret.size() <= 1)
                break;

            // 턴을 진행하기 전 필요한 전처리를 정리해줍니다.
            init();

            // 각성을 진행합니다.
            awake();

            // 레이저 공격을 진행합니다.
            boolean isSuc = laserAttack();
            // 레이저 공격을 하지 못했다면 포탄 공격을 진행합니다.
            if(!isSuc)
                bombAttack();

            // 공격에 관여하지 않은 모든 살아있는 포탑의 힘을 1 증가시킵니다.
            reserve();

            System.out.println("------------------" + (k-K+1) + "번째 공격");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            };
        }

        // 살아있는 포탑의 힘 중 가장 큰 값을 출력합니다.
        int ans = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                ans = Math.max(ans, board[i][j]);

        System.out.print(ans);
    }
}