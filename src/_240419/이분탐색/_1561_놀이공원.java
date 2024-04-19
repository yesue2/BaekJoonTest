package _240419.이분탐색;

import java.io.*;
import java.util.*;

public class _1561_놀이공원 {
    static long N, result, mid;
    static int M, maxT;
    static int[] time;
    static List<Integer> startList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = new int[M];
        maxT = 0;
        startList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            time[i] = Integer.parseInt(st.nextToken());
            maxT = Math.max(time[i], maxT);
        }
        binarySearch();
        System.out.println(result);
    }

    static void binarySearch() {
        if (N <= M) {  // 사람 수가 놀이기구 수보다 적거나 같을 때 해당 번호 놀이기구 반환
            result = N;
            return;
        }
        long left = 0;
        long right = maxT * N;
        while (left <= right) {
            mid = (left + right) / 2;
            long num = countStart(mid);  // 해당 시간(mid)까지 운행 시작한 놀이기구의 수
            if (num >= N) {  // 현재 시간에 N개 이상의 놀이기구가 운행을 시작했을 경우
                getLastRide(mid);  // 마지막 아이가 탑승한 놀이기구 번호 구하기
                right = mid - 1;  // 시간 줄이기
            } else {  // 현재 시간에 N보다 적은 놀이기구가 운행을 시작했을 경우
                left = mid + 1;  // 시간 늘리기
            }
        }
    }

    static long countStart(long t) {
        long cnt = M;  // 모든 놀이기구가 한 번씩은 시작
        for (int i = 0; i < M; i++) {
            cnt += t / time[i];
        }
        return cnt;  // 해당 시간(t)까지 운행 시작한 놀이기구의 수
    }

    static void getLastRide(long t) {
        long cnt = M;  // 모든 놀이기구가 한 번씩은 시작
        for (int i = 0; i < M; i++) {
            cnt += (t - 1) / time[i];  // t-1 시간까지 운행 시작한 놀이기구의 수
        }

        for (int i = 0; i < M; i++) {
            if (t % time[i] == 0) {  // 마지막 아이가 탑승한 놀이기구의 번호를 반환
                cnt++;
                if (cnt == N) {
                    result = i + 1;
                }
            }
        }
    }
}
