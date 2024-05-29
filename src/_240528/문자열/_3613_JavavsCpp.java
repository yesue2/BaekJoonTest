package _240528.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _3613_JavavsCpp {
    static char c;
    static StringBuilder sb;
    static boolean isTrans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        isTrans = false;
        String str = br.readLine();
        sb.append(str);

        check();

        if (isTrans) {
            System.out.println(sb);
        } else {
            System.out.println("Error!");
        }
    }

    static void check() {
        boolean isJava = false;
        boolean isCpp = false;
        for (int i = 0; i < sb.length(); i++) {
            c = sb.charAt(i);
            if (i == 0) {
                if (Character.isUpperCase(c)) return; // 첫 글자가 대문자일 때
                if (c == '_') return;  // 첫 글자가 _일 때
            }

            if (i == sb.length() - 1) {
                if (c == '_') return;  // 마지막 글자가 _일 때
            }

            if (Character.isUpperCase(c)) {
                isJava = true;
            }
            if (isJava && c == '_') return;  // 형식이 섞여서 주어졌을 때(대문자와 _)

            if (c == '_') {
                isCpp = true;
                if (i + 1 < sb.length()) {
                    char tmp = sb.charAt(i + 1);
                    if (tmp == '_') return;  // _가 연속 2번 이상 주어졌을 때
                }
            }

            if (isCpp && Character.isUpperCase(c)) return;  // 형식이 섞여서 주어졌을 때(대문자와 _)
        }

        for (int i = 0; i < sb.length(); i++) {
            c = sb.charAt(i);
            if (Character.isUpperCase(c)) {
                javaToCpp(i);
                i++;
            } else if (c == '_') {
                cppToJava(i);
            }
        }
        isTrans = true;
    }

    static void javaToCpp(int i) {
        sb.setCharAt(i, Character.toLowerCase(c));
        sb.insert(i, '_');
    }

    static void cppToJava(int i) {
        if (i + 1 < sb.length()) {
            char nc = sb.charAt(i + 1);
            sb.setCharAt(i + 1, Character.toUpperCase(nc));
            sb.deleteCharAt(i);
        }
    }
}
