package _240702.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2003_수들의합2 {
    static int n, m, result;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        twoSum();

        System.out.println(result);
    }
    public static void twoSum() {
        int start = 0, sum = 0;
        for (int end = 0; end < n; end++) {
            sum += arr[end];

            while (sum > m && start <= end) {
                sum -= arr[start++];
            }
            if (sum == m) {
                result++;
            }
        }
    }
}
