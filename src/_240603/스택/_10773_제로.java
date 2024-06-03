package _240603.스택;

import java.io.*;
import java.util.Stack;

public class _10773_제로 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                stack.pop();
                continue;
            }
            stack.push(num);
        }

        int result = 0;
        while (!stack.isEmpty())
            result += stack.pop();

        System.out.println(result);
    }
}
