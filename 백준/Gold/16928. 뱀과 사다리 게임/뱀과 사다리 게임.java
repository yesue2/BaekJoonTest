import java.io.*;
import java.util.*;

public class Main {
    static int[] board;
    static boolean[] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[101];
        visited = new boolean[101];

        for (int i = 1; i <= 100; i++) {
            board[i] = i;
        }

        // 사다리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x] = y;
        }

        // 뱀
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u] = v;
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();

            for (int i = 1; i <= 6; i++) {
                int nXY = xy[0] + i;

                if (nXY > 100) continue;

                nXY = board[nXY];

                if (nXY == 100) {
                    return xy[1] + 1;
                }

                if (!visited[nXY]) {
                    visited[nXY] = true;
                    queue.offer(new int[] {nXY, xy[1] + 1});
                }
            }
        }
        return -1;
    }
}
