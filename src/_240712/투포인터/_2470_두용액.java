package _240712.투포인터;

import java.io.*;
import java.util.*;

public class _2470_두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int s = 0;
        int e = n-1;
        int min = Integer.MAX_VALUE;
        int[] result = new int[2];

        while (s < e) {
            int sum = arr[s] + arr[e];
            if (Math.abs(sum) < Math.abs(min)) {
                min = sum;
                result[0] = arr[s];
                result[1] = arr[e];
            }

            if (sum < 0) {
                s++;
            } else {
                e--;
            }
        }

        System.out.println(result[0] + " " + result[1]);
    }
}
