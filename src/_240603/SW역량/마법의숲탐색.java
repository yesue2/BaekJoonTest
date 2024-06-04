package _240603.SW역량;

import java.io.*;
import java.util.*;

public class 마법의숲탐색 {
    static int R, C, K;
    static int[][] arr, forest;
    static int[] dx = {1, 0, 0};
    static int[] dy = {0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[K][2];
        forest = new int[R][C];

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

    }
}
