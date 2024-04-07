package _240406.SW역량;

import java.io.*;
import java.util.*;

public class _23290_마법사상어와복제 {
    static class Fish {
        int x, y, d;
        Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    static int[] fdx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] fdy = {0, 0, 1, 1, 1, 0, -1, -1, -1};
    static int[] sdx = {0, -1, 0, 1};
    static int[] sdy = {1, 0, -1, 0};
    static ArrayList<Fish>[][] map;
    static ArrayList<Fish> list;
    static int sx, sy, S, run, dSum, result;
    static int[][] smell;
    static boolean[][] visited;
    static int[] maxDelFish;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        smell = new int[4][4];
        map = new ArrayList[4][4];
        list = new ArrayList<>();
        visited = new boolean[4][4];
        maxDelFish = new int[2];
        maxDelFish[0] = Integer.MAX_VALUE;
        maxDelFish[1] = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                smell[i][j] = -3;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new Fish(x, y, d));
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;

        simulation();
        System.out.println(result);
    }

    static void simulation() {
        for (run = 0; run < S; run++) {
            ArrayList<Fish> copy = list;

            for (int i = 0; i < list.size(); i++) {
                Fish cur = list.get(i);
                cur = fishMove(cur);
                map[cur.x][cur.y].add(cur);
            }
            sharkMove(0, 0);
            sharkMapMove();
            removeSmell();
            setCopyMap(copy);
            reset();
        }
    }
    static Fish fishMove(Fish cur) {
        int fcx = cur.x;
        int fcy = cur.y;
        int fcd = cur.d + 1;
        int fnx, fny;

        if (checkFishMove(fcx, fcy))
            return cur;
        else {
            for (int i = 0; i < 8; i++) {
                fcd -= 1;
                if (fcd == 1) {
                    fcd = 8;
                    fnx = fdx[8] + fcx;
                    fny = fdy[8] + fcy;
                } else {
                    fnx = fdx[fcd] + fcx;
                    fny = fdy[fcd] + fcy;
                }
                if (checkFishMove(fnx, fny)) {
                    cur.x = fnx;
                    cur.y = fny;
                    cur.d = fcd;
                    break;
                }
            }
        }
        return cur;
    }
    static boolean checkFishMove(int i, int j) {
        if (i < 0 || i >= 4 || j < 0 || j >= 4 || (i == sx && j == sy) || smell[i][j] != -3)
            return false;
        else
            return true;
    }

    static void sharkMove(int cnt, int fishCnt) {
        if (cnt == 3) {
            if (fishCnt == maxDelFish[1] && maxDelFish[0] > dSum)
                maxDelFish[0] = dSum;
            if (fishCnt > maxDelFish[1]) {
                maxDelFish[0] = dSum;
                maxDelFish[1] = fishCnt;
            }
            dSum = 0;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int snx = sdx[i] + sx;
            int sny = sdy[i] + sy;

            if (snx > 0 && sny > 0 && snx < 4 && sny < 4 && !visited[snx][sny]) {
                visited[snx][sny] = true;
                if (cnt == 0)
                    dSum = (i + 1) * 100;
                else if (cnt == 1)
                    dSum = dSum + (i + 1) * 10;
                else dSum = dSum + i + 1;

                if (!map[snx][sny].isEmpty()) {
                    smell[snx][sny] = run;
                    deleteFish(snx, sny);
                    sharkMove(cnt + 1, fishCnt + map[snx][sny].size());
                    visited[snx][sny] = false;
                } else {
                    sharkMove(cnt + 1, fishCnt);
                    visited[snx][sny] = false;
                }
            }
        }
    }

    static void deleteFish(int x, int y) {
        for (int i = 0; i < list.size(); i++) {
            Fish del = list.get(i);
            if (del.x == x && del.y == y)
                list.remove(i);
        }
    }

    static void sharkMapMove() {
        String[] str = String.valueOf(dSum).split("");
        if (dSum == 0)
            return;
        int[] d = new int[3];
        for (int i = 0; i < 3; i++) {
            d[i] = Integer.parseInt(str[i]);
            sx += sdx[d[i]];
            sy += sdy[d[i]];
        }
    }

    static void removeSmell() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smell[i][j] == run - 2)
                    smell[i][j] = -3;
            }
        }
    }
    static void setCopyMap(ArrayList<Fish> copy) {
        for (int i = 0; i < copy.size(); i++) {
            Fish cur = copy.get(i);
            map[cur.x][cur.y].add(cur);
        }
    }
    static void reset() {
        result = 0;
        list.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < map[i][j].size(); k++) {
                    Fish cur = map[i][j].get(k);
                    list.add(cur);
                    result++;
                }
                map[i][j].clear();
            }
        }
    }
}
