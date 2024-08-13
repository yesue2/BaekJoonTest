import java.io.*;
import java.util.*;

public class Main {
    static int n, m, x;
    static List<List<Node>> graph;
    static List<List<Node>> reverseGraph;

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
            graph.get(a).add(new Node(b, t));
            reverseGraph.get(b).add(new Node(a, t));
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

    static int[] dijkstra(List<List<Node>> graph, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > dist[current.index]) {
                continue;
            }

            for (Node neighbor : graph.get(current.index)) {
                int newDist = dist[current.index] + neighbor.cost;
                if (newDist < dist[neighbor.index]) {
                    dist[neighbor.index] = newDist;
                    pq.add(new Node(neighbor.index, newDist));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparable<Node> {
        int index;
        int cost;

        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
