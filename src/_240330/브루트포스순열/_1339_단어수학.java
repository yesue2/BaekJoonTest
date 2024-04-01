package _240330.브루트포스순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1339_단어수학 {
    static int N;
    static String[] str;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
        }
        result = 0;
        max();
        System.out.println(result);
    }
    static void max() {
        int[] alpha = new int[26];
        for (int i = 0; i < N; i++) {
            int tmp = (int)Math.pow(10, str[i].length()-1);
            for (int j = 0; j < str[i].length(); j++) {
                alpha[(int)str[i].charAt(j) - 65] += tmp;
                tmp /= 10;
            }
        }

        Arrays.sort(alpha);
        int idx = 9;
        for (int i = alpha.length-1; i >= 0; i--) {
            if (alpha[i] == 0)
                break;
            result += alpha[i] * idx;
            idx--;
        }
    }
}
