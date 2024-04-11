package _240409.SW역량;

import java.io.*;
import java.util.*;

public class 왕실의기사대결 {
    static int L, N, Q, result, d;
    static int[][] map;
    static int[][] knightLocation;
    static int[] knightPower;
    static int[] knightPowerR;
    static HashMap<Integer, Integer> command;
    static Queue<Integer> queue;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int[] w;
    static int[] h;

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
        command = new HashMap<>();  // 명령 순서와 기사 번호 저장
        queue = new LinkedList<>();  // 방향 저장
        h = new int[N + 1];
        w = new int[N + 1];

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
            h[i + 1] = Integer.parseInt(st.nextToken());
            w[i + 1] = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for (int j = r; j < r + h[i + 1]; j++) {  // 체스판에 기사 세팅
                for (int l = c; l < c + w[i + 1]; l++) {
                    knightLocation[j][l] = (i + 1);
                }
            }
            knightPower[i + 1] = k;
            knightPowerR[i + 1] = k;
        }

        for (int i = 0; i < Q; i++) {  // 명령 저장, <= 100
            st = new StringTokenizer(br.readLine());
            int knightNum = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            command.put(i, knightNum);
            queue.add(d);
        }
        for (int i = 0; i < Q; i++) {  // <= 100
            int ii = i + 1;
            System.out.println("------------명령 진행 순서 : " + ii);
            battle(i);
        }
        System.out.println(result);
    }

    static void battle(int i) {
        int knightNum = command.get(i);  // 명령 순서에 해당하는 기사 번호 가져오기
        d = queue.poll();
        System.out.println("움직일 기사 : " + knightNum);
        System.out.println("움직일 방향 : " + d);
        knightMove(knightNum);
        setResult();
    }

    static void knightMove(int knightNum) {
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        List<Integer> nxList = new ArrayList<>();
        List<Integer> nyList = new ArrayList<>();

        if(!knightSearch(knightNum)) {  // 기사의 칸 탐색 시 이미 탈락한 기사일 때
            System.out.println("존재하지 않는 기사라서 명령 종료");
            return;
        }

        for (int i = 0; i < L; i++) {  // 옮겨질 기사의 칸 리스트에 넣기
            for (int j = 0; j < L; j++) {
                if (visited[i][j]) {
                    xList.add(i);
                    yList.add(j);
                }
            }
        }

        if (!checkWall(xList, yList)) return;

        for (int j = 0; j < xList.size(); j++) {  // 현재 옮겨야 할 기사의 칸 하나씩 옮기며 특수 경우 확인
            System.out.println("시뮬레이션 List : " + xList.get(j) + " " + yList.get(j));
            int nx = dx[d] + xList.get(j);
            int ny = dy[d] + yList.get(j);
            System.out.println("시뮬레이션 next : " + nx + " " + ny);

            if (nx >= 0 && ny >= 0 && nx < L && ny < L) {

                if (knightLocation[nx][ny] != knightNum && knightLocation[nx][ny] != 0) {  // 기사가 옮겨질 칸에 다른 기사가 있을 때
                    int pushOutKnight = knightLocation[nx][ny];
                    if (!knightPushOut(pushOutKnight)) {
                        System.out.println("밀리는 기사가 체스판을 넘어가거나 벽에 막혀 종료");
                        return;
                    } else {
                        nxList.add(nx);
                        nyList.add(ny);
                    }
                } else {
                    nxList.add(nx);
                    nyList.add(ny);
                }
            } else {
                System.out.println("체스판을 넘어가 이동 종료");
                return;
            }
        }

        setKnightLocation(knightNum, xList, yList, nxList, nyList);  // 기사의 좌표 이동하기
    }

    static boolean knightSearch(int knightNum) {  // 현재 옮겨야 할 기사의 칸 탐색
        for (int j = 0; j < L; j++) {
            for (int k = 0; k < L; k++) {
                if (knightLocation[j][k] == knightNum) {
                    visited = new boolean[L][L];
                    dfs(j, k, knightNum);
                    System.out.println("j, k : " + j + " " + k);
                    if (!visited[j][k])  // 기사가 한 칸인 경우 자기자신 방문 표시
                        visited[j][k] = true;
                    return true;
                }
            }
        }
        return false;
    }

    static void dfs(int x, int y, int knightNum) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (nx >= 0 && ny >= 0 && nx < L && ny < L && knightLocation[nx][ny] == knightNum && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, knightNum);
            }
        }
    }

    static boolean checkWall(List<Integer> xList, List<Integer> yList) {  // 밀리는 곳에 벽(2)가 있는지 확인
        for (int i = 0; i < xList.size(); i++) {
            int nx = dx[d] + xList.get(i);
            int ny = dy[d] + yList.get(i);
            System.out.println("tnx : " + nx + ", tny : " + ny);
            if (nx >= 0 && ny >= 0 && nx < L && ny < L && map[nx][ny] == 2) {
                System.out.println("벽이 있어 밀리기 종료");
                return false;
            }
        }
        return true;
    }

    static boolean knightPushOut(int knightNum) {  // 밀려난 기사 처리
        System.out.println("밀려날 기사 : " + knightNum);
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        List<Integer> nxList = new ArrayList<>();
        List<Integer> nyList = new ArrayList<>();

        knightSearch(knightNum);

        for (int i = 0; i < L; i++) {  // 밀려날 기사의 칸 리스트에 넣기
            for (int j = 0; j < L; j++) {
                if (visited[i][j]) {
                    xList.add(i);
                    yList.add(j);
                }
            }
        }

        if (!checkWall(xList, yList)) return false;

        for (int j = 0; j < xList.size(); j++) {  // 현재 밀려날 기사의 칸 하나씩 옮기며 특수 경우 확인
            int nx = dx[d] + xList.get(j);
            int ny = dy[d] + yList.get(j);
            System.out.println("시뮬레이션 밀려나는 List : " + xList.get(j) + " " + yList.get(j));
            System.out.println("시뮬레이션 밀려나는 next : " + nx + " " + ny);

            if (nx >= 0 && ny >= 0 && nx < L && ny < L) {
                if (knightLocation[nx][ny] != knightNum && knightLocation[nx][ny] != 0) {  // 기사가 밀려날 칸에 다른 기사가 있을 때
                    int pushOutKnight = knightLocation[nx][ny];
                    if (!knightPushOut(pushOutKnight)) {
                        return false;
                    } else {
                        nxList.add(nx);
                        nyList.add(ny);
                    }
                } else {
                    nxList.add(nx);
                    nyList.add(ny);
                }
            } else {  // 체스판을 벗어나서 밀렸을 때 밀리지 않고 종료
                System.out.println("체스판을 벗어나서 밀리기 종료");
                return false;
            }
        }

        setKnightLocation(knightNum, xList, yList, nxList, nyList);

        for (int i = 0; i < nxList.size(); i++) {
            if (map[nxList.get(i)][nyList.get(i)] == 1)   // 밀려난 칸에 함정이 있을 때
                knightPower[knightNum]--;
        }

        System.out.println("밀려난 기사의 체력 : " + knightPower[knightNum]);
        if (knightPower[knightNum] <= 0) {  // 함정에 의해 k가 0이하로 떨어지면 삭제
            System.out.println("삭제된 기사 : " + knightNum);
            for (int j = 0; j < nxList.size(); j++) {
                int cx = nxList.get(j);
                int cy = nyList.get(j);
                knightLocation[cx][cy] = 0;
            }
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < L; j++) {
                    System.out.print(knightLocation[i][j] + " ");
                }
                System.out.println();
            }
        }
        return true;
    }

    static void setKnightLocation(int knightNum, List<Integer> xList, List<Integer> yList, List<Integer> nxList, List<Integer> nyList) {
        // 밀려나는 기사도 벽에 막히지 않으면 옮겨야 할 기사의 좌표 옮기기
        System.out.println("현재 움직일 기사 : " + knightNum);
        for (int i = 0; i < xList.size(); i++) {
            if (xList.size() >= 2) {
                if (d == 1 && w[knightNum] > 1) {  // 이동 방향과 같은 방향으로 같은 기사가 존재할 때
                    knightLocation[xList.get(0)][yList.get(0)] = 0;
                    knightLocation[nxList.get(nxList.size() - 1)][nyList.get(nyList.size() - 1)] = knightNum;
                    System.out.println("오른쪽으로 움직여 삭제될 xList, yList : " + xList.get(0) + " " + yList.get(0));
                    System.out.println("오른쪽으로 움직여 추가될 nxList, nyList : " + nxList.get(nxList.size() - 1) + " " + nyList.get(nyList.size() - 1));
                    for (int j = 0; j < h[knightNum] - 1; j++) {
                        knightLocation[xList.get(j + w[knightNum])][yList.get(j + w[knightNum])] = 0;
                        knightLocation[nxList.get(j + w[knightNum] - 1)][nyList.get(j + w[knightNum] - 1)] = knightNum;
                        System.out.println("오른쪽으로 움직여 삭제될 xList, yList : " + xList.get(j + w[knightNum]) + " " + yList.get(j + w[knightNum]));
                        System.out.println("오른쪽으로 움직여 추가될 nxList, nyList : " + nxList.get(j + w[knightNum] - 1) + " " + nyList.get(j + w[knightNum] - 1));
                    }
                    break;
                }
                if (d == 2 && h[knightNum] > 1) {
                    for (int j = 0; j < w[knightNum]; j++) {
                        knightLocation[xList.get(j)][yList.get(j)] = 0;
                        knightLocation[nxList.get(nxList.size() - 1 - j)][nyList.get(nxList.size() - 1 - j)] = knightNum;
                        System.out.println("아래로 움직일 첫 번째 xList, yList : " + xList.get(j) + " " + yList.get(j));
                        System.out.println("아래로 움직인 마지막 nxList, nyList : " + nxList.get(nxList.size() - 1 - j) + " " + nyList.get(nxList.size() - 1 - j));
                    }
                    break;
                }
            }
            knightLocation[xList.get(i)][yList.get(i)] = 0;
            knightLocation[nxList.get(i)][nyList.get(i)] = knightNum;
            System.out.println("움직일 xList, yList : " + xList.get(i) + " " + yList.get(i));
            System.out.println("움직인 nxList, nyList : " + nxList.get(i) + " " + nyList.get(i));
        }
        for (int j = 0; j < L; j++) {
            for (int k = 0; k < L; k++) {
                System.out.print(knightLocation[j][k] + " ");
            }
            System.out.println();
        }
    }

    static void setResult() {
        List<Integer> exist = new ArrayList<>();
        result = 0;

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (knightLocation[i][j] != 0 && !exist.contains(knightLocation[i][j])) {
                    exist.add(knightLocation[i][j]);  // 마지막까지 체스판에 존재하는 기사 번호 추출
                    System.out.println("존재하는 기사 : " + knightLocation[i][j]);
                }
            }
        }
        for (int i = 0; i < exist.size(); i++) {
            int knightNum = exist.get(i);
            result = result + (knightPowerR[knightNum] - knightPower[knightNum]);
        }
        System.out.println(result);
    }
}
