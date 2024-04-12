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

    static int N, M, K, result;
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

        for (int i = 0; i < pList.size(); i++) {
            Participant participant = pList.get(i);
            int x = participant.x;
            int y = participant.y;
            if (runner[x][y] == null)
                runner[x][y] = new ArrayList<>();
            runner[x][y].add(participant);
        }

        for (int i = 0; i < runner.length; i++) {
            for (int j = 0; j < runner[i].length; j++) {
                if (runner[i][j] != null) {
                    System.out.println(i + " " + j);
                }
            }
        }
        running();
        System.out.println(result);
    }

    static void running() {
        result = 0;
        visited = new boolean[N][N];
        move();  // 참가자 이동
        System.out.println("result : " +  result);

        rotation();  // 미로 회전
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
                        result++;
                        break;
                    } else if (d.get(j) == 1) {
                        setMove(participant, 1);
                        result++;
                        break;
                    }  // 좌우만 있을 땐 어떻게 해야되나
                }
            } else if (d.size() == 1) {  // 최단거리가 하나일 때
                setMove(participant, d.get(0));
                result++;
            }
        }
    }
    static void setMove(Participant participant, int i) {
        participant.x += dx[i];
        participant.y += dx[i];
        runner[participant.x][participant.y].remove(participant);
        if (runner[participant.x][participant.y] == null)
            runner[participant.x][participant.y] = new ArrayList<>();
        runner[participant.x][participant.y].add(participant);
        System.out.println("움직인 방향 : " + i);
    }

    static void rotation() {

    }
}
