package _240609.연결리스트;

import java.io.*;
import java.util.*;

public class _5397_키로거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String tmpStr = br.readLine();
            LinkedList<Character> l = new LinkedList<>();
            ListIterator<Character> iterator = l.listIterator();

            for (char ch : tmpStr.toCharArray()) {
                switch (ch) {
                    case '<':
                        if (iterator.hasPrevious()) {  // 리스트의 이전 요소가 존재하는지 여부를 반환
                            iterator.previous();  //  리스트의 이전 요소를 반환, 커서를 이전 위치로 이동
                        }
                        break;
                    case '>':
                        if (iterator.hasNext()) {  // 리스트의 다음 요소가 존재하는지 여부를 반환
                            iterator.next();  // 리스트의 다음 요소를 반환, 커서를 다음 위치로 이동
                        }
                        break;
                    case '-':
                        if (iterator.hasPrevious()) {
                            iterator.previous();
                            iterator.remove();  // ListIterator에서 가장 최근에 반환된 요소를 리스트에서 제거
                        }
                        break;
                    default:
                        iterator.add(ch);  // 문자 리스트에 삽입
                        break;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (char ch : l) {
                sb.append(ch);
            }
            System.out.println(sb);
        }
    }
}
