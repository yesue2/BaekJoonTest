package _230928.Sort;

import java.util.Scanner;

public class _2750 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        int[] number = new int[count];

        for (int i = 0; i < number.length; i++) {
            number[i] = scanner.nextInt();
        }

        int tmp;
        for (int i = 0; i < count; i++) {
            for (int j = i+1; j < count; j++) {
                if(number[i] > number[j]) {
                    tmp = number[i];
                    number[i] = number[j];
                    number[j] = tmp;
                }
            }
        }


        for (int i = 0; i < number.length; i++) {
            System.out.println(number[i]);
        }
    }
}
