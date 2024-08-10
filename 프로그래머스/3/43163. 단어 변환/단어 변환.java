import java.io.*;
import java.util.*;

class Solution {
    static class WordState {
        int cnt;
        String word;
        WordState(int cnt, String word) {
            this.cnt = cnt;
            this.word = word;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        Queue<WordState> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        
        q.offer(new WordState(0, begin));
        
        while(!q.isEmpty()) {
            WordState cur = q.poll();
            if (cur.word.equals(target)) return cur.cnt;
            
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && getDiffCnt(cur.word, words[i]) == 1) {
                    visited[i] = true;
                    q.offer(new WordState(cur.cnt + 1, words[i]));
                }
            }
        }
        int answer = 0;
        
        return answer;
    }
    
    static int getDiffCnt(String word, String target) {
        int diffCnt = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) diffCnt++;
        }
        return diffCnt;
    }
}