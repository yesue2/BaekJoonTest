package _240610.정렬;

import java.io.*;
import java.util.*;

public class _2910_빈도정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> map = new HashMap();  // 배열 저장
        Map<Integer, Integer> order = new HashMap<>();  // 순서 저장

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            // 순서 저장 map에 해당 요소가 없으면, 순서 저장 후 배열 저장 map에 value 초기화
            if (order.get(tmp) == null) {
                order.put(tmp, i);
                map.put(tmp, 0);
            }
            map.put(tmp, map.get(tmp) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());  //map을 list로 변환
        list.sort((e1, e2) -> {
            if (e2.getValue().equals(e1.getValue())) {  // list의 요소의 두 값이 같으면, 순서를 비교해 오름차순
                return order.get(e1.getKey()) - order.get(e2.getKey());
            } else
                return e2.getValue().compareTo(e1.getValue());  // list의 요소의 두 값이 다르면, 값을 비교해 내림차순
        });


        int j = 0;
        int cnt = 0;
        while (true) {
            for (int i = 0; i < list.get(j).getValue(); i++) {
                System.out.print(list.get(j).getKey() + " ");
                cnt++;
            }
            j++;
            if (cnt == n) break;
        }
    }
}
