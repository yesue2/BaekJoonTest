import java.io.*;
import java.util.*;

public class Main {
    static int v, e, s;
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(br.readLine());

        graph = new ArrayList[v + 1];
        for (int i = 0; i < v; i++) {
            graph[i + 1] = new ArrayList<>();
        }

        // 방향그래프
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, c});
        }


        int[] tmp = di();
        for (int i = 1; i < tmp.length; i++) {
            if (tmp[i] == 3000000) {
                System.out.println("INF");
            } else {
                System.out.println(tmp[i]);
            }
        }

    }

    static int[] di() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] dist = new int[v + 1];  // 시작점 s에서 각 노드까지의 최단거리 저장
        // 가중치의 최대값 * 간선의 최대 개수 = 10 * 300,000 = 3000000
        Arrays.fill(dist, 3000000);
        dist[s] = 0;
        pq.add(new int[]{s, 0});

        while (!pq.isEmpty()) {
            // pq에서 현재 가장 짧은 거리를 가진 노드 꺼내기
            int[] cur = pq.poll();

            // 꺼낸 노드의 거리가 이미 저장된 최단 거리보다 클 때 => 이미 더 짧은 경로가 발견된 것이므로 건너뛰기
            if (cur[1] > dist[cur[0]]) continue;

            // cur[0]과 연결된 모든 인접 노드 탐색
            for (int[] neighbor : graph[cur[0]]) {
                // 현재 노드를 거쳐서 인접 노드에 도달하는 거리 계산
                int nDist = dist[cur[0]] + neighbor[1];

                // 새로운 경로가 기존의 최단거리보다 짧을 때 => 최단거리 갱신 후, 해당 노드 pq에 추가
                if (nDist < dist[neighbor[0]]) {
                    dist[neighbor[0]] = nDist;
                    pq.add(new int[]{neighbor[0], nDist});
                }
            }
        }
        return dist;
    }
}
