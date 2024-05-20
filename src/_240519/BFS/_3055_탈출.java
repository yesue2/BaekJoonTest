package _240519.BFS;

import java.io.*;
import java.util.*;

public class _3055_탈출 {
    static int R, C;
    static char[][] arr;
    static int[][] waterTime, hedgehogTime;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> waterQueue = new LinkedList<>();
    static Queue<int[]> hedgehogQueue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        waterTime = new int[R][C];
        hedgehogTime = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
                waterTime[i][j] = -1;
                hedgehogTime[i][j] = -1;

                if (arr[i][j] == '*') {  // 물이 찬 지역이면
                    waterQueue.offer(new int[]{i, j});
                    waterTime[i][j] = 0;
                } else if (arr[i][j] == 'S') {  // 고슴도치가 있으면
                    hedgehogQueue.offer(new int[]{i, j});
                    hedgehogTime[i][j] = 0;
                }
            }
        }

        spreadWater();
        int result = moveHedgehog();

        if (result == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(result);
        }
    }

    static void spreadWater() {
        while (!waterQueue.isEmpty()) {
            int[] current = waterQueue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // waterTime으로 방문 확인
                if (nx >= 0 && ny >= 0 && nx < R && ny < C && arr[nx][ny] == '.' && waterTime[nx][ny] == -1) {
                    waterQueue.offer(new int[]{nx, ny});
                    waterTime[nx][ny] = waterTime[x][y] + 1;
                }
            }
        }
    }

    static int moveHedgehog() {
        while (!hedgehogQueue.isEmpty()) {
            int[] current = hedgehogQueue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                    if (arr[nx][ny] == 'D') {  // nx, ny가 비버의 굴일 때
                        return hedgehogTime[x][y] + 1;
                    }

                    // hedgehogTime으로 방문 확인
                    if (arr[nx][ny] == '.' && hedgehogTime[nx][ny] == -1) {
                        // 고슴도치가 이동하려는 위치가 비어있는지 || 고슴도치가 이동하려는 위치에 물이 퍼지기 전에 도착할 수 있는지 확인
                        if (waterTime[nx][ny] == -1 || hedgehogTime[x][y] + 1 < waterTime[nx][ny]) {
                            hedgehogQueue.offer(new int[]{nx, ny});
                            hedgehogTime[nx][ny] = hedgehogTime[x][y] + 1;
                        }
                    }
                }
            }
        }

        return -1; // 고슴도치가 비버의 굴에 도달할 수 없을 때
    }
}
