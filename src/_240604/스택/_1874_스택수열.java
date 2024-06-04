package _240604.스택;

import java.io.*;
import java.util.*;

public class _1874_스택수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int tmp = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > tmp) {
                for (int j = tmp + 1; j <= num; j++) {
                    stack.push(j);
                    sb.append("+\n");
                }
                tmp = num;
            }

            if (stack.peek() == num) {
                stack.pop();
                sb.append("-\n");
            }
        }
        if (stack.size() > 0) {
            System.out.println("NO\n");
        } else {
            System.out.println(sb);
        }
    }
}
