package _240608.덱;

import java.io.*;
import java.util.*;

public class _5430_AC {
    static int t, n;
    static char[] p;
    static Deque<Integer> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            p = br.readLine().toCharArray();
            n = Integer.parseInt(br.readLine());

            String arr = br.readLine();
            deque = new ArrayDeque<>();

            // 배열의 길이가 0이상이면, 후처리 진행
            if (n > 0) {
                String[] tmpArr = arr.substring(1, arr.length() - 1).split(",");
                for (String s : tmpArr) {
                    deque.offer(Integer.parseInt(s.trim()));
                }
            }

            if (!getResult()) {
                sb.append("error\n");  // 답 배열이 빈 배열일 때
            } else {
                sb.append("[");
                while (!deque.isEmpty()) {
                    if (deque.size() == 1) {
                        sb.append(deque.poll());
                    } else {
                        sb.append(deque.poll()).append(",");
                    }
                }
                sb.append("]\n");
            }
        }
        System.out.print(sb);
    }

    static boolean getResult() {
        boolean reverse = false;

        for (int i = 0; i < p.length; i++) {
            switch (p[i]) {
                case 'R':
                    reverse = !reverse;
                    break;
                case 'D':
                    if (deque.isEmpty()) return false;
                    // R 명령이 이전에 있었을 때, 덱의 뒤부터 삭제
                    // R 명령이 이전에 없었을 때, 덱의 앞부터 삭제
                    if (reverse) deque.pollLast();
                    else deque.pollFirst();

            }
        }

        if (reverse) {
            Deque<Integer> reversed = new ArrayDeque<>();
            while (!deque.isEmpty()) {
                reversed.offer(deque.pollLast());
            }
            deque = reversed;
        }
        return true;
    }
}
