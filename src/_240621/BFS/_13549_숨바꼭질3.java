package _240621.BFS;
import java.io.*;
import java.util.*;

public class _13549_숨바꼭질3 {
    static int n, k;
    static int[] map;

    static class Location {
        int N, dis;
        public Location(int N, int dis) {
            this.N = N;
            this.dis = dis;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[100001];

        if (n == k) {
            System.out.println(0);
            return;
        }

        int result = getTime();
        System.out.println(result);
    }

    static int getTime() {
        Deque<Location> deque = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        Location location = new Location(n, 0);
        int x = location.N;
        while (x <= 100000) {
            deque.add(new Location(x, 0));
            visited[x] = true;
            x *= 2;
            if (x == 0) break;
        }

        while (!deque.isEmpty()) {
            Location current = deque.poll();
            int curX = current.N;
            // 걷기
            int[] dx = {1, -1};
            for (int i = 0; i < 2; i++) {
                int nx = curX + dx[i];

                while (nx <= 100000 && nx >= 0 && !visited[nx]) {
                    if (nx == k) return current.dis;
                    deque.add(new Location(nx, current.dis+1));
                    visited[nx] = true;
                    nx *= 2;
                }
            }
        }
        return -1;
    }
}
