package _240429.BFS;
import java.util.*;

public class _1600_말이되고픈원숭이 {

        static int K, W, H;
        static int[][] map;
        static int min = Integer.MAX_VALUE;
        static int[] hdx = {-2, -2, -1, -1, 1, 1, 2, 2};
        static int[] hdy = {-1, 1, -2, 2, -2, 2, -1, 1};
        static int[] dx = {0, 1, 0 ,-1};
        static int[] dy = {1, 0, -1, 0};
        static boolean[][][] visited;

        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);

            K = scan.nextInt();
            W = scan.nextInt();
            H = scan.nextInt();

            map = new int[H][W];
            for(int i = 0; i < H; i++) {
                for(int j = 0; j < W; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            visited = new boolean[H][W][K + 1];
            min = bfs(0, 0);

            if(min == Integer.MAX_VALUE) System.out.println("-1");
            else System.out.println(min);
        }

        public static int bfs(int x, int y) {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node(x, y, 0, K));
            visited[x][y][K] = true;

            while(!queue.isEmpty()) {
                Node cur = queue.poll();
                if(cur.x == H - 1 && cur.y == W - 1) return cur.count;

                for(int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if(nx >= 0 && ny >= 0 && nx < H && ny < W && !visited[nx][ny][cur.k] && map[nx][ny] == 0) {
                        visited[nx][ny][cur.k] = true;
                        queue.offer(new Node(nx, ny, cur.count + 1, cur.k));
                    }
                }

                if(cur.k > 0) {
                    for(int i = 0; i < 8; i++) {
                        int nx = cur.x + hdx[i];
                        int ny = cur.y + hdy[i];
                        if(nx >= 0 && ny >= 0 && nx < H && ny < W && !visited[nx][ny][cur.k - 1] && map[nx][ny] == 0) {
                            visited[nx][ny][cur.k - 1] = true;
                            queue.offer(new Node(nx, ny, cur.count + 1, cur.k - 1));
                        }
                    }
                }
            }
            return min;
        }

        public static class Node {
            int x;
            int y;
            int count;
            int k;

            public Node(int x, int y, int count, int k) {
                this.x = x;
                this.y = y;
                this.count = count;
                this.k = k;
            }
        }

}
