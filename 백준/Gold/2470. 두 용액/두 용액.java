import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 정렬
        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;
        int minSum = Integer.MAX_VALUE;
        int sol1 = 0, sol2 = 0;

        // 두 포인터를 사용하여 가장 0에 가까운 합을 찾음
        while (left < right) {
            int sum = arr[left] + arr[right];

            if (Math.abs(sum) < Math.abs(minSum)) {
                minSum = sum;
                sol1 = arr[left];
                sol2 = arr[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        // 결과 출력 (오름차순으로)
        System.out.println(sol1 + " " + sol2);
    }
}
