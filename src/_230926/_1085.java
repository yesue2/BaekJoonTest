package _230926;

import java.util.Scanner;

public class _1085 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int w = scanner.nextInt();
        int h = scanner.nextInt();

        int x_min = Math.min(x, w-x);
        int y_min = Math.min(y, h-y);

        System.out.println(Math.min(x_min, y_min));
    }
}
