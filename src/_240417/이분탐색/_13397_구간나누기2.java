package _240417.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _13397_구간나누기2 {
    static int N, M, result;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        int left = 0;
        int right = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[i]);  // 배열의 최댓값을 right에 입력
        }
        result = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(arr, mid)) {   // mid로 M개 이하의 구간으로 나눌 수 있는지 판단
                result = Math.min(result, mid);
                right = mid - 1;   // right를 조정해 더 작은 값으로 탐색 진행
            } else {  // 나눈 구간이 M개 초과일 때
                left = mid + 1;  // left를 조정해 더 큰 값으로 탐색 진행
            }
        }
        System.out.println(result);
    }

    static boolean isValid(int[] arr, int mid) {
        int cnt = 1;
        int min = arr[0];
        int max = arr[0];

        for (int i = 0; i < N; i++) {
            if (arr[i] < min)
                min = arr[i];
            if (arr[i] > max)
                max = arr[i];
            if (max - min > mid) {   // (최댓값 - 최솟값) <= 중간값 이 되는 지점에서 구간을 나눔
                cnt++;
                min = arr[i];
                max = arr[i];
            }
        }
        return cnt <= M;
    }
}
