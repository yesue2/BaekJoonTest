package _240109.자료구조1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10845 {
    public  static int[] queue;
    public static int size = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        queue = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            switch (st.nextToken()) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;

                case "pop":
                    sb.append(pop()).append('\n');
                    break;

                case "size":
                    sb.append(size()).append('\n');
                    break;

                case "empty":
                    sb.append(empty()).append('\n');
                    break;

                case "front":
                    sb.append(front()).append('\n');
                    break;

                case "back":
                    sb.append(back()).append('\n');
                    break;
            }
        }
        System.out.println(sb);
    }

    public static void push(int x) {
        queue[size] = x;
        size++;
    }

    public static int pop() {
        if (size == 0) {
            return -1;
        } else {
            int tmp = queue[0];
            for (int i = 0; i < size; i++) {
                queue[i] = queue[i+1];
            }
            queue[size - 1] = 0;
            size--;
            return tmp;
        }
    }

    public static int size() {
        return size;
    }

    public static int empty() {
        if (size == 0)
            return 1;
        else
            return 0;
    }

    public static int front() {
        if (size == 0)
            return -1;
        else
            return queue[0];
    }

    public static int back() {
        if (size == 0)
            return -1;
        else
            return queue[size - 1];
    }
}
