package _240131.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _3085 {
    static int max = 1;
    static int N;
    static char[][] contents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        contents = new char[N][N];

        for (int i = 0; i < N; i++) {
            String c = br.readLine();
            for (int j = 0; j < N; j++) {
                contents[i][j] = c.charAt(j);
            }
        }

        //행 기준 오른쪽과 변경
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                swap(i, j, i, j + 1);
                search();
                swap(i, j + 1, i, j);
            }
        }

        // 열 기준 아래쪽과 변경
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N; j++) {
                swap(i, j, i + 1, j );
                search();
                swap(i + 1, j, i, j);
            }
        }
        System.out.println(max);
        br.close();
    }

    public static void swap(int x1, int y1, int x2, int y2) {
        char tmp = contents[x1][y1];
        contents[x1][y1] = contents[x2][y2];
        contents[x2][y2] = tmp;
    }

    public static void search() {
        // 행에서 긴 수열 탐색
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            for (int j = 0; j < N - 1; j++) {
                if (contents[i][j] == contents[i][j+1]) {
                    cnt++;
                    max = Math.max(cnt, max);
                } else
                    cnt = 1;
            }
        }

        // 열에서 긴 수열 탐색
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            for (int j = 0; j < N - 1; j++) {
                if (contents[j][i] == contents[j+1][i]) {
                    cnt++;
                    max = Math.max(cnt, max);
                } else
                    cnt = 1;
            }
        }
    }
}
