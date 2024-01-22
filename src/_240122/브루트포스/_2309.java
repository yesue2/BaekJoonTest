package _240122.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] height = new int[9];
        int sum = 0;

        for (int i = 0; i < height.length; i++) {
            height[i] = Integer.parseInt(br.readLine());
            sum += height[i];
        }

        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i+1; j < height.length; j++) {
                if (sum - height[i] - height[j] == 100) {
                    height[i] = 0;
                    height[j] = 0;
                    Arrays.sort(height);
                    for (int k = 2; k < height.length; k++) {
                        System.out.println(height[k]);
                    }
                    return;
                }
            }
        }
    }
}
