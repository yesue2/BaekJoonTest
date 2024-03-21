package _240321.소프티어;

import java.io.*;
import java.util.*;

public class 출퇴근길 {
    static int n, m, S, T;
    static List<List<Integer>> road = new ArrayList<>();
    static List<List<Integer>> reverseRoad = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {  // 각 노드의 인접 노드 추척 위함
            road.add(new ArrayList<>());
            reverseRoad.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            road.get(x).add(y);
            reverseRoad.get(y).add(x);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        HashSet<Integer> s1 = new HashSet<>();
        HashSet<Integer> s2 = new HashSet<>();
        dfs(S, T, road, s1, new boolean[n+1]);
        dfs(T, -1, reverseRoad, s2, new boolean[n+1]);
        s1.retainAll(s2);

        HashSet<Integer> s3 = new HashSet<>();
        HashSet<Integer> s4 = new HashSet<>();
        dfs(T, S, road, s3, new boolean[n+1]);
        dfs(S, -1, reverseRoad, s4, new boolean[n+1]);
        s3.retainAll(s4);

        s1.retainAll(s3);

        int result = s1.size();
        if (s1.contains(S)) result--;
        if (s1.contains(T)) result--;
        System.out.println(result);
    }

    static void dfs(int start, int end, List<List<Integer>> road, HashSet<Integer> set, boolean[] visited) {
        if (end != -1 && start == end) return;

        for (int i = 0; i < road.get(start).size(); i++) {
            int next = road.get(start).get(i);
            if (visited[next]) continue;
            visited[start] = true;
            set.add(next);
            dfs(next, end, road, set, visited);
        }
    }
}
