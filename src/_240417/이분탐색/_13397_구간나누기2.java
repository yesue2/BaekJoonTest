package _240417.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _13397_구간나누기2 {
    static int N, M, result, difference;
    static int[] arr;
    static int[] right;
    static int[] left;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        right = new int[N / 2];
        left = new int[N / 2 + 1];
        result = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        setArr(arr, left, right);
    }

    static void setArr(int[] arr, int[] left, int[] right) {
        int mid;
        int cnt = 0;
        if (cnt == M) {
            result = Math.min(result, difference);
            return;
        }

        if (arr.length % 2 == 1) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            mid = arr.length / 2 + 1;
            for (int i = 0; i < mid; i++) {
                left[i] = arr[i];
                if (max < arr[i]) {
                    max = arr[i];
                }
                if (min > arr[i]) {
                    min = arr[i];
                }
            }
            for (int i = mid; i < arr.length; i++) {
                right[i - mid] = arr[i];
                if (max < arr[i]) {
                    max = arr[i];
                }
                if (min > arr[i]) {
                    min = arr[i];
                }
            }
            cnt += 2;
        } else {
            mid = arr.length / 2;
            for (int i = 0; i < mid; i++) {
                left[i] = arr[i];
            }
            for (int i = mid; i < arr.length; i++) {
                right[mid] = arr[i];
            }
            cnt += 2;
        }
    }

}
