package _230928.Sort;

import java.util.Arrays;
import java.util.Scanner;

public class _2750 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        int[] number = new int[count];

        for (int i = 0; i < number.length; i++) {
            number[i] = scanner.nextInt();
        }

        Arrays.sort(number);


        for (int i = 0; i < number.length; i++) {
            System.out.println(number[i]);
        }
    }
}
