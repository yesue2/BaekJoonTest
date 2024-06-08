package _240608.덱;

import java.io.*;
import java.util.*;

public class _1021_회전하는큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int result = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            deque.offer(i + 1);
        }

        int[] target = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            int targetIdx = getIdx(deque, target[i]);
            while (deque.peekFirst() != target[i]) {
                int second = Math.abs(targetIdx - 1);
                int third = Math.abs(targetIdx - deque.size());
                if (second > third) {
                    deque.offerFirst(deque.pollLast());
                    result++;
                } else {
                    deque.offerLast(deque.pollFirst());
                    result++;
                }
            }
            deque.pollFirst();
        }
        System.out.println(result);
    }

    static int getIdx(Deque<Integer> deque, int target) {
        Deque<Integer> de = new ArrayDeque<>(deque);
        for (int i = 0; i < deque.size(); i++) {
            if (de.pollFirst() == target) return i + 1;
        }
        return 0;
    }
}
