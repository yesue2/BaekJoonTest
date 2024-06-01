package _240601.배열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1475_방번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        int[] cnt = new int[10];
        int result = 0;

        for (int i = 0; i < num.length(); i++) {
            int sp = num.charAt(i) - '0';
            cnt[sp]++;
        }

        int tmp = (cnt[6] + cnt[9] + 1) / 2;
        cnt[6] = tmp;
        cnt[9] = tmp;

        for (int i = 0; i < cnt.length; i++) {
            result = Math.max(result, cnt[i]);
        }

        System.out.println(result);
    }
}
