import java.io.*;
import java.util.*;

public class Main {
    static int n, m, s, e;
    static List<int[]>[] map;
    static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map = new ArrayList[n + 1];
        prev = new int[n + 1];
        Arrays.fill(prev, -1);

        for (int i = 0; i < n; i++) {
            map[i + 1] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a].add(new int[]{b, c});
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        long minCost = di();
        List<Integer> path = getPath();

        System.out.println(minCost);
        System.out.println(path.size());
        for (int city : path) {
            System.out.print(city + " ");
        }
    }

    static long di() {
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[s] = 0;
        pq.add(new long[]{s, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int curNode = (int)cur[0];

            if (cur[1] > dist[curNode]) continue;

            for (int[] neighbor : map[curNode]) {
                int nextNode = neighbor[0];
                long nDist = dist[curNode] + neighbor[1];

                if (nDist < dist[nextNode]) {
                    dist[nextNode] = nDist;
                    pq.add(new long[]{nextNode, nDist});
                    prev[nextNode] = curNode;
                }
            }
        }
        return dist[e];
    }

    static List<Integer> getPath() {
        List<Integer> path = new ArrayList<>();
        for (int at = e; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
