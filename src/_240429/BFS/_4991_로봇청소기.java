package _240429.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _4991_로봇청소기 {
    static List<Integer> w;
    static List<Integer> h;
    static List<char[][]> map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int tw = 1;
        int th = 1;
        int c = 0;

        w = new ArrayList<>();
        h = new ArrayList<>();

        w.add(Integer.parseInt(st.nextToken()));
        h.add(Integer.parseInt(st.nextToken()));

        while (!(tw == 0 && th == 0)) {
            map = new ArrayList<>();
            for (int i = 0; i < h.get(c); i++) {
                String tmp = br.readLine();
                for (int j = 0; j < w.get(c); j++) {
                    map.get(c)[i][j] = tmp.charAt(j);
                }
            }
            c++;

            st = new StringTokenizer(br.readLine());
            tw = Integer.parseInt(st.nextToken());
            th = Integer.parseInt(st.nextToken());
            w.add(tw);
            h.add(th);
        }

        for (int i = 0; i < h.size(); i++) {
            for (int j = 0; j < h.get(i); j++) {
                for (int k = 0; k < w.get(i); k++) {
                    if (map.get(i)[j][k] == 'o') {
                        if(bfs(j, k, i)) {
                            System.out.println();
                        }
                    }
                }
            }
        }
    }

    static boolean bfs(int x, int y, int num) {
        Queue<int[]> queue = new LinkedList<>();
        int nh = h.get(num);
        int nw = w.get(num);
        visited = new boolean[nh][nw];

        visited[x][y] = true;
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int durty = 0;
            cnt = 0;  // 최단거리 값
            int non = 0;  // 인접한 칸 중 더러운 칸이 아닌 칸의 수

            for (int i = 0; i < nh; i++) {   // 전체 칸 돌면서 남은 더러운 칸이 있는지 확인
                for (int j = 0; j < nw; j++) {
                    if (map.get(num)[i][j] == '*')
                        durty++;
                }
            }
            if (durty == 0) {  // 더러운 칸이 더이상 없을 때
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < nh && ny < nw && !visited[nx][ny] && map.get(num)[nx][ny] == '*') {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    map.get(num)[nx][ny] = '.';
                    cnt++;
                } else {
                    non++;
                }
            }
            if (non == 4) {  // 인접한 칸 모두 더러운 칸이 아니고, 가구가 아닌 칸이 있을 때 탐색

            }
        }
        return false;
    }
}
