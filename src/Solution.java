import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        // 각 친구의 선물 지수를 저장할 Map
        Map<String, Integer> giftScoreMap = new HashMap<>();

        // 각 친구의 선물 지수 초기화
        for (String friend : friends) {
            giftScoreMap.put(friend, 0);
        }

        // 주고 받은 선물 기록을 바탕으로 선물 지수 갱신
        for (String giftRecord : gifts) {
            String[] giftInfo = giftRecord.split(" ");
            String giver = giftInfo[0];
            String receiver = giftInfo[1];

            // 주고 받은 선물이 같으면 선물 지수를 갱신하지 않음
            if (!giver.equals(receiver)) {
                giftScoreMap.put(giver, giftScoreMap.get(giver) + 1);
                giftScoreMap.put(receiver, giftScoreMap.get(receiver) - 1);
            }

        }

        // 가장 많은 선물을 받을 친구의 선물 지수 찾기
        int maxGiftIndex = Integer.MIN_VALUE;
        for (int giftIndex : giftScoreMap.values()) {
            maxGiftIndex = Math.max(maxGiftIndex, giftIndex);
        }

        // 가장 많은 선물을 받을 친구 수 찾기
        for (int giftIndex : giftScoreMap.values()) {
            if (giftIndex == maxGiftIndex) {
                answer++;
            }
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String[] friends = new String[10];
        String[] gifts = new String[10000];

    }
}