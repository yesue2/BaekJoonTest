package _231003.Sort3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _11650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] coordinate = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            coordinate[i][0] = Integer.parseInt(st.nextToken());
            coordinate[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(coordinate, (c1, c2) -> {
            if(c1[0] == c2[0]) {
                return c1[1] - c2[1];
            } else {
                return c1[0] - c2[0];
            }
        });

        for (int i = 0; i < n; i++) {
            sb.append(coordinate[i][0] + " " + coordinate[i][1] + '\n');
        }
        System.out.println(sb);
    }
}
