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

        System.out.println(result);
        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        sb.append(result.get(result.size()-1)).append('\n').append(result.get(0));
        System.out.println(sb);
    }
    static void perm(int idx) {
        if (idx == tgt.length) {
            result.add(makeNum());
            return;
        }
        for (int i = 0; i < src.length; i++) {
            if (idx > 0 && !check(idx, src[i])) {  // 부등호에 따른 순서 틀리면 continue
                continue;
            }
            if (!checked[i]) {
                checked[i] = true;
                tgt[idx] = src[i];
                perm(idx+1);
                checked[i] = false;
            }
        }
    }
    static boolean check(int idx, int num) {  // 부등호 판별
        // 숫자 순서가 맞으면 true, 틀리면 false
        if (A[idx-1] == '<')
            return tgt[idx-1] < num;
        else
            return tgt[idx-1] > num;
    }
    static String makeNum() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tgt.length; i++)
            sb.append(tgt[i]);
        return sb.toString();
    }
}
