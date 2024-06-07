package _240604.SW역량;

import java.io.*;
import java.util.*;

//class Golrem {
//    List<Golrem> xy;
//
//
//    public Golrem(int) {
//
//    }
//}


public class 마법의숲탐색 {
    static int R, C, K;
    static int[][] arr, forest;
    static int[] dx = {1, 0, 0};
    static int[] dy = {0, -1, 1};
    static int[][] gol;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[K][2];
        forest = new int[R + 3][C];
        gol = new int[5][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        mvGolrem();

    }

    static void mvGolrem() {
        for (int i = 0; i < K; i++) {
            setGolrem(i);
        }
    }

    static void setGolrem(int i) {
        int r = arr[i][0];
        gol[0][0] = 2;
        gol[0][1] = r;
        gol[1][0] = 1;
        gol[1][1] = r - 1;
        gol[2][0] = 0;
        gol[2][1] = r;
        gol[3][0] = 1;
        gol[3][1] = r + 1;
        gol[4][0] = 1;
        gol[4][1] = r;

        while (true) {
            for (int j = 0; j < gol.length; j++) {
                int nj = j + 1;
                if (gol[nj][0] == -1) {

                }
            }
        }
    }
}
