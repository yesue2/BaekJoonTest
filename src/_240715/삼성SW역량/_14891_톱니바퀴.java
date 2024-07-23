package _240715.삼성SW역량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class _14891_톱니바퀴 {
    static char[][] ch;
    static int[][] com;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ch = new char[4][8];
        Deque<char[]> q = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            ch[i] = str.toCharArray();
            q.add(ch[i]);
        }
        int k = Integer.parseInt(br.readLine());
        com = new int[k][2];
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            com[i][0] = Integer.parseInt(st.nextToken());
            com[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void setRound() {

    }
}
