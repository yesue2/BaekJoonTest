import java.io.*;
import java.util.*;

public class Main {
    static int n, e;
    static List<int[]>[] graph;  // 노드 수 고정, 메모리 사용 최적화

    //    static Map<Integer, int[]> graph; => 노드 수가 동적으로 변하거나, 노드 수가 많은 경우
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n; i++) {
            graph[i + 1] = new ArrayList<>();
        }

        // 양방향
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int dist1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, n);
        int dist2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n);

        int result = Math.min(dist1, dist2);
        System.out.println(result >= 200000000 ? -1 : result);

    }

    static int dijkstra(int s, int e) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, 200000000);
        dist[s] = 0;
        pq.add(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();  // cur[] => 노드번호, 거리

            if (cur[1] > dist[cur[0]]) continue;

            for (int[] neighbor : graph[cur[0]]) {
                int nDist = dist[cur[0]] + neighbor[1];

                if (nDist < dist[neighbor[0]]) {
                    dist[neighbor[0]] = nDist;
                    pq.add(new int[]{neighbor[0], nDist});
                }
            }
        }
        return dist[e];
    }
}
