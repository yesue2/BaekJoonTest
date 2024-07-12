import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] homes = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                homes[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // 두 번째 집부터 n번째 집까지의 최소 비용 계산
        for (int i = 1; i < n; i++) {
            homes[i][0] += Math.min(homes[i - 1][1], homes[i - 1][2]);
            homes[i][1] += Math.min(homes[i - 1][0], homes[i - 1][2]);
            homes[i][2] += Math.min(homes[i - 1][0], homes[i - 1][1]);
        }

        // 마지막 집의 최소 비용 중 최솟값 찾기
        int result = Math.min(homes[n - 1][0], Math.min(homes[n - 1][1], homes[n - 1][2]));
        System.out.println(result);
    }
}
