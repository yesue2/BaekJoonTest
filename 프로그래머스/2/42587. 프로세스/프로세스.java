import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < priorities.length; i++) {
            pq.offer(priorities[i]);
            if (location == i) {
                queue.offer(new int[]{priorities[i], 1});
            } else {
                queue.offer(new int[]{priorities[i], 0});
            }
        }
        while(true) {
            int[] tmp = queue.poll();
            if (tmp[0] == pq.peek()) {
                pq.poll();
                answer++;
                if (tmp[1] == 1) {
                    return answer;
                }
            } else {
                queue.offer(new int[]{tmp[0], tmp[1]});
            }
        }
    }
}