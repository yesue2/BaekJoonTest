package _240111.수학1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10872 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int result =1;

        for (int i = 1; i <= N; i++) {
            result *= i;
        }

        System.out.println(result);
    }
}
