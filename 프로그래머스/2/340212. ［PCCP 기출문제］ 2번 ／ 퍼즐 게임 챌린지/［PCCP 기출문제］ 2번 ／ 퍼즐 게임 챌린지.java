import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length;
        int left = 1;  // 최소 숙련도
        int right = 100_000;  // 최대 난이도
        int answer = right;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (isSolve(diffs, times, mid, limit)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }
    
    private boolean isSolve(int[] diffs, int[] times, int level, long limit) {
        long totalT = 0;  // 총 소요시간
        int prevT = 0;  // 이전 퍼즐 소요시간
        
        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];
            int curT = times[i];
            
            if (diff <= level) {
                totalT += curT;
            } else {
                int fail = diff - level;
                totalT += fail * (prevT + curT) + curT;
            }
            
            if (totalT > limit) {
                return false;
            }
            
            prevT = curT;
        }
        
        return totalT <= limit;
    }
}