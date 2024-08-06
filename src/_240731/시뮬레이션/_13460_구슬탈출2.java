package _240731.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _13460_구슬탈출2 {
    static int n, m;
    static char[][] board;
    static int[] red;
    static int[] blue;
    static int[] hole;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        red = new int[2];
        blue = new int[2];
        hole = new int[2];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            board[i] = tmp.toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    red[0] = i;
                    red[1] = j;
                }
                if (board[i][j] == 'B') {
                    blue[0] = i;
                    blue[1] = j;
                }
                if (board[i][j] == 'O') {
                    hole[0] = i;
                    hole[1] = j;
                }
            }
        }

    }
    static int set(int rx, int ry, int bx, int by, int depth) {
        if (depth > 10) {
            return -1;
        }

        if (rx == hole[0] && ry == hole[1]) {
            return depth;
        }



        return -1;
    }
}
