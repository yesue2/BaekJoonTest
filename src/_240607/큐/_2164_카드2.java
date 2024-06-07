package _240607.큐;

import java.io.*;
import java.util.*;

public class _2164_카드2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            queue.offer(i + 1);
        }

        while (queue.size() != 1) {
            // 첫 번째 카드 버리기
            queue.remove();

            // 가장 위의 카드를 밑으로 옮기기
            int tmp =  queue.poll();
            queue.offer(tmp);
        }

        System.out.println(queue.peek());
    }
}
