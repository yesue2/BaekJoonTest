package _240529.문자열;

import java.io.*;

public class _2999_비밀이메일 {
    static String msg;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        msg = br.readLine();

        setRC();

        System.out.println(sb);
    }

    static void setRC() {
        int lng = msg.length();
        int r = 0, c = 0;

        for (int i = 1; i * i <= lng; i++) {
            if (lng % i == 0) {
                r = i;
                c = lng / i;
            }
        }
        mkMatrix(r, c);
    }

    static void mkMatrix(int r, int c) {
        char[][] mat = new char[r][c];
        int index = 0;

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                mat[j][i] = msg.charAt(index++);
            }
        }

        sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(mat[i][j]);
            }
        }
    }
}
