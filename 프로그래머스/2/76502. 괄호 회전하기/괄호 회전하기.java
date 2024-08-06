import java.io.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        String ex = s + s;
        for (int i = 0; i < s.length(); i++) {
            if(isValid(ex.substring(i, s.length()+i))) answer++;
        }
        return answer;
    }
    static boolean isValid(String tmp) {
        Deque<Character> dq = new ArrayDeque<>();
        
        for (char cur : tmp.toCharArray()) {
            if (cur == '(' || cur == '[' || cur == '{') {
                dq.push(cur);
            } else {
                if (dq.isEmpty()) return false;
                char target = dq.pop();
                if ((target == '(' && cur != ')') || (target == '[' && cur != ']') || (target == '{' && cur != '}')) return false;
            }
        }
        return dq.isEmpty();
    }
}