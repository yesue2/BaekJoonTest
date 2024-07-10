package _240705.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2531_회전초밥 {
    static int n, d, k, c;
    static int[] sushi;
    static int[] eated;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sushi = new int[n];
        eated = new int[d + 1];

        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.valueOf(br.readLine());
        }

        int count = 0;
        for (int i = 0; i < k; i++) {
            if (eated[sushi[i]] == 0) {
                count++;
            }

            eated[sushi[i]]++;
        }

        int maxLen = count;
        for (int i = 1; i < n; i++) {

            if (maxLen <= count) {
                if (eated[c] == 0) { // 쿠폰초밥을 안먹은 상태
                    maxLen = count + 1;
                } else { // 쿠폰초밥을 먹은 상태
                    maxLen = count;
                }
            }

            int end = (i + k - 1) % n;
            if (eated[sushi[end]] == 0) {
                count++;
            }
            eated[sushi[end]]++;

            eated[sushi[i - 1]]--;    // 이전 초밥 제거
            if (eated[sushi[i - 1]] == 0) {
                count--;
            }
        }
        System.out.println(maxLen);
    }
}
