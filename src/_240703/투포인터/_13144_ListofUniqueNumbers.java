package _240703.투포인터;

import java.io.*;
import java.util.*;

public class _13144_ListofUniqueNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> set = new HashSet<>();  // 중복 제거
        int l = 0;
        for (int r = 0; r < n; r++) {
            // 중복이 발생할 때까지 set에서 원소를 제거하고 s를 증가
            while (set.contains(arr[r])) {
                set.remove(arr[l]);
                l++;
            }
            set.add(arr[r]);
            result += r - l + 1;
        }

        System.out.println(result);
    }
}
