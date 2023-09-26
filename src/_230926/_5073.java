package _230926;

import java.util.Scanner;

public class _5073 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = new int[3];
        int max;

        while (true) {
            arr[0] = scanner.nextInt();
            arr[1] = scanner.nextInt();
            arr[2] = scanner.nextInt();

            if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0)
                break;

            max = -1;
            for (int i : arr) {
                if(max < i) {
                    max = i;
                }
            }

            if (arr[0] == arr[1] && arr[1] == arr[2]){
                System.out.println("Equilateral");
            } else if (max >= arr[0] + arr[1] || max >= arr[1] + arr[2] || max >= arr[1] + arr[2]) {
                System.out.println("Invalid");
            } else if (arr[0] == arr[1] || arr[0] == arr[2] || arr[1] == arr[2]) {
                System.out.println("Isosceles");
            } else if (arr[1] != arr[0] && arr[1] != arr[2] && arr[0] != arr[2]) {
                System.out.println("Scalene");
            }
        }

    }
}
