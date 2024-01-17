package _240117.수학1_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1373 {

    public static StringBuilder sb = new StringBuilder();
    public static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arrStr = br.readLine().split("");
        int[] arrInt = new int[arrStr.length];

        for (int i = 0; i < arrStr.length; i++) {
            arrInt[i] = Integer.parseInt(arrStr[i]);
            queue.add(arrInt[i]);
        }

        if (queue.size()%3 == 0) {
            binaryToOctal(queue);
        } else if (queue.size()%3 == 1){
            sb.append(queue.poll());
            binaryToOctal(queue);
        } else {
            sb.append(queue.poll()*2+queue.poll());
            binaryToOctal(queue);
        }
        System.out.println(sb);
    }

    public static void binaryToOctal (Queue<Integer> queue) {
        int size = queue.size();

        // 2진수를 3씩 묶은 칸 수 만큼 반복 진행
        for (int i = 0; i < size/3; i++) {
            int[] tmp = new int[3];
            int result = 0;

            //한 칸 내에서 j의 수에 따라 연산 수행
            for (int j = 0; j < 3; j++) {
                tmp[j] = queue.poll();
                if (j == 0)
                    result += tmp[j]*4;
                else if (j == 1)
                    result += tmp[j]*2;
                else
                    result += tmp[j];
            }
            sb.append(result);
        }
    }
}
