package _240105.자료구조1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _9093 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] words = br.readLine().split(" ");

            for (String word : words) {
                StringBuilder sb = new StringBuilder(word);
                System.out.print(sb.reverse().append(' '));
            }
            System.out.println();
        }
    }
}
