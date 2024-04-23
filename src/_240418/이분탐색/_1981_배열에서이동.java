package _240418.이분탐색;

import java.io.*;
import java.util.*;

public class _1981_배열에서이동 {
    static class Path {
        int x, y;
        public Path(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, min, max, result;
    static int[][] arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        min = 201;
        max = -1;
        result = 201;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        setMin();
        System.out.println(result);
    }

    static void setMin() {
        int left = 0;  // 나올 수 있는 차이값의 최소
        int right = max - min;  // 나올 수 있는 차이값의 최대

        // 나올 수 있는 차이값을 기준으로 이분탐색 진행
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean check = false;
            for (int i = min; i + mid <= max; i++) {
                int start = i;
                int end = i + mid;
                if(arr[0][0] >= start && arr[0][0] <= end) {
                    if (bfs(start, end)) {   // mid보다 크기가 작은 칸으로만 가서 n,n칸까지 도달했을 경우
                        check = true;
                        break;
                    }
                }
            }
            if (check) {   // 도달 성공했을 경우
                right = mid - 1;
                result = Math.min(result, mid);
            } else   // 도달 실패했을 경우
                left = mid + 1;
        }
    }

    static boolean bfs(int start, int end) {
        Queue<Path> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        visited[0][0] = true;
        queue.add(new Path(0, 0));

        while (!queue.isEmpty()) {
            Path cur = queue.poll();
            int cx = cur.x;
            int cy = cur.y;

            if (cx == n - 1 && cy == n - 1) {  // n,n 도달 시
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + cx;
                int ny = dy[i] + cy;
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && arr[nx][ny] <= end && arr[nx][ny] >= start) {
                    visited[nx][ny] = true;
                    queue.add(new Path(nx, ny));
                }
            }
        }
        return false;
    }
}
