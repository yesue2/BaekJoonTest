package _240610.정렬;

import java.io.*;
import java.util.*;

public class _7795_먹을것인가먹힐것인가 {
    static List<Integer> a, b;
    static int n, m, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            a = new ArrayList<>();
            b = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a.add(Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                b.add(Integer.parseInt(st.nextToken()));
            }
            setResult();
            StringBuilder sb = new StringBuilder();
            sb.append(result);
            System.out.println(sb);
        }
    }

    static void setResult() {
        result = 0;
        Collections.sort(a);
        Collections.sort(b);
        for (int i = 0; i < n; i++) {
            result += countSmaller(a.get(i));
        }
    }

    // 이중for문 시간초과 => 이분 탐색 사용
    static int countSmaller(int x) {
        int left = 0;
        int right = b.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (b.get(mid) < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
