import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long result;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        twoPointer();
        System.out.println(result);
    }
    static void twoPointer() {
        int[] cnt = new int[100001];
        int l = 1, r = 0;
        while (l <= n) {
            while (r + 1 <= n && cnt[arr[r + 1]] == 0) {
                r++;
                cnt[arr[r]]++;
            }
            result += r - l + 1;
            cnt[arr[l++]]--;
        }
    }
}
