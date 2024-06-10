package _240610.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.OptionalInt;
import java.util.StringTokenizer;

public class _2230_수고르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
    }
}
