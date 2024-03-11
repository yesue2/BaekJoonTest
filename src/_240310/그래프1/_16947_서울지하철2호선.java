package _240310.그래프1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _16947_서울지하철2호선 {
    static ArrayList<Integer>[] map;
    static int N;
    static boolean[] isCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x].add(y);
            map[y].add(x);
        }

        isCycle = new boolean[N + 1];
        for(int i = 1; i <= N; i++) {
            if(checkCycle(i, i, i)) break;
            isCycle = new boolean[N + 1];
        }

        int[] result = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            if(!isCycle[i]) result[i] = bfs(i);
        }

        for(int i = 1; i <= N; i++) System.out.print(result[i] + " ");
    }

    static int bfs(int node) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(new Node(node, 0));
        visited[node] = true;

        while(!queue.isEmpty()) {
            Node current = queue.poll();
            if(isCycle[current.v]) return current.count;

            for(int i = 0; i < map[current.v].size(); i++) {
                int next = map[current.v].get(i);
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(new Node(next, current.count + 1));
                }
            }
        }
        return 0;
    }

    static boolean checkCycle(int prev, int node, int start) {
        isCycle[node] = true;

        for(int i = 0; i < map[node].size(); i++) {
            int next = map[node].get(i);

            if(!isCycle[next]) {
                if(checkCycle(node, next, start)) return true;
            } else if(next != prev && next == start) return true;
        }
        isCycle[node] = false;

        return false;
    }

    static class Node {
        int v;
        int count;

        public Node(int v, int count) {
            this.v = v;
            this.count = count;
        }
    }
}
