package _240117.수학1_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17087 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        long all;
        long result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.abs(Integer.parseInt(st.nextToken()) - S);
        }

        int first = arr[0];
        for (int i = 1; i < arr.length; i++) {
            all = gcd(first, arr[i]);
            result = Math.max(result, all);
        }
        System.out.println(result);
    }

    public static int gcd (int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a%b);
    }
}
