package _240117.수학1_연습;

import java.io.*;

public class _1212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String octalStr = br.readLine();

        for (int i = 0; i < octalStr.length(); i++) {
            // 문자를 숫자로 바꿔줌
            String a = Integer.toBinaryString(octalStr.charAt(i) - '0');

            if (a.length() == 2 && i != 0)
                a = "0" + a;
            else if (a.length() == 1 && i != 0)
                a = "00" + a;
            sb.append(a);
        }

        bw.write(sb+"\n");
        bw.flush();
        bw.close();
    }
}
