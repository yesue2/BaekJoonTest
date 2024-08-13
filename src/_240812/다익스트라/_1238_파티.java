package _240812.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1238_파티 {
    static int n, m, x;
    static List<List<int[]>> graph; // int[] => 노드번호, 비용
    static List<List<int[]>> reverseGraph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[] { b, t });
            reverseGraph.get(b).add(new int[] { a, t });
        }

        int[] toParty = dijkstra(graph, x);
        int[] fromParty = dijkstra(reverseGraph, x);

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            int totalTime = toParty[i] + fromParty[i];
            if (totalTime > maxTime) {
                maxTime = totalTime;
            }
        }

        System.out.println(maxTime);
    }

    static int[] dijkstra(List<List<int[]>> graph, int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.add(new int[] { start, 0 });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentCost = current[1];

            if (currentCost > dist[currentNode]) {
                continue;
            }

            for (int[] neighbor : graph.get(currentNode)) {
                int neighborNode = neighbor[0];
                int edgeCost = neighbor[1];
                int newDist = dist[currentNode] + edgeCost;

                if (newDist < dist[neighborNode]) {
                    dist[neighborNode] = newDist;
                    pq.add(new int[] { neighborNode, newDist });
                }
            }
        }

        return dist;
    }
}
