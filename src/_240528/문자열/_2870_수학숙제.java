package _240528.문자열;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class _2870_수학숙제 {
    static int N;
    static String[] str;
    static ArrayList<BigInteger> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        str = new String[N];
        result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
        }

        checkExist();
        Collections.sort(result);

        for (BigInteger num : result) {
            System.out.println(num);
        }
    }

    static void checkExist() {
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < str[i].length(); j++) {
                char c = str[i].charAt(j);

                if (Character.isDigit(c)) {  // 숫자를 만났을 때
                    sb.append(c);
                } else {
                    if (sb.length() > 0) {
                        result.add(new BigInteger(sb.toString()));
                        sb.setLength(0);
                    }
                }
            }

            if (sb.length() > 0) {
                result.add(new BigInteger(sb.toString()));  // 마지막 숫자 처리
            }
        }
    }
}

