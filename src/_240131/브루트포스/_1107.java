package _240131.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1107 {
    static boolean[] broken;
    static int N;
    static long cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        broken = new boolean[10];
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                broken[num] = true;
            }
        }

        // N == 100일 경우 0 반환
        if (N == 100) {
            System.out.println(0);
            return;
        }

        cnt = Math.abs(N - 100);
        dfs(0, 0);
        System.out.println(cnt);
    }

    public static void dfs(int idx, int click) {
        for (int i = 0; i < 10; i++) {
            // 고장나지 않은 숫자 버튼으로 누른 수 카운팅
            if (!broken[i]) {
                int newBtn = click * 10 + i;
                int cnt2 = Math.abs(N - newBtn) + String.valueOf(newBtn).length();
                // N - 100의 절댓값 구해서 숫자로 최대한 눌렀을 때와 +,- 버튼으로만 눌렀을 때 비교
                cnt = Math.min(cnt, cnt2);

                // 채널의 최대가 6자리
                if (idx < 6) {
                    dfs(idx + 1, newBtn);
                }
            }
        }
    }
}
