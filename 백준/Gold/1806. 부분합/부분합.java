import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int start = 0;
        int end = 0;
        int sum = 0;
        int minLeng = Integer.MAX_VALUE;

        while (end < n) {
            sum += arr[end];
            while (sum >= s) {
                minLeng = Math.min(minLeng, end - start + 1);
                sum -= arr[start];
                start++;
            }
            end++;
        }

        if (minLeng == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLeng);
        }
    }
}
