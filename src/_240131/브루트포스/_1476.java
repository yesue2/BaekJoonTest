package _240131.브루트포스;

import java.io.*;
import java.util.StringTokenizer;

public class _1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int year  = 0;

        while (true) {
            year++;
            if ((year - E) % 15 == 0 && (year - S) % 28 == 0 && (year - M) % 19 == 0)
                break;
        }
        bw.write(year+"\n");
        bw.flush();
        bw.close();
    }
}
