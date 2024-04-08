package _240407.SW역량;

import java.io.*;
import java.util.*;

public class _14890_경사로 {
    static int N, L;
    static int[][] map;
    static int dNum, rNum, low, downRes, rightRes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        downRes = 0;
        rightRes = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        search();
        int result = downRes + rightRes;
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }

    static void search() {
        for (int start = 0; start < N; start++) {
            if (down(start)) {
                downRes++;
            }
            if (right(start)) {
                rightRes++;
            }
        }
    }

    static boolean down(int start) {
        int cnt = 1;
        int y = 1;
        dNum = map[0][start];

        while (y < N) {
            if (dNum != map[y][start]) {
                if (Math.abs(map[y][start] - dNum) == 1) {
                    if (dNum > map[y][start]) {
                        low = map[y][start];
                        int lowCnt = downLowCnt(start, y + 1, low);
                        if (lowCnt >= L && y + lowCnt - 1 < N) {
                            cnt = lowCnt - L;
                            y += lowCnt;
                            dNum = map[y - 1][start];
                        } else return false;
                    } else {
                        if (cnt >= L) {
                            cnt = 1;
                            y += 1;
                            dNum = map[y - 1][start];
                        } else return false;
                    }
                } else return false;
            } else {
                cnt++;
                y++;
            }
        }
        return true;
    }


    static int downLowCnt(int i, int y, int low) {
        int lowCnt = 1;
        if (y >= N)
            return lowCnt;
        while (low == map[y][i]) {
            lowCnt++;
            y++;
            if (y >= N)
                break;
        }
        return lowCnt;
    }

    static boolean right(int start) {
        int cnt = 1;
        int x = 1;
        rNum = map[start][0];
        while (x < N) {
            if (rNum != map[start][x]) {
                if (Math.abs(map[start][x] - rNum) == 1) {
                    if (rNum > map[start][x]) {
                        low = map[start][x];
                        int lowCnt = rightLowCnt(start, x + 1, low);
                        if (lowCnt >= L && x + lowCnt - 1 < N) {
                            cnt = lowCnt - L;
                            x += lowCnt;
                            rNum = map[start][x - 1];
                        } else return false;
                    } else {
                        if (cnt >= L) {
                            cnt = 1;
                            x += 1;
                            rNum = map[start][x - 1];
                        } else return false;
                    }
                } else return false;
            } else {
                cnt++;
                x++;
            }
        }
        return true;
    }

    static int rightLowCnt(int i, int x, int low) {
        int lowCnt = 1;
        if (x >= N)
            return lowCnt;
        while (low == map[i][x]) {
            lowCnt++;
            x++;
            if (x >= N)
                break;
        }
        return lowCnt;
    }
}
