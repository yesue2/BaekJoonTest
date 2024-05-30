package _240530.문자열;

import java.io.*;
import java.util.*;

public class _15312_이름궁합 {
    static String a, b;
    static List<Integer> result;
    static int[] num = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();

        result = new ArrayList<>();

        for (int i = 0; i < a.length(); i++) {
            result.add(num[a.charAt(i) - 'A']);
            result.add(num[b.charAt(i) - 'A']);
        }

        while (result.size() > 2) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < result.size() - 1; i++) {
                tmp.add((result.get(i) + result.get(i + 1)) % 10);
            }
            result = tmp;
        }

        System.out.print(result.get(0));
        System.out.println(result.get(1));
    }
}
