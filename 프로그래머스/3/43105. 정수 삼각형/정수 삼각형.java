import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (triangle[i+1][j] >= triangle[i+1][j+1]) {
                    triangle[i][j] += triangle[i+1][j];
                } else {
                    triangle[i][j] += triangle[i+1][j+1];
                }
            }
        }
        answer = triangle[0][0];
        return answer;
    }
}