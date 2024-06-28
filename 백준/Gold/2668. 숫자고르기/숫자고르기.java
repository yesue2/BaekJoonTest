import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
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
        for (int num : result) {
            System.out.println(num);
        }
    }

    static boolean dfs(int start) {
        visited[start] = true;
        tmpCycle.add(start);

        int next = arr[start];
        if (!visited[next]) {
            if (dfs(next)) {
                return true;
            }
        } else if (!finished[next]) {
            int cycleStartIndex = tmpCycle.indexOf(next);
            if (cycleStartIndex != -1) {
                result.addAll(tmpCycle.subList(cycleStartIndex, tmpCycle.size()));
                return true;
            }
        }

        finished[start] = true;
        tmpCycle.remove(tmpCycle.size() - 1);
        return false;
    }
}
