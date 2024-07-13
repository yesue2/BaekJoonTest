import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(a);

        int s = 0;
        int e = 0;
        int min = Integer.MAX_VALUE;
        while (e < n) {
            int tmp = a[e] - a[s];
            if (tmp >= m) {
                min = Math.min(min, tmp);
                s++;
                if (s > e) {
                    e++;
                }
            } else {
                e++;
            }
        }
        System.out.println(min);
    }
}
