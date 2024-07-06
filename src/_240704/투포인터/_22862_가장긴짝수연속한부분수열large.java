package _240704.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class _22862_가장긴짝수연속한부분수열large {
    static int n, k;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int result = twoPointer();
        System.out.println(result);
    }

    static int twoPointer() {
        int s = 0, e = 0;
        int oddCnt = 0;  // 홀수 개수
        int max = Integer.MIN_VALUE;


        while (e < n) {
            if (arr[e] % 2 == 1) {
                oddCnt++;
            }

            // 홀수의 개수가 k개 이상일 때
            // s를 이동시키며 홀수 줄이기
            while (oddCnt > k) {
                if (arr[s] % 2 == 1) {
                    oddCnt--;
                }
                s++;
            }
            // 현재 부분 수열의 길이 - 홀수의 길이(oddCnt) = 짝수의 길이
            max = Math.max(max, e - s + 1 - oddCnt);
            e++;
        }
        return max;
    }
}
