package _231117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;



public class _10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Integer> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        int result = -1;

        for (int i = 0; i < n; i++) {
            String recommend = st.nextToken();
            int number = Integer.parseInt(st.nextToken());
            switch (recommend) {
                case "push" :
                    System.out.println(stack.push(number));
                    break;

                case "pop" :
                    if(stack.empty() == true) {
                        System.out.println(result);
                    } else {
                        System.out.println(stack.pop());
                    }
                    break;

                case "size" :
                    System.out.println(stack.size());
                    break;

                case "empty" :
                    if(stack.empty() == true) {
                        System.out.println("1");
                    } else {
                        System.out.println("0");
                    }
                    break;

                case "top" :
                    if(stack.empty() == true) {
                        System.out.println("-1");
                    } else {
                        System.out.println(stack.peek());
                    }
                    break;
                default:
            }
        }
    }
}
