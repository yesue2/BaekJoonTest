package _240602.배열;

import java.io.*;
import java.util.*;

public class _3273_두수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int result = 0;
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        // 확인한 숫자를 set에 저장
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            // x - i가 set에 있다면 result + 1
            int target = x - arr[i];
            if (set.contains(target)) {
                result++;
            }

            set.add(arr[i]);
        }
        System.out.println(result);
    }
}
