package _240405.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _11399_ATM {
    static int[] P;
    static int time, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        P = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        min();
        System.out.println(result);
    }
    static void min() {
        time = 0;
        result = 0;
        int[] perTime = new int[P.length];

        Arrays.sort(P);
        for (int i = 0; i < P.length; i++) {
            time += P[i];
            perTime[i] = time;
            result += perTime[i];
        }
    }
}
