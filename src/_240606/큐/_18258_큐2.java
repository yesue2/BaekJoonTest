package _240606.큐;

import java.io.*;
import java.util.*;

public class _18258_큐2 {
    static Deque<Integer> queue = new ArrayDeque<>();
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String com = st.nextToken();
            switch (com) {
                case "push":
                    int num = Integer.parseInt(st.nextToken());
                    push(num);
                    break;
                case "pop":
                    pop();
                    break;
                case "size":
                    size();
                    break;
                case "empty":
                    empty();
                    break;
                case "front":
                    front();
                    break;
                case "back":
                    back();
                    break;
            }
        }
        System.out.println(sb);
    }

    static void push(int x) {
        queue.addLast(x);
    }

    static void pop() {
        if (queue.isEmpty()) sb.append(-1).append("\n");
        else {
            sb.append(queue.pollFirst()).append("\n");
        }
    }

    static void size() {
        sb.append(queue.size()).append("\n");
    }

    static void empty() {
        sb.append(queue.isEmpty() ? "1\n" : "0\n");
    }

    static void front() {
        if (queue.isEmpty()) sb.append(-1).append("\n");
        else sb.append(queue.peekFirst()).append("\n");
    }

    static void back() {
        if (queue.isEmpty()) sb.append(-1).append("\n");
        else sb.append(queue.peekLast()).append("\n");
    }
}
