package _240610.정렬;

import java.io.*;
import java.util.*;

public class _5648_역원소정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Long> list = new ArrayList<>();

        // 첫 번째 줄 입력 처리
        int n = Integer.parseInt(st.nextToken());
        while (st.hasMoreTokens()) {  // n을 넣고도 st에 토큰이 남아있다면 (=첫 번째 줄에 n 외에 다른 요소를 입력받았다면)
            String token = st.nextToken();
            list.add(Long.parseLong(reverseStr(token)));
        }

        // 두 번째 줄부터 끝까지 입력 처리
        while (true) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                list.add(Long.parseLong(reverseStr(token)));
            }
            if (list.size() == n) break;  // list의 크기가 n과 같아질 때 break
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (Long num : list) {
            sb.append(num).append("\n");
        }
        System.out.println(sb);
    }
    // list의 각 요소 뒤집기
    static String reverseStr(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
