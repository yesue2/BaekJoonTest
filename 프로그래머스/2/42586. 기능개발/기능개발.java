import java.io.*;
import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        
        // 각 작업이 완료되기까지 남은 일수를 큐에 추가합니다.
        for (int i = 0; i < progresses.length; i++) {
            int days = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            queue.offer(days);
        }
        
        while (!queue.isEmpty()) {
            int currentDay = queue.poll();
            int count = 1;
            
            // 현재 작업이 완료되는 날짜 이후의 작업들을 확인합니다.
            while (!queue.isEmpty() && queue.peek() <= currentDay) {
                queue.poll();
                count++;
            }
            
            result.add(count);
        }
        
        return result;

    }
}