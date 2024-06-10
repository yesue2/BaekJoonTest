package _240610.SW역량;

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
    static int[][] forest, cd;
    static int[] dx = {1, 0, 0};
    static int[] dy = {0, -1, 1};
    static int[][] gol;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        forest = new int[R + 3][C];
        gol = new int[5][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            cd[i][0] = Integer.parseInt(st.nextToken());  // c
            cd[i][1] = Integer.parseInt(st.nextToken());  // d
        }
        mvGolrem();
    }

    static void mvGolrem() {

    }

    static void setGolrem() {
        List<int[]> recent = new ArrayList<>();

    }
}
