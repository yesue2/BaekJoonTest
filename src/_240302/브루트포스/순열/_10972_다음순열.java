package _240302.브루트포스.순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _10972_다음순열 {
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
            for (int i = 0; i < N; i++)
                System.out.print(arr[i] + " ");
        } else
            System.out.println(-1);
    }

    static boolean check() {
        // 1. 가장 뒤 쪽(i)부터 i-1 < i이 성립될 때 까지 i-- 하며 탐색해 교환할 위치(i-1) 찾기
        int i = arr.length - 1;
        while (i > 0 && arr[i-1] > arr[i])
            i--;
        if (i <= 0)
            return false;

        // 2. 가장 뒤 쪽(j)부터 i-1 < j이 성립될 때 까지 j-- 하며 탐색해 i-1와 교환할 j 찾기
        int j = arr.length - 1;
        while (arr[i-1] > arr[j])
            j--;

        // 3. i-1과 j의 값 교환
        swap(i-1, j);

        // 4. 가장 큰 값인 i부터 가장 마지막 값인 j까지 다시 오름차순 정렬
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
