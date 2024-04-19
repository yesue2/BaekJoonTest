package _240419.이분탐색;

import java.io.*;
import java.util.*;

public class _1561_놀이공원 {
    static long N, num, result;
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
            long mid = (left + right) / 2;
            System.out.println("left, mid, right : " + left + " " + mid + " " + right);
            num = setTime(mid);  // 놀이기구에 모든 인원이 탑승하게 되는 시간 구하기
            System.out.println("num : " + num);
            if (num == -1) {  // 놀에기구에 탑승한 인원이 N명보다 많을 경우
                right = mid - 1;
            } else if (num == -2) {  // 놀에기구에 탑승한 인원이 N명보다 적을 경우
                left = mid + 1;
            } else {
                long tmp = 0;
                for (int i = 1; i < right; i++) {
                    tmp = setTime(num-i);
                    if (tmp != num) {
                        num -= i + 1;
                    }
                }
                break;
            }
        }
        // mid값이 시간 정답
        setNum(num);  // 마지막 고객이 몇 번째 놀이기구에 탔는지 반환
    }

    static long setTime(long t) {
        startList.clear();
        long allNum = M;
        for (int i = 0; i < M; i++) {
            allNum += t / time[i];

            long tmp = t % time[i];  // 해당 시간에 출발한 놀이기구의 번호 구하기
            if (tmp == 0) {
                startList.add(i+1);
            }
        }
        if (allNum > N) {
            return -1;
        } else if (allNum < N) {
            return -2;
        }
        return t;
    }

    static void setNum(long t) {
        System.out.print("시작한 놀이기구 : ");
        for (int i = 0; i < startList.size(); i++) {
            System.out.print(startList.get(i) + " ");
        }
        System.out.println();
        long allNum = M;
        for (int i = 0; i < M; i++) {  // 마지막 고객이 놀이기구를 탈 수 있는 시간보다 -1해서 이전 시간까지 탈 수 있는 사람의 수 구하기
            allNum += (t-1) / time[i];
        }
        for (int i = 0; i < startList.size(); i++) {   // 이전 시간까지 탑승한 고객 수에 +1 하며 마지막 고객이 탑승한 놀이기구의 번호 구하기
            result = startList.get(i);
            System.out.println("result : " + result);
            allNum++;
            if (allNum == M)
                return;
        }
    }
}
