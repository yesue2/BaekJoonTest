import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxSum = arr[0];
        int curSum = arr[0];

        // 카데인(Kadane)의 알고리즘 => O(n)
        for (int i = 1; i < n; i++) {
            curSum = Math.max(arr[i], curSum + arr[i]);
            maxSum = Math.max(maxSum, curSum);
        }

        System.out.println(maxSum);
    }
}
