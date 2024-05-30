package _240530.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _15312_이름궁합 {
    static String a, b;
    static char[] ac, bc;
    static int[] num = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();
        ac = new char[a.length()];
        bc = new char[b.length()];

        for (int i = 0; i < a.length(); i++) {
            ac[i] = a.charAt(i);
            bc[i] = b.charAt(i);
        }

        // 97~122
    }
}
