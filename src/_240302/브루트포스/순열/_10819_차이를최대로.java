package _240302.브루트포스.순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10819_차이를최대로 {
    static int[] arr, res;
    static boolean[] visited;
    static int sum, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];  // 입력받을 배열
        res = new int[N];  // 탐색하며 새로 생성할 배열
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0);
        System.out.println(result);
    }

    static void dfs(int depth) {
        if (depth == arr.length) {
            sum = 0;

            for (int i = 0; i < res.length - 1; i++) {
                sum += Math.abs(res[i] - res[i+1]);
            }
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                res[depth] = arr[i];
                dfs(depth+1);
                visited[i] = false;
            }
        }
    }
}
