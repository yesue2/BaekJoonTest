package _230929.Sort2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] number = new int[n];

        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(number);

        for (int i = 0; i < n; i++) {
            sb.append(number[i] + "\n");
        }

        System.out.println(sb);
    }
}
