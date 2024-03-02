package _240302.브루트포스.순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10974_모든순열 {
    static int[] res, arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N];
        res = new int[N];  // 만들어진 순열 담을 배열

        for (int i = 0; i < N; i++) {
            arr[i] = i+1;
        }

        permutation(0);
    }

    static void permutation(int depth) {
        StringBuilder sb = new StringBuilder();

        if (depth == arr.length) {
            for (int i : res) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i])
                continue;
            else {
                visited[i] = true;
                res[depth] = i + 1;
                permutation(depth+1);
                visited[i] = false;
            }
        }
    }
}
