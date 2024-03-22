package _240322.소프티어;

import java.beans.Introspector;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class 성적평가 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> score = new HashMap<>();
        HashMap<Integer, Integer> sumScore = new HashMap<>();

        for (int i = 0; i < N; i++) {
            sumScore.put(i, 0);
        }

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                score.put(j, Integer.parseInt(st.nextToken()));
                sumScore.put(j, sumScore.get(j) + score.get(j));
            }
            rank(score);
            System.out.println();
        }
        rank(sumScore);
    }

    static void rank(HashMap<Integer, Integer> score) throws IOException {
        // value 기준 오름차순 정렬
        List<Map.Entry<Integer, Integer>> sortScore = new LinkedList<>(score.entrySet());
        sortScore.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 정렬된 sortScore의 순서대로 등수 score의 value에 put
        int sameScoreCnt = 0;  // 동점자 수를 저장
        int prevScore = Integer.MAX_VALUE;  // 이전 점수를 저장
        int rank = 1;
        for (Map.Entry<Integer, Integer> entry : sortScore) {
            int currentScore = entry.getValue();
            if (currentScore < prevScore) {
                // 이전 점수와 현재 점수가 다르면, 이전 점수를 현재 점수로 갱신하고 같은 점수 수를 0으로 초기화
                prevScore = currentScore;
                rank += sameScoreCnt;
                sameScoreCnt = 0;
            } else {
                // 이전 점수와 현재 점수가 같으면, 같은 점수 수 1 증가
                sameScoreCnt++;
                score.put(entry.getKey(), rank);
                continue;
            }
            // 현재 학생의 등수 설정
            score.put(entry.getKey(), rank);
        }

        // key 기준 정렬 후 출력
        List<Map.Entry<Integer, Integer>> resultScore = new LinkedList<>(score.entrySet());
        resultScore.sort(Map.Entry.comparingByKey());
        for (Map.Entry<Integer, Integer> entry : resultScore) {
            System.out.print(entry.getValue() + " ");
        }
    }
}
