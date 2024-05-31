package _240531.배열;

import java.io.*;

public class _2577_숫자의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int a, b, c;
        a = Integer.parseInt(br.readLine());
        b = Integer.parseInt(br.readLine());
        c = Integer.parseInt(br.readLine());

        int tmp = a * b * c;
        String tmpStr = Integer.toString(tmp);
        char[] tmpCh = new char[tmpStr.length()];

        int[] result = new int[10];
        for (int i = 0; i < tmpStr.length(); i++) {
            tmpCh[i] = tmpStr.charAt(i);
            switch (tmpCh[i]) {
                case '0':
                    result[0]++;
                    break;
                case '1':
                    result[1]++;
                    break;
                case '2':
                    result[2]++;
                    break;
                case '3':
                    result[3]++;
                    break;
                case '4':
                    result[4]++;
                    break;
                case '5':
                    result[5]++;
                    break;
                case '6':
                    result[6]++;
                    break;
                case '7':
                    result[7]++;
                    break;
                case '8':
                    result[8]++;
                    break;
                case '9':
                    result[9]++;
                    break;
            }
        }

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
