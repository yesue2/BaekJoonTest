package _230928.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2587 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] number = new  int[5];
        int avg = 0;
        int mid = 0;

        for (int i = 0; i < number.length; i++) {
            number[i] = Integer.parseInt(br.readLine());
        }

        int add = 0;
        for (int i = 0; i < number.length; i++) {
            add += number[i];
        }
        avg = add/5;

        int tmp;
        for (int i = 0; i < number.length; i++) {
            for (int j = i+1; j < number.length; j++) {
                if(number[i] > number[j]) {
                    tmp = number[i];
                    number[i] = number[j];
                    number[j] = tmp;
                }
            }
        }
        mid = number[2];

        System.out.println(avg);
        System.out.println(mid);
    }
}
