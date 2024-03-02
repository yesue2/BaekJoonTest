package _240302.브루트포스.순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10973_이전순열 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (check()) {
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
        } else
            System.out.println(-1);
    }

    static boolean check() {
        int i = arr.length-1;
        while (i > 0 && arr[i] > arr[i-1])
            i--;
        if (i <= 0)
            return false;

        int j = arr.length-1;
        while (arr[i-1] < arr[j])
            j--;
        swap(i-1, j);

        j = arr.length-1;
        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }
        return true;
    }

    static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
