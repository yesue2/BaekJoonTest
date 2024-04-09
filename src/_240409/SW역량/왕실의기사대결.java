package _240409.SW역량;

import java.io.*;
import java.util.*;

public class 왕실의기사대결 {
    static int L, N, Q, n, result, d;
    static int[][] map;
    static int[][] knightLocation;
    static int[] knightPower;
    static int[] knightPowerR;
    static int[] command;
    static int[] moveCommand;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());  // 체스판 크기
        N = Integer.parseInt(st.nextToken());  // 기사의 수
        Q = Integer.parseInt(st.nextToken());  // 명령의 수
        map = new int[L][L];  // 지도 세팅
        knightLocation = new int[L][L];  // 기사들이 위치한 지도 세팅
        knightPower = new int[N + 1];  // 각 기사의 체력 저장
        knightPowerR = new int[N + 1];  // 각 기사의 체력 복사(원본)
        command = new int[Q];  // 명령 순서 저장
        moveCommand = new int[Q + 1];  // 명령 저장

        for (int i = 0; i < L; i++) {  // <= 40
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                knightLocation[i][j] = 0;
            }
        }

        for (int i = 0; i < N; i++) {   // <= 30
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int j = r; j < r + h; j++) {  // 체스판에 기사 세팅
                for (int l = c; l < c + w; l++) {
                    knightLocation[j][l] = (i + 1) * 10;
                }
            }
            knightPower[i + 1] = k;
            knightPowerR[i + 1] = k;
        }

        for (int i = 0; i < Q; i++) {  // 명령 저장, <= 100
            st = new StringTokenizer(br.readLine());
            int knightNum = Integer.parseInt(st.nextToken());
            command[i] = knightNum;
            moveCommand[knightNum] = Integer.parseInt(st.nextToken());
            System.out.println("command의 순서 : " + i + ", 기사 순번 : " + knightNum);
        }
        for (int i = 0; i < Q; i++) {  // <= 100
            int ii = i + 1;
            System.out.println("------------명령 진행 순서 : " + ii);
            battle(i);
        }
        System.out.println(result);
    }

    static void battle(int i) {
        int knightNum = command[i];
        d = moveCommand[knightNum];
        System.out.println("움직일 방향 : " + d);
        knightMove(knightNum);

        setResult();
    }

    static void knightMove(int knightNum) {
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        List<Integer> nxList = new ArrayList<>();
        List<Integer> nyList = new ArrayList<>();
        int trueCnt = 0;

        n = knightNum * 10;
        knightSearch(knightNum);

        for (int l = 0; l < L; l++) {
            for (int m = 0; m < L; m++) {
                if (visited[l][m]) {  // 햔제 옮겨야 할 기사의 칸 리스트에 넣기
                    xList.add(l);
                    yList.add(m);
                }
            }
        }
        for (int j = 0; j < xList.size(); j++) {  // 현재 옮겨야 할 기사의 칸 하나씩 옮기며 특수 경우 확인
            System.out.print("List : " + xList.get(j) + " ");
            System.out.println(yList.get(j));
            int nx = dx[d] + xList.get(j);
            int ny = dy[d] + yList.get(j);
            System.out.print("next : " + nx + " ");
            System.out.println(ny);

            if (nx >= 0 && ny >= 0 && nx < L && ny < L) {
                if (map[nx][ny] == 2) return;  // 기사가 옮겨질 칸에 벽이 있을 때 옮기기 종료
                if (knightLocation[nx][ny] != n && knightLocation[nx][ny] != 0) {  // 기사가 옮겨질 칸에 다른 기사가 있을 때
                    nxList.add(nx);
                    nyList.add(ny);
                    int pushOutKnight = knightLocation[nx][ny] / 10;
                    System.out.println("밀려날 기사 : " + pushOutKnight);
                    n = pushOutKnight * 10;
                    if (knightPushOut(pushOutKnight)) {
                        trueCnt++;
                    }
                } else if (map[nx][ny] == 1)   // 밀려난 칸에 함정이 있을 때
                    knightPower[knightNum]--;
            }
        }
        if (trueCnt != 0) {  // 밀려나는 기사도 벽에 막히지 않으면 옮겨야 할 기사의 좌표 옮기기
            for (int i = 0; i < xList.size(); i++) {
                knightLocation[xList.get(i)][yList.get(i)] = 0;
                knightLocation[nxList.get(i)][nyList.get(i)] = knightNum * 10;
            }
        }
    }

    static void knightSearch(int knightNum) {  // 현재 옮겨야 할 기사의 칸 탐색
        for (int j = 0; j < L; j++) {
            for (int k = 0; k < L; k++) {
                if (knightLocation[j][k] == knightNum * 10) {
                    visited = new boolean[L][L];
                    dfs(j, k);
                    return;
                }
            }
        }
    }

    static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (nx >= 0 && ny >= 0 && nx < L && ny < L && knightLocation[nx][ny] == n && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }

    static boolean knightPushOut(int knightNum) {  // 밀려난 기사 처리
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        List<Integer> nxList = new ArrayList<>();
        List<Integer> nyList = new ArrayList<>();
        int trueCnt = 0;

        knightSearch(knightNum);

        for (int l = 0; l < L; l++) {
            for (int m = 0; m < L; m++) {
                if (visited[l][m]) {  // 햔제 밀려날 기사의 칸 리스트에 넣기
                    xList.add(l);
                    yList.add(m);
                }
            }
        }
        for (int j = 0; j < xList.size(); j++) {  // 현재 밀려날 기사의 칸 하나씩 옮기며 특수 경우 확인
            System.out.print("push List : " + xList.get(j) + " ");
            System.out.println(yList.get(j));
            int nx = dx[d] + xList.get(j);
            int ny = dy[d] + yList.get(j);
            System.out.print("push next : " + nx + " ");
            System.out.println(ny);

            if (nx >= 0 && ny >= 0 && nx < L && ny < L) {
                if (map[nx][ny] == 2) return false;  // 기사가 밀려날 칸에 벽이 있을 때 옮기기 종료
                if (knightLocation[nx][ny] != n && knightLocation[nx][ny] != 0) {  // 기사가 밀려날 칸에 다른 기사가 있을 때
                    int pushOutKnight = knightLocation[nx][ny] / 10;
                    nxList.add(nx);
                    nyList.add(ny);
                    n = pushOutKnight * 10;
                    if (knightPushOut(pushOutKnight)) {
                        trueCnt++;
                    }
                }
            }
        }
        if (trueCnt != 0) {  // 밀려나는 기사도 벽에 막히지 않으면 옮겨야 할 기사의 좌표 옮기기
            for (int i = 0; i < xList.size(); i++) {
                knightLocation[xList.get(i)][yList.get(i)] = 0;
                knightLocation[nxList.get(i)][nyList.get(i)] = knightNum * 10;
                if (map[nxList.get(i)][nyList.get(i)] == 1)   // 밀려난 칸에 함정이 있을 때
                    knightPower[knightNum]--;
            }
        }
        System.out.println("밀려난 기사의 체력 : " + knightPower[knightNum]);
        if (knightPower[knightNum] <= 0) {  // 함정에 의해 k가 0이하로 떨어지면 삭제
            for (int j = 0; j < xList.size(); j++) {
                int cx = xList.get(j);
                int cy = yList.get(j);
                knightLocation[cx][cy] = 0;
            }
        }
        return true;
    }

    static void setResult() {
        List<Integer> exist = new ArrayList<>();
        result = 0;

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (knightLocation[i][j] != 0 && !exist.contains(knightLocation[i][j] / 10)) {
                    exist.add(knightLocation[i][j] / 10);
                }
            }
        }
        for (int i = 0; i < exist.size(); i++) {
            int knightNum = exist.get(i);
            result = result + (knightPowerR[knightNum] - knightPower[knightNum]);
        }
    }
}
