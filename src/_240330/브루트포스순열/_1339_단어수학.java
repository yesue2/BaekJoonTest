package _240330.브루트포스순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class _1339_단어수학 {
    static int N;
    static String[] str;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
        }
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
        max();
        System.out.println(result);
    }
    static void max() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            String[] arr = str[i].split("");  //arr[0] = G, arr[1] = C
            for (int j = 0; j < arr.length; j++) {
                if (hashMap.containsKey(arr[j])) {
                    if (hashMap.get(arr[j]) < 9-j) {
                        hashMap.remove(arr[j]);
                        hashMap.put(arr[j], 9 - j);
                    }
                } else
                    hashMap.put(arr[j], 9-j);
            }
        }


        int[] add = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            String[] arr = str[i].split("");
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr.length; j++) {
                sb.append(hashMap.get(arr[j]));
            }
            add[i] = Integer.parseInt(sb.toString());
        }
        result = 0;
        for (int i = 0; i < add.length; i++) {
            System.out.println(add[i]);
            result += add[i];
        }
    }
}
