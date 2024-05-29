package _240529.문자열;

import java.io.*;
import java.util.*;

public class _1969_DNA {
    static int N, M, resultSum;
    static char[][] dna;
    static StringBuilder sb;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dna = new char[N][M];
        sb.setLength(M);

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                dna[i][j] = tmp.charAt(j);
            }
        }

        countDNA();

        System.out.println(sb);
        System.out.println(resultSum);
    }

    static void countDNA() {
        for (int i = 0; i < M; i++) {
            cnt = new int[4];  // A, C, G, T
            for (int j = 0; j < 4; j++) {
                cnt[j] = 0;
            }

            for (int j = 0; j < N; j++) {
                switch (dna[j][i]) {
                    case 'A':
                        cnt[0]++;
                        break;
                    case 'C':
                        cnt[1]++;
                        break;
                    case 'G':
                        cnt[2]++;
                        break;
                    case 'T':
                        cnt[3]++;
                        break;
                }
            }
            mkResult(i);
        }
    }

    static void mkResult(int i) {
        int max = 0;
        int maxIndex = -1;
        for (int j = 0; j < 4; j++) {
            resultSum += cnt[j];
            if (max < cnt[j]) {
                maxIndex = j;
                max = cnt[j];
                switch (j) {
                    case 0:
                        sb.setCharAt(i, 'A');
                        break;
                    case 1:
                        sb.setCharAt(i, 'C');
                        break;
                    case 2:
                        sb.setCharAt(i, 'G');
                        break;
                    case 3:
                        sb.setCharAt(i, 'T');
                        break;
                }
            }
        }
        resultSum -= cnt[maxIndex];
    }
}
