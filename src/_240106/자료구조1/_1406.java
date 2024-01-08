package _240106.자료구조1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class _1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Stack<String> stack = new Stack<>();
        Stack<String> tmp = new Stack<>();
        String str = br.readLine();
        int N = str.length();
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            stack.push(str.substring(i, i+1));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            switch (st.nextToken()) {
                case "L":
                    tmp.push(stack.pop());
                    break;

                case "D":
                    stack.push(tmp.pop());
                    break;

                case "B":
                    stack.pop();
                    break;

                case "p":
                    stack.push(st.nextToken());
                    break;
            }
        }
        sb.append(stack).append(tmp);
        System.out.println(sb);
    }
}
