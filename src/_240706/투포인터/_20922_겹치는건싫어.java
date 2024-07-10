package _240706.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _20922_겹치는건싫어 {
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
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();

        while (e < n) {
            map.put(arr[e], map.getOrDefault(arr[e], 0) + 1);

            // 현재 arr[e] 숫자의 개수가 k를 초과했을 때
            // Map의 모든 값이 k 이하가 될 때 까지 s를 이동시키며 윈도우 축소
            while (map.get(arr[e]) > k) {
                map.put(arr[s], map.get(arr[s]) - 1);
                s++;
            }
            max = Math.max(max, e - s + 1);
            e++;
        }
        return max;
    }
}
