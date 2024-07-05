import java.io.*;
import java.util.*;


class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '(' && tmp == ')') {
                stack.pop();
                continue;
            }
            stack.push(tmp);
        }
        if(!stack.isEmpty()) answer = false;
        return answer;
    }
}