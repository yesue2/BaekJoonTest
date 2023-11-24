package _231118;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
public class DFS {

    public static int solution(int n, int[] orders) {
        Map<Integer, Integer> bookOrders = new HashMap<>();

        // orders 배열을 순회하며 각 책의 주문 수량을 매핑
        for (int i = 0; i < 2 * n; i += 2) {
            int bookType = orders[i];
            int order = orders[i + 1];
            bookOrders.put(bookType, bookOrders.getOrDefault(bookType, 0) + order);
        }

        long result = 0;
        long currentHeight = 0;

        // 각 책의 종류에 대해 필요한 높이와 힘을 계산
        for (int i = 1; i <= n; i++) {
            int neededHeight = (bookOrders.getOrDefault(i, 0) + 1) / 2 * 2;
            int neededForce = Math.abs((int) currentHeight - bookOrders.getOrDefault(i, 0) * 2);

            // 계산된 필요한 힘을 결과에 더함
            result += neededForce;

            // 다음 책을 쌓을 때 필요한 높이 계산을 위해 현재 높이 업데이트
            currentHeight += neededHeight;
        }

        // 결과를 1000000000으로 나눈 나머지를 반환
        return (int) (result % 1000000000);
    }

    public static void main(String[] args) {
        int n1 = 3;
        int[] orders1 = {1, 2, 1, 3, 3, 2};
        System.out.println(solution(n1, orders1)); // Expected output: 3

        int n2 = 4;
        int[] orders2 = {3, 3, 2, 2, 1, 1, 4, 4};
        System.out.println(solution(n2, orders2)); // Expected output: 0
    }
}
