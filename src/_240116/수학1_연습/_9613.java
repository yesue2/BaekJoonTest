package _240116.수학1_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _9613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while (t-- != 0) {
            st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            long result = 0;

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (i != j) {
                        result += gcd (arr[i], arr[j]);
                    }
                }
            }
            System.out.println(result);
        }
    }

    public static int gcd (int a, int b) {
        if(b == 0)
            return a;

        return gcd(b,a%b);
    }
}
