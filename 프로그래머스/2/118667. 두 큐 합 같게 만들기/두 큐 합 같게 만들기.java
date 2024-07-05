import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        int answer = 0;
        long totalSum = 0;
        long q2Sum = 0;
        
        for (int i = 0; i < queue1.length; i++) {
            totalSum += queue1[i];
            q1.offer(queue1[i]);
        }
        for (int i = 0; i < queue2.length; i++) {
            q2Sum += queue2[i];
            totalSum += queue2[i];
            q2.offer(queue2[i]);
        }
        
        long targetSum = totalSum / 2;
        if (totalSum % 2 == 1) return -1;
        
        while(true) {
            if (queue2.length * 3 < answer) return -1;
            if (targetSum == q2Sum) break;
            else if (q2Sum > targetSum) {
                int pollInt = q2.poll();
                q1.offer(pollInt);
                q2Sum -= pollInt;
            } else {
                int pollInt = q1.poll();
                q2.offer(pollInt);
                q2Sum += pollInt;
            }
            answer++;
        }
        return answer;
    }
}