package _240423.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _9376_탈옥 {
    static class Path {
        int x, y;

        public Path(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int cnt, min, result;
    static List<char[][]> mapList;
    static List<Integer> resultList;
    static char[][] map;
    static List<Path> minPath = new ArrayList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        mapList = new ArrayList<>();
        resultList = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            char[][] tmpMap = new char[h][w];

            for (int j = 0; j < h; j++) {
                String tmp = br.readLine();
                for (int k = 0; k < w; k++) {
                    tmpMap[j][k] = tmp.charAt(k);
                }
            }
            mapList.add(tmpMap);
        }

        for (int i = 0; i < T; i++) {
            result = 0;
            System.out.println(i + "번째 테스트케이스");
            map = mapList.get(i);  // 순서대로 map에 감옥을 넣어 탐색
            escapePrisoner();  // 죄수들 탈옥시키기
            resultList.add(result);
        }
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i));
        }
    }

    static void escapePrisoner() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[map.length - 1].length; j++) {
                if (map[i][j] == '$') {
                    System.out.println("죄수의 위치 : " + i + " " + j);
                    visited = new boolean[map.length][map[map.length - 1].length];
                    cnt = 0;
                    min = Integer.MAX_VALUE;
                    bfs(i, j);
                    result += min;
                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[map.length - 1].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    // 경로 탐색해 result 값이 가장 작은 경로 선택
    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + xy[0];
                int ny = dy[i] + xy[1];

                if (nx >= 0 && ny >= 0 && nx < map.length && ny < map[map.length-1].length && !visited[nx][ny] && map[nx][ny] != '*') {
                    if (map[nx][ny] == '#') {
                        map[nx][ny] = '.';
                        cnt++;
                        System.out.println("nx ny : " + nx + ny);
                    }
                    if (nx == 0 || ny == 0 || nx == map.length-1 || ny == map[map.length-1].length-1) {
                        System.out.println("탈출 nx ny : " + nx + " " + ny);
                        if (min > cnt) {
                            min = cnt;
                        }
                        System.out.println("min : " + min);
                        return;
                    }
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}
