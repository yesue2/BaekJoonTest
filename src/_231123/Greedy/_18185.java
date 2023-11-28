package _231123.Greedy;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _18185 {
    public static int result(int n, int[] a) {
        int result;

        int count = 0;
        int stop = 0;

        for (int i = 0; i < n; i++) {
            if(a[i] != 0) {
                count++;
            }
            else if(a[i] == 0) {
                stop = i;
                break;
            }
        }
        result = stop;

        if(stop == 3) {
            result += 7;
        } else if (stop == 2) {

        }

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        String a = br.readLine();
        st = new StringTokenizer(a, " ");
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sb.append(result(n, arr));
        System.out.println(sb);

    }

}
