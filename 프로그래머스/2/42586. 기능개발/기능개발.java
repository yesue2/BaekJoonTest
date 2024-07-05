import java.io.*;
import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < progresses.length; i++){
            if((100 - progresses[i]) % speeds[i] == 0)
                progresses[i] = Integer.valueOf((100 - progresses[i]) / speeds[i]);
            else
                progresses[i] = Integer.valueOf((100 - progresses[i]) / speeds[i] + 1);
        }
        ArrayList<Integer> arr = new ArrayList<>();
        stack.push(progresses[0]);
        int count = 1;
        int idx = 0;
        for(int i = 1; i < progresses.length; i++){
            if(stack.peek() >= progresses[i]){
                count++;
            }
            else{
                stack.push(progresses[i]);
                idx = i;
                arr.add(count);
                count = 1;
            }
        }
        arr.add(count);
        return arr;
        
        
    }
}