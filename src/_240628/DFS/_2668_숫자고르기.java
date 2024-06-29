package _240628.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class _2668_숫자고르기 {
    static int n;
    static int[] arr;
    static boolean[] visited;
    static boolean[] finished;
    static ArrayList<Integer> result;
    static ArrayList<Integer> tmpCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        visited = new boolean[n + 1];
        finished = new boolean[n + 1];
        result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                tmpCycle = new ArrayList<>();
                dfs(i);
            }
        }

        Collections.sort(result);

        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    static boolean dfs(int start) {
        visited[start] = true;
        tmpCycle.add(start);

        int n = arr[start];
        if (!visited[n]) {
            if (dfs(n)) {
                return true;
            }
        } else if (!finished[n]) {
            int idx = tmpCycle.indexOf(n);
            if (idx != -1) {
                result.addAll(tmpCycle.subList(idx, tmpCycle.size()));
                return true;
            }
        }
        finished[start] = true;
        tmpCycle.remove(tmpCycle.size() - 1);
        return false;
    }
}
