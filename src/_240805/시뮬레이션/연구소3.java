package _240805.시뮬레이션;

import java.io.*;
import java.util.*;

public class 연구소3 {
    static class Virus {
        int x, y, time;

        Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int N, M;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static List<Virus> viruses = new ArrayList<>();
    static Virus[] active;
    static int resultMinTime = Integer.MAX_VALUE;
    static int originEmptySpace = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        active = new Virus[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 0) {
                    originEmptySpace++;
                } else if (arr[i][j] == 2) {
                    viruses.add(new Virus(i, j, 0));
                }
            }
        }

        // solution
        if (originEmptySpace == 0) {
            System.out.println(0);
        } else {
            selectVirus(0, 0);
            System.out.println(resultMinTime == Integer.MAX_VALUE ? -1 : resultMinTime);
        }
    }

    // 백트래킹으로 M 개의 바이러스를 선택하는 Combination 구현
    static void selectVirus(int start, int selectCount) {
        if (selectCount == M) {
            spreadVirus(originEmptySpace);
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            active[selectCount] = viruses.get(i);
            selectVirus(i + 1, selectCount + 1);
        }
    }

    // BFS 로 바이러스를 퍼트린다
    static void spreadVirus(int emptySpace) {
        Queue<Virus> q = new LinkedList<>();
        boolean[][] infected = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            Virus virus = active[i];
            infected[virus.x][virus.y] = true;
            q.add(virus);
        }

        while (!q.isEmpty()) {
            Virus virus = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = virus.x + dx[i];
                int ny = virus.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (infected[nx][ny] || arr[nx][ny] == 1) continue;

                if (arr[nx][ny] == 0) {
                    emptySpace--;
                }

                if (emptySpace == 0) {
                    resultMinTime = Math.min(resultMinTime, virus.time + 1);
                    return;
                }

                infected[nx][ny] = true;
                q.add(new Virus(nx, ny, virus.time + 1));
            }
        }
    }
}
