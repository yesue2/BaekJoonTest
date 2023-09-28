package _230928.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _2750_BufferedReader {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());
        int[] number = new int[count];

        for (int i = 0; i < count; i++) {
            number[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(number);

        for (int result : number) {
            System.out.println(result);
        }
    }
}
