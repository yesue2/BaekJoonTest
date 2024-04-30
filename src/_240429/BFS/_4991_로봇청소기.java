package _240429.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _4991_로봇청소기 {
    static List<Integer> w, h, result;
    static List<char[][]> map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

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
                String tmp = br.readLine();
                currentMap[i] = tmp.toCharArray();  // 문자열을 char 배열로 변환
            }
            map.add(currentMap);
        }

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < h.get(i); j++) {
                for (int k = 0; k < w.get(i); k++) {
                    if (map.get(i)[j][k] == 'o') {
                        System.out.println("j, k : " + j + " " + k);
                        if (bfs(j, k, i)) {
                            System.out.println(result.get(i));
                        } else {
                            System.out.println(-1);
                        }
                    }
                }
            }
        }
    }

    static boolean bfs(int x, int y, int num) {
        Queue<Node> queue = new LinkedList<>();
        int nh = h.get(num);
        int nw = w.get(num);
        boolean[][] visited = new boolean[nh][nw];
        boolean[][] cleaned = new boolean[nh][nw];

        visited[x][y] = true;
        queue.offer(new Node(x, y, 0, null, map.get(num), visited));

        int totalDirty = 0;
        for (int i = 0; i < nh; i++) {   // 전체 칸 돌면서 남은 더러운 칸이 있는지 확인
            for (int j = 0; j < nw; j++) {
                if (map.get(num)[i][j] == '*')
                    totalDirty++;
            }
        }

        int tmpTotal = totalDirty;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (map.get(num)[cur.x][cur.y] == '*' && !cleaned[cur.x][cur.y]) {  // 더러운 칸 도달 시
                System.out.println("청소된 더러운 칸 : " + cur.x + " " + cur.y);
                cleaned[cur.x][cur.y] = true;
                totalDirty--;  // 남은 더러운 칸 수 감소

                if (totalDirty == 0) {
                    for (int i = 0; i < nh; i++) {
                        for (int j = 0; j < nw; j++) {
                            if (cleaned[i][j] && cur.map[i][j] == '.') {
                                System.out.println("더러운 칸 조건 부합 : " + i + " " + j);
                                tmpTotal--;
                            } else if (cleaned[i][j] && cur.map[i][j] == '*') {
                                cleaned[i][j] = false;
                                totalDirty++;
                            }
                        }
                    }
                    System.out.println("연산된 더러운 칸, 더러운 칸과 같은 수 : " + totalDirty + " " + tmpTotal);
                    if (tmpTotal == 0) {
                        printPath(cur);  // 경로 출력
                        result.add(cur.dis);  // 모든 더러운 칸을 청소했으면 결과 추가
                        return true;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < nh && ny < nw && !cur.visited[nx][ny] && map.get(num)[nx][ny] != 'x') {
                    cur.visited[nx][ny] = true;
                    char[][] tmpMap = map.get(num);
                    if (tmpMap[cur.x][cur.y] == '*') {
                        tmpMap[cur.x][cur.y] = '.';
                    }
                    queue.offer(new Node(nx, ny, cur.dis + 1, cur, tmpMap, cur.visited));
                }
            }
        }
        return false;
    }
    static void printPath(Node node) {
        Stack<Node> path = new Stack<>();
        while (node != null) {
            path.push(node);
            node = node.parent; // 부모 노드를 통해 역추적
        }

        // 경로 역순 출력
        while (!path.isEmpty()) {
            Node step = path.pop();
            System.out.println("(" + step.x + ", " + step.y + ")");
        }
        System.out.println("End");
    }
    static class Node {
        int x, y, dis;
        Node parent;
        char[][] map;
        boolean[][] visited;

        Node(int x, int y, int dis, Node parent, char[][] map, boolean[][] visited) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.parent = parent;
            this.map = map;
            this.visited = visited;
        }
    }
}
