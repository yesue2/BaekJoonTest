import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int result = twoPointer();
        System.out.println(result);
    }

    static int twoPointer() {
        int s = 0, e = 0;
        int oddCnt = 0;  // 홀수 개수
        int max = Integer.MIN_VALUE;


        while (e < n) {
            if (arr[e] % 2 == 1) {
                oddCnt++;
            }
            while (oddCnt > k) {
                if (arr[s] % 2 == 1) {
                    oddCnt--;
                }
                s++;
            }
            max = Math.max(max, e - s + 1 - oddCnt);
            e++;
        }
        return max;
    }
}
