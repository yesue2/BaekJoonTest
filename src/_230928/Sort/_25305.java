package _230928.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _25305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] score = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        int tmp;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (score[i] > score[j]) {
                    tmp = score[i];
                    score[i] = score[j];
                    score[j] = tmp;
                }
            }
        }
        System.out.println(score[n-k]);
    }
}
