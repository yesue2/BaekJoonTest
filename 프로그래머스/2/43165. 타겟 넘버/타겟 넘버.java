import java.io.*;
import java.util.*;

class Solution {
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(0, numbers, target, 0);
        return answer;
    }
    static void dfs(int depth, int[] numbers, int target, int sum) {
        // 종료조건 : depth가 numbers의 길이와 같을 때
        if (depth == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        dfs(depth+1, numbers, target, sum+numbers[depth]);
        dfs(depth+1, numbers, target, sum-numbers[depth]);
    }
}