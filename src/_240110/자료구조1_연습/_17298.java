package _240110.자료구조1_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Integer> stack = new Stack<>();

        int N = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        int[] result = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            result[i] = -1;
        }

        for (int i = 0; i < N; i++) {
            // 스택에 있는 가장 최근의 숫자와 현재 숫자와 비교해서 현재 숫자가 더 클 경우 스택에 있는 모든 인덱스를 현재 숫자로 넣기
            while (!stack.isEmpty() && A[stack.peek()] < A[i]){
                int idx = stack.pop();
                result[idx] = A[i];
            }
            // 현재 숫자의 인덱스를 저장
            stack.push(i);
        }

        for(int i=0;i<N;i++){
            sb.append(result[i]);
            sb.append(" ");
        }
        System.out.println(sb);
    }
}
