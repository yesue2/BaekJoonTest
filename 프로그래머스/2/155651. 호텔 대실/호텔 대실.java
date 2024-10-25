import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        List<int[]> times = new ArrayList<>();
        
        for (int i = 0; i < book_time.length; i++) {
            times.add(new int[]{setTime(book_time[i][0]), setTime(book_time[i][1]) + 10});
        }
        
        times.sort(Comparator.comparingInt(a -> a[0]));
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int[] time:times) {
            int start = time[0];
            int end = time[1];
            
            if (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll();
            }
            pq.add(end);
        }
        
        return pq.size();
    }
    static int setTime(String strTime) {
        String[] part = strTime.split(":");
        int start = Integer.parseInt(part[0]);
        int end = Integer.parseInt(part[1]);
        int time = start * 60 + end;
        
        return time;
    }
}