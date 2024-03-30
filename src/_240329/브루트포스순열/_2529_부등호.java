package _240329.브루트포스순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class _2529_부등호 {
    static int k;
    static char[] A;
    static int[] src = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    static int[] tgt;
    static boolean[] checked = new boolean[src.length];
    static ArrayList<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        A = new char[k];
        tgt = new int[k+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            A[i] = st.nextToken().charAt(0);
        }

        perm(0);

        Collections.sort(result);
        StringBuilder sb = new StringBuilder(result.get(result.size()-1) + "\n" + result.get(0));
        System.out.println(sb);
    }
    static void perm(int idx) {
        if (idx == tgt.length) {
            result.add(makeNum());
            return;
        }

        for (int i = 0; i < src.length; i++) {
            if (checked[i])
                continue;
            if (idx > 0 && !checkSign(idx, src[i]))
                continue;
            checked[i] = true;
            tgt[idx] = src[i];
            perm(idx+1);
            checked[i] = false;
        }
    }
    static boolean checkSign(int idx, int num) {
        if (A[idx-1] == '<')
            return tgt[idx-1] < num;
        else
            return tgt[idx-1] > num;
    }
    static String makeNum() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tgt.length; i++) {
            sb.append(tgt[i]);
        }
        return sb.toString();
    }
}
