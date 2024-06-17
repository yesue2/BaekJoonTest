import java.util.*;
import java.io.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        
        stack.push(arr[arr.length-1]);
        for(int i = arr.length-2; i >= 0; i--) {
            int com = stack.peek();
            if(arr[i] != com)  {
                stack.push(arr[i]);
            }
        }
        int size = stack.size();
        int[] answer = new int[size];
        for(int i = 0; i < size; i++) {
            answer[i] = stack.pop();
        }
        return answer;
    }
}