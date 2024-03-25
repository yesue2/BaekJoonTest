package _240323.소프티어;

import java.util.*;
import java.io.*;
public class 업무처리 {
    static int H, K, R;
    static int[][] task;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        task = new int[H+1][K];

        for(int i = 0; i < H+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < K; j++) {
                task[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
}
