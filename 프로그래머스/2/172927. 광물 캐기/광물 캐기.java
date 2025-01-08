import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int[][] fatigue = {
            {1, 1, 1},    // 다이아몬드 곡괭이
            {5, 1, 1},    // 철 곡괭이
            {25, 5, 1}    // 돌 곡괭이
        };

        int totalPicks = picks[0] + picks[1] + picks[2];
        int n = Math.min(minerals.length, totalPicks * 5);

        List<int[]> blocks = new ArrayList<>();
        for (int i = 0; i < n; i += 5) {
            int[] block = new int[3]; // [다이아몬드, 철, 돌]
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                if (minerals[j].equals("diamond")) {
                    block[0]++;
                } else if (minerals[j].equals("iron")) {
                    block[1]++;
                } else {
                    block[2]++;
                }
            }
            blocks.add(block);
        }

        blocks.sort((a, b) -> {
            int aScore = a[0] * 25 + a[1] * 5 + a[2];
            int bScore = b[0] * 25 + b[1] * 5 + b[2];
            return bScore - aScore;
        });

        int fatigueSum = 0;
        for (int[] block : blocks) {
            if (picks[0] > 0) {
                picks[0]--;
                fatigueSum += block[0] * fatigue[0][0] + block[1] * fatigue[0][1] + block[2] * fatigue[0][2];
            } else if (picks[1] > 0) {
                picks[1]--;
                fatigueSum += block[0] * fatigue[1][0] + block[1] * fatigue[1][1] + block[2] * fatigue[1][2];
            } else if (picks[2] > 0) {
                picks[2]--;
                fatigueSum += block[0] * fatigue[2][0] + block[1] * fatigue[2][1] + block[2] * fatigue[2][2];
            }
        }

        return fatigueSum;
    }
}
