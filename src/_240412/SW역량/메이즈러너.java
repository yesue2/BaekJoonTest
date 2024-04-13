package _240412.SW역량;

import java.io.*;
import java.util.*;

public class 메이즈러너 {
    static class Participant {
        int x, y;

        Participant(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, K, result, msx, msy, mlx, mly;
    static int[][] miro;
    static List<Participant> pList;
    static List<Participant>[][] runner;
    static int[] exit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        miro = new int[N][N];  // 미로 저장
        pList = new ArrayList<>();  // 참가자 클래스의 객체들 저장
        runner = new ArrayList[N][N];  // 미로의 각 칸에 참가자 저장
        exit = new int[2];  // 출구 좌표 저장

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                miro[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            pList.add(new Participant(x, y));
        }
        st = new StringTokenizer(br.readLine());
        exit[0] = Integer.parseInt(st.nextToken()) - 1;
        exit[1] = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < runner.length; i++) {
            for (int j = 0; j < runner[i].length; j++) {
                runner[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < pList.size(); i++) {
            Participant participant = pList.get(i);
            int x = participant.x;
            int y = participant.y;
            runner[x][y].add(participant);
        }

        for (int i = 0; i < runner.length; i++) {
            for (int j = 0; j < runner[i].length; j++) {
                if (!runner[i][j].isEmpty()) {
                    System.out.println("참가자의 i, j : " + i + " " + j);
                }
            }
        }

        for (int i = 0; i < K; i++) {
            if(!running()) {
                break;
            }
        }
        System.out.println(result);
        System.out.println(exit[0] + " " + exit[1]);
    }

    static boolean running() {
        result = 0;
        visited = new boolean[N][N];
        move();  // 참가자 이동
        System.out.println("result : " + result);

        if (!rotation()) {
            return false;
        }
        return true;
    }

    static void move() {
        for (int i = 0; i < pList.size(); i++) {
            Participant participant = pList.get(i);
            int cx = participant.x;
            int cy = participant.y;
            List<Integer> d = new ArrayList<>();  // 최단거리가 두 개 이상일 때 이동방향 저장

            for (int j = 0; j < 4; j++) {
                int nx = dx[j] + cx;
                int ny = dy[j] + cy;

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && miro[nx][ny] == 0
                        && Math.abs(cx - exit[0]) + Math.abs(cy - exit[1]) > Math.abs(nx - exit[0]) + Math.abs(ny - exit[1])) {
                    d.add(j);
                }
            }
            if (d.size() > 1) {  // 최단거리가 여러개일 때
                for (int j = 0; j < d.size(); j++) {   // 최단거리 상하로 움직이는 것부터 선택
                    if (d.get(j) == 0) {
                        setMove(participant, 0);
                        break;
                    } else if (d.get(j) == 1) {
                        setMove(participant, 1);
                        break;
                    } else {  // 좌우만 있을 땐 어떻게 해야되나
                        setMove(participant, d.get(j));
                        break;
                    }
                }
            } else if (d.size() == 1) {  // 최단거리가 하나일 때
                System.out.println(d.get(0));
                setMove(participant, d.get(0));

            }
        }
    }

    static void setMove(Participant participant, int i) {
        runner[participant.x][participant.y].remove(participant);
        participant.x += dx[i];
        participant.y += dx[i];
        System.out.println(participant.x + " " + participant.y);
        runner[participant.x][participant.y].add(participant);
        result++;
        System.out.println("움직인 방향 : " + i);
    }

    static boolean rotation() {
        setMinSquare();  // 가장 작은 정사각형 선택
        setRotation();  // 90도 회전
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!runner[i][j].isEmpty() && i == exit[0] && j == exit[1]) {
                    runner[i][j].removeIf(participant -> true);
                } else if (!runner[i][j].isEmpty()) {
                    cnt++;
                }
            }
        }
        if (cnt == 0) {
            return false;
        }
        return true;
    }

    static void setMinSquare() {
        for (int i = 1; i < N; i++) {  // 사각형의 크기
            for (int sx = 0; sx < N - i; sx++) {   // 시작점의 x
                int lx = sx + i;   // 끝점의 x

                for (int sy = 0; sy < N - i; sy++) {   // 시작점의 y
                    int ly = sy + i;   // 끝점의 y
                    if (exit[0] >= sx && exit[1] >= sy && exit[0] <= lx && exit[1] <= ly) {  // 출구가 사각형 안에 있을 때
                        for (int tsx = sx; tsx <= lx; tsx++) {
                            for (int tsy = sy; tsy <= ly; tsy++) {
                                if (!runner[tsx][tsy].isEmpty()) {  // 한 명 이상의 참가자가 사각형 안에 있을 때
                                    msx = sx;
                                    msy = sy;
                                    mlx = lx;
                                    mly = ly;
                                    System.out.println("msx, msy, mlx, mly : " + msx + " " + msy + " " + mlx + " " + mly + " ");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    static void setRotation() {
        int[][] exitMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == exit[0] && j == exit[1]) {
                    exitMap[i][j] = 1;
                } else
                    exitMap[i][j] = 0;
            }
        }

        int length = mlx - msx + 1;  // 정사각형 한 변의 길이
        int tmpMiro;
        int tmpExit;
        List<Participant> tmpRunner;
        int bx;
        int by;
        int ax;
        int ay;
        for (int i = 0; i < length / 2; i++) {
            int tsx = msx + i;
            int tsy = msy + i;
            for (int j = 0; j < length - 1; j++) {
                tmpMiro = miro[tsx][tsy];
                tmpExit = exitMap[tsx][tsy];
                tmpRunner = runner[tsx][tsy];
                runner[tsx][tsy] = new ArrayList<>();

                for (int k = 0; k < length - 1; k++) {
                    bx = tsx + k + 1;
                    by = tsy;
                    ax = tsx + k;
                    ay = tsy;
                    miro[ax][ay] = miro[bx][by];
                    exitMap[ax][ay] = exitMap[bx][by];
                    runner[ax][ay] = runner[bx][by];
                }
                for (int k = 0; k < length - 1; k++) {
                    bx = tsx + length - 1;
                    by = tsy + k + 1;
                    ax = tsx + length - 1;
                    ay = tsy + k;
                    miro[ax][ay] = miro[bx][by];
                    exitMap[ax][ay] = exitMap[bx][by];
                    runner[ax][ay] = runner[bx][by];
                }
                for (int k = 0; k < length - 1; k++) {
                    bx = tsx + length - 2 - k;
                    by = tsy + length - 1;
                    ax = tsx + length - 1 - k;
                    ay = tsy + length - 1;
                    miro[ax][ay] = miro[bx][by];
                    exitMap[ax][ay] = exitMap[bx][by];
                    runner[ax][ay] = runner[bx][by];
                }
                for (int k = 0; k < length - 2; k++) {
                    bx = tsx;
                    by = tsy + length - 2 - k;
                    ax = tsx;
                    ay = tsy + length - 1 - k;
                    miro[ax][ay] = miro[bx][by];
                    exitMap[ax][ay] = exitMap[bx][by];
                    runner[ax][ay] = runner[bx][by];
                }
                miro[tsx][tsy + 1] = tmpMiro;
                exitMap[tsx][tsy + 1] = tmpExit;
                runner[tsx][tsy + 1] = tmpRunner;
            }
            for (int j = 0; j < N; j++) {   // 내구력 -1
                for (int k = 0; k < N; k++) {
                    if (miro[j][k] != 0) {
                        miro[j][k]--;
                    }
                    if (exitMap[j][k] == 1) {
                        exit[0] = j;
                        exit[1] = k;
                    }
                }
            }
            for (int k = 0; k < N; k++) {
                for (int l = 0; l < N; l++) {
                    System.out.print(miro[k][l] + " ");
                }
                System.out.println();
            }
            System.out.println("exit : " + exit[0] + " " + exit[1]);
            for (int k = 0; k < runner.length; k++) {
                for (int j = 0; j < runner[k].length; j++) {
                    if (!runner[k][j].isEmpty()) {
                        System.out.println("참가자의 i, j : " + k + " " + j);
                    }
                }
            }
        }
    }
}
