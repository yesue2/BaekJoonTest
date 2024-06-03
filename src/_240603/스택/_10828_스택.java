package _240603.스택;

import java.io.*;
import java.util.*;

public class _10828_스택 {
    static int n;
    static ArrayList<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        stack = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String com = st.nextToken();
            if (com.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                push(num);
            }
            switch (com) {
                case "pop":
                    pop();
                    break;
                case "size":
                    size();
                    break;
                case "empty":
                    empty();
                    break;
                case "top":
                    top();
                    break;
            }
        }
    }

    static void push(int x) {
        stack.add(x);
    }

    static void pop() {
        if (stack.isEmpty()) System.out.println(-1);
        else {
            System.out.println(stack.get(stack.size() - 1));
            stack.remove(stack.size() - 1);
        }
    }

    static void size() {
        System.out.println(stack.size());
    }

    static void empty() {
        if (stack.isEmpty()) System.out.println(1);
        else System.out.println(0);
    }

    static void top() {
        if (stack.isEmpty()) System.out.println(-1);
        else System.out.println(stack.get(stack.size() - 1));

    }
}
