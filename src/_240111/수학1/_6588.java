package _240111.수학1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _6588 {
    static final int MAX = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        boolean[] isPrime = new boolean[MAX+1];

        for (int i = 3; i < isPrime.length; i+=2) {
            isPrime[i] = true;
        }

        for (int i = 3; i <= Math.sqrt(MAX); i+=2) {
            for (int j = i*i; j <= MAX; j += i) {
                isPrime[j] = false;
            }
        }

        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0)
                break;

            boolean possible = false;
            for (int i = 3; i <= n/2; i+=2) {
                if (isPrime[i] &&  isPrime[n-i] && i + (n-i) == n) {
                    System.out.println(n+" = "+i+" + "+(n-i));
                    possible = true;
                    break;
                }
            }

            if (!possible)
                System.out.println("Goldbach's conjecture is wrong.");
        }
    }
}
