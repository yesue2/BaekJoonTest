package _230926.BruteForce;

import java.util.Scanner;

public class _2798 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] number = new int[n];

        for (int i = 0; i < n; i++) {
            number[i] = scanner.nextInt();
        }

        int result = search(number, n, m);
        System.out.println(result);
    }

    static int search(int[] arr, int N, int M) {
        int result = 0;

        for (int i = 0; i < N - 2; i++) {
            for (int j = i+1; j < N - 1; j++) {
                for (int k = j+1; k < N; k++) {
                    int tmp = arr[i] + arr[j] + arr[k];

                    if(M == tmp) {
                        return tmp;
                    } else if(result < tmp && tmp <M) {
                        result = tmp;
                    }
                }
            }
        }
        return result;
    }
}
