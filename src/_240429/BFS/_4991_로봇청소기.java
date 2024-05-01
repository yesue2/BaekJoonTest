package _240429.BFS;

import java.io.*;
import java.util.*;

public class _4991_로봇청소기 {
    static List<Integer> w, h, result;
    static List<char[][]> map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][][][] dis;  // x, y 지점에서 nx, ny 지점까지의 최단거리 저장
    static List<Node> dirty;
    static boolean[] visited;
    static int minDis, startX = -1, startY = -1;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        w = new ArrayList<>();
        h = new ArrayList<>();
        map = new ArrayList<>();
        result = new ArrayList<>();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tw = Integer.parseInt(st.nextToken());
            int th = Integer.parseInt(st.nextToken());

            if (tw == 0 && th == 0) break;  // 입력 종료 조건

            w.add(tw);
            h.add(th);

            char[][] currentMap = new char[th][tw];
            for (int i = 0; i < th; i++) {
                currentMap[i] = br.readLine().toCharArray();  // 문자열을 char 배열로 변환
            }
            map.add(currentMap);
        }

        for (int i = 0; i < map.size(); i++) {
            dirty = new ArrayList<>();
            dis = new int[h.get(i)][w.get(i)][h.get(i)][w.get(i)];
            for (int j = 0; j < h.get(i); j++) {
                for (int k = 0; k < w.get(i); k++) {
                    if (map.get(i)[j][k] == 'o') {
                        System.out.println("j, k : " + j + " " + k);
                        startX = j;
                        startY = k;
                    } else if (map.get(i)[j][k] == 'x') {
                        dirty.add(new Node(j, k));
                    }
                    bfs(j, k, i);  // 시작 지점부터 모든 접근 가능 지점까지의 최단거리 저장
                }
            }
            System.out.println("BFS 끝");

            visited = new boolean[dirty.size()];
            minDis = Integer.MAX_VALUE;
            dfs(0, new int[dirty.size()]);
            System.out.println("DFS 끝 : " + minDis);

            if (minDis == Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(minDis);
            dirty.clear();
        }
    }

    static void bfs(int x, int y, int num) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        dis[x][y][x][y] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h.get(num) || ny >= w.get(num)) continue;
                if (map.get(num)[nx][ny] == 'x') continue;
                if (dis[x][y][nx][ny] != 0) continue;

                queue.offer(new Node(nx, ny));
                dis[x][y][nx][ny] = dis[x][y][cur.x][cur.y] + 1;

            }
        }
    }

    static void dfs(int depth, int[] perm) {
        if (depth == dirty.size()) {
            int distance = 0;

            for (int i = 0; i < perm.length; i++) {
                Node cur = dirty.get(perm[i]);
                if (dis[startX][startY][cur.x][cur.y] == 0)
                    return;
                distance += dis[startX][startY][cur.x][cur.y];
                startX = cur.x;
                startY = cur.y;
            }
            minDis = Math.min(minDis, distance);
            return;
        }

        for (int i = 0; i < dirty.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm[depth] = i;
                dfs(depth + 1, perm);
                visited[i] = false;
            }
        }
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
