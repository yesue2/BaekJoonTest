package _240111.수학1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[N+1];

        //0과 1은 소수가 아니므로 2부터 default값을 true로 바꿔줌
        for (int i = 2; i < isPrime.length; i++) {
            isPrime[i] = true;
        }

        //2부터 N의 제곱근까지의 모든 수 확인
        for (int i = 2; i <= Math.sqrt(N); i++) {
            //해당 수가 소수라면, 해당 수를 제외한 배수들을 모두 false 처리
            for (int j = i*i; j <= N; j += i) {
                isPrime[j] = false;
            }
        }

        for (int i = 0; i < isPrime.length; i++) {
            if (i >= M && isPrime[i] == true) {
                sb.append(i).append('\n');
            }
        }
        System.out.println(sb);
    }
}
