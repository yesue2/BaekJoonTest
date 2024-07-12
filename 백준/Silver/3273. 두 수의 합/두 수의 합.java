import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int result = 0;
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int target = x - arr[i];
            if (set.contains(target)) {
                result++;
            }
            set.add(arr[i]);
        }
        System.out.println(result);
    }
}
