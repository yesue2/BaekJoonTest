import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 탑의 index와 높이(값)를 저장
        Stack<Integer> tower = new Stack<>();

        int[] height = new int[N];
        int[] result = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            while (!tower.isEmpty() && height[tower.peek()] < height[i]) {
                tower.pop();
            }

            if (!tower.isEmpty()) {
                result[i] = tower.peek() + 1;
            } else {
                result[i] = 0;
            }

            tower.push(i);
        }
        for (int i = 0; i < N; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
