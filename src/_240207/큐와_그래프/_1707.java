package _240207.큐와_그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1707 {
    static boolean[] visited;
    static int V, E;
    static List<Integer>[] list;
    static int[] color;
    static boolean bipartite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // 인접 리스트 구현
            list = new ArrayList[V + 1];
            for (int j = 1; j < V + 1; j++) {
                list[j] = new ArrayList<>();
            }

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list[x].add(y);
                list[y].add(x);
            }
            color = new int[list.length];
            bipartite = false;
            bfs(1);

            if (!bipartite)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < V + 1; i++) {
            // 비연결 그래프일 경우
            if (color[i] == 0) {
                color[i] = 1;
                queue.add(i);
            }
            while (!queue.isEmpty()) {
                start = queue.poll();
                // 인접 노드와 현재 노드가 색이 같은지 확인
                for (int next : list[start]) {
                    if (color[next] == color[start]) {
                        // 같다면 함수 종료
                        return;
                    } else if (color[next] == 0) {  // 인접 노드의 색이 0일 때
                        queue.add(next);
                        if (color[start] == 1)  // 기준 노드의 색이 1일 때
                            color[next] = 2;  // 인접 노드의 색 2로 주입
                        else
                            color[next] = 1;  // 인접 노드의 색 1 주입
                    }
                }
            }
        }
        bipartite = true;
    }
}
