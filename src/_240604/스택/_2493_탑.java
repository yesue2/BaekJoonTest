package _240604.스택;

import java.io.*;
import java.util.*;

public class _2493_탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 탑의 인덱스를 저장
        Stack<Integer> tower = new Stack<>();

        int[] height = new int[N];
        int[] result = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            // 현재 탑보다 낮은 탑들은 스택에서 제거
            while (!tower.isEmpty() && height[tower.peek()] < height[i]) {
                tower.pop();
            }

            // 스택이 비어있지 않다면, 신호를 발사한 탑의 인덱스를 result에 저장
            if (!tower.isEmpty()) {
                result[i] = tower.peek() + 1;
            } else {
                // 스택이 비어있다면, 수신할 신호가 없는 것 => 0 저장
                result[i] = 0;
            }
            // 현재 탑의 인덱스를 스택에 추가
            tower.push(i);
        }
        for (int i = 0; i < N; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
