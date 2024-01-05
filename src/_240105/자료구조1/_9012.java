package _240105.자료구조1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class _9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            Stack<Character> stack = new Stack<>();
            String s = br.readLine();
            boolean result = true;

            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '(') {
                    stack.push('(');
                } else{
                    if (stack.isEmpty()) {
                        result = false;
                        break;
                    }
                    stack.pop();
                }
            }

            if (!stack.isEmpty())
                result = false;

            if (result)
                sb.append("YES\n");
            else
                sb.append("NO\n");
        }
        System.out.print(sb);
    }
}
