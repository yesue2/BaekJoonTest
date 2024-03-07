package _240307.브루트포스.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14501_퇴사 {
    static int[][] arr;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());  // T
            arr[i][1] = Integer.parseInt(st.nextToken());  // P
        }

        result = 0;
        DFS(0, 0);
        System.out.println(result);
    }
    static void DFS(int i, int p) {
        if (i >= arr.length) {
            result = Math.max(p, result);
            return;
        }

        if (i + arr[i][0] <= arr.length)  // 상담이 가능한 기간일 때 p 더해 백트래킹(dfs)
            DFS(i+arr[i][0], p+arr[i][1]);
        else  // 상담 기간이 퇴사일을 넘어갈 때 p는 더해지지 않고 종료조건으로 감
            DFS(i+arr[i][0], p);
        DFS(i+1, p);  // 1일부터 N일까지 각각 상담을 시작할 모든 경우 탐색
    }
}
