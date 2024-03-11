package _240311.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class _14226_이모티콘 {

    static int S;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        bfs(S);
        System.out.println(result);
    }

    static void bfs(int start) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[S + 1][S + 1];  //[clipboard][total]
        queue.add(new Node(0, 1, 0));
        visited[0][1] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.total == start) {
                result = cur.time;
                return;
            }
            queue.add(new Node(cur.total, cur.total, cur.time + 1));
            if(cur.clipboard != 0 && cur.total + cur.clipboard <= start && !visited[cur.clipboard][cur.total + cur.clipboard]) {
                queue.offer(new Node(cur.clipboard, cur.total + cur.clipboard, cur.time + 1));
                visited[cur.clipboard][cur.total + cur.clipboard] = true;
            }
            if(cur.total >= 1 && !visited[cur.clipboard][cur.total - 1]) {
                queue.offer(new Node(cur.clipboard, cur.total - 1, cur.time + 1));
                visited[cur.clipboard][cur.total - 1] = true;
            }
        }
    }

    static class Node {
        int clipboard;
        int total;
        int time;

        public Node(int clipboard, int total, int time) {
            this.clipboard = clipboard;
            this.total = total;
            this.time = time;
        }
    }
}
