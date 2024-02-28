package _240216;

import java.util.*;

public class Solution {

    public int solution(int K, String[] user_scores) {
        int answer = 0;

        Map<String, Integer> preScores = new HashMap<>();
        Map<String, Integer> preRanks = new HashMap<>();
        Set<String> inRankUsers = new HashSet<>();

        for (String user_score : user_scores) {
            String[] parts = user_score.split(" ");
            String username = parts[0];
            int score = Integer.parseInt(parts[1]);

            // 이전 기록과 현재 점수를 비교하여 랭킹이 변화하는지 확인
            if (!preScores.containsKey(username) || score > preScores.get(username)) {

                if (!preRanks.containsKey(username) || getRank(score, preScores) != preRanks.get(username)) {
                    if (inRankUsers.size() <= K) {
                        answer++;
                    }
                }
                if (inRankUsers.size() <= K) {
                    answer++;
                }
                System.out.println(score);
                System.out.println(preScores.get(username));

                // 현재 페이지에 사용자 추가
                inRankUsers.add(username);

                // 이전 기록 갱신
                preScores.put(username, score);
            }
        }
        return answer;
    }
    private int getRank(int score, Map<String, Integer> preScores) {
        int rank = 1;
        for (int preScore : preScores.values()) {
            if (score < preScore) {
                rank++;
            }
        }
        return rank;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        int K = 3;
        String[] user_scores = {"a 100", "b 200", "a 200", "b 150", "c 50", "c 200"};
        System.out.println(solution.solution(K, user_scores)); // Output: 4
    }
}
