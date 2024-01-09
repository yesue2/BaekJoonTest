package _240109.자료구조1_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s = br.readLine();

        boolean tag = false;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '<') {
                tag = true;
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(s.charAt(i));

            } else if (s.charAt(i) == '>') {
                tag = false;
                sb.append(s.charAt(i));

            } else if (tag) {
                sb.append(s.charAt(i));

            } else if (!tag) {
                if (s.charAt(i) == ' ') {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(s.charAt(i));

                } else {
                    stack.push(s.charAt(i));

                }
            }
        }
        while(!stack.isEmpty())
            sb.append(stack.pop());

        System.out.println(sb);
    }
}
