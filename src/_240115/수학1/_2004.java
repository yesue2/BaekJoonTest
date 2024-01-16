package _240115.수학1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long n = Integer.parseInt(st.nextToken());
        long m = Integer.parseInt(st.nextToken());

        long cnt5 = five_power_n(n) - five_power_n(n-m) - five_power_n(m);
        long cnt2 = two_power_n(n) - two_power_n(n - m) - two_power_n(m);
        System.out.println(Math.min(cnt5, cnt2));

    }

    static long five_power_n (long num) {
        int cnt = 0;
        while (num >= 5) {
            cnt += (num / 5);
            num /= 5;
        }
        return cnt;
    }

    static long two_power_n (long num) {
        int cnt = 0;

        while (num >= 2) {
            cnt += (num / 2);
            num /= 2;
        }

        return cnt;
    }
}
