package _240813.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1238_파티 {
    static int n, m, x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        List<int[]>[] map = new ArrayList[n + 1];
        List<int[]>[] reverseMap = new ArrayList[n + 1];
        for (int i = 0; i < n; i++) {
            map[i + 1] = new ArrayList<>();
            reverseMap[i + 1] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a].add(new int[]{b, c});
            reverseMap[b].add(new int[]{a, c});
        }

        int[] go = di(map);
        int[] back = di(reverseMap);

        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (go[i] == 1000000 || back[i] == 1000000) continue;
            int tmp = go[i] + back[i];
            if (tmp > result) {
                result = tmp;
            }
        }
        System.out.println(result);
    }

    static int[] di(List<int[]>[] map) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 1000000);
        dist[x] = 0;
        pq.add(new int[]{x, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[1] > dist[cur[0]]) continue;

            for (int[] neighbor : map[cur[0]]) {
                int nDist = neighbor[1] + dist[cur[0]];

                if (nDist < dist[neighbor[0]]) {
                    dist[neighbor[0]] = nDist;
                    pq.add(new int[]{neighbor[0], nDist});
                }
            }
        }
        return dist;
    }
}
