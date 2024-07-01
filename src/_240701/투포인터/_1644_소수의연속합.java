package _240701.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1644_소수의연속합 {
    static int n;
    static List<Integer> prime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        isPrimeFun();

        int result = 0, s = 0, e = 0, sum = 0;
        while (true) {
            if (sum >= n) {
                sum -= prime.get(s++);
            } else if (e == prime.size()) {
                break;
            } else {
                sum += prime.get(e++);
            }
            if (sum == n) {
                result++;
            }
        }
        System.out.println(result);
    }

    public static void isPrimeFun() {
        // 에라토스테네스의 체
        boolean[] isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        prime = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                prime.add(i);
            }
        }
    }
}
