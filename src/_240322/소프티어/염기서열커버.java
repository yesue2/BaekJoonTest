package _240322.소프티어;

import java.util.*;
import java.io.*;
public class 염기서열커버 {
    static int N, M;
    static char[][] dna;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dna = new char[M][M];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                dna[i][j] = str.charAt(j);
                System.out.println(dna[i][j]);
            }
        }
    }
}
