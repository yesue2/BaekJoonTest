package _240320.소프티어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 자동차테스트 {
    static int n, q;
    static int[] f;
    static int[] m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        f = new int[n];
        m = new int[q];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            f[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < q; i++) {
            m[i] = Integer.parseInt(br.readLine());
        }
        test();
    }

    static void test() {
        for(int i = 0; i < q; i++) {
            int cntLow = 0;
            int cntTop = 0;
            boolean check = false;
            for(int j = 0; j < n; j++) {
                if(m[i] == f[j])
                    check = true;
            }
            if (!check) {
                System.out.println(0);
                continue;
            }
            for(int j = 0; j < n; j++) {
                if(m[i] < f[j])
                    cntTop++;
                else if(m[i] > f[j])
                    cntLow++;
            }
            int result = cntLow*cntTop;
            System.out.println(result);
        }
    }
}
