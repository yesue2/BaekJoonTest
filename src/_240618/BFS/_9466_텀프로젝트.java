package _240618.BFS;
import java.io.*;
import java.util.*;

public class _9466_텀프로젝트 {
    static int n;
    static int[] selected;
    static boolean[] visited;
    static boolean[] finished;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            selected = new int[n];
            visited = new boolean[n];
            finished = new boolean[n];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                selected[j] = Integer.parseInt(st.nextToken()) - 1;
            }

            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    setTeam(j);
                }
            }

            System.out.println(n - count);
        }
    }

    static void setTeam(int start) {
        visited[start] = true;
        int next = selected[start];

        if (!visited[next]) {
            // 현재 학생이 팀을 원하는 학생을 아무도 선택하지 않았을 때
            setTeam(next);
        } else if (!finished[next]) {
            // 현재 학생이 팀을 원하는 학생이 아직 팀이 없을 때, 현재 학생이 나올 때 까지 팀원 탐색
            for (int i = next; i != start; i = selected[i]) {
                // 팀에 속한 학생 수
                count++;
            }
            count++; // 팀에 포함된 자기 자신 추가
        }

        finished[start] = true;  // 결성된 팀의 학생은 더이상 탐색 불가
    }
}
