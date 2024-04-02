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
        int[] alpha = new int[26];  // 0부터 25까지 (각 알파벳의 순서-1)에 맞게 저장됨
        for (int i = 0; i < N; i++) {  // i = 0
            int tmp = (int)Math.pow(10, str[i].length()-1);  // 10 * 10 = 100
            for (int j = 0; j < str[i].length(); j++) {
                alpha[(int)str[i].charAt(j) - 65] += tmp;  // G = 71, 71-65 = 6(G는 7번째 알파벳) => alpha[6] = 100 저장
                tmp /= 10;  // 다음 알파벳부터 자릿수가 하나씩 줄어들며 저장
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

