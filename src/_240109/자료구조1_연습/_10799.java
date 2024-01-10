package _240109.자료구조1_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        int result = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push('(');
            } else if (str.charAt(i) == ')' && str.charAt(i-1) == ')') {
                stack.pop();
                result++;
            } else {
                // '(' 이후에 바로 ')'가 들어왔을 때
                stack.pop();
                result += stack.size();
            }
        }
        System.out.println(result);
    }
}
