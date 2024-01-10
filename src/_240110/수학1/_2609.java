package _240110.수학1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int x = a*b;
        int max = Math.max(a, b);
        int min = Math.min(a, b);

        int gcd;
        int lcm;
        int mod = 1;

        while (mod != 0) {
            mod = max % min;
            max = min;
            min = mod;
        }

        gcd = max;
        lcm = x/gcd;

        System.out.println(gcd);
        System.out.println(lcm);
    }
}
