package _240405.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1931_회의실배정 {
    static int N;
    static int result = 0;
    static Queue<meeting> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        queue = new PriorityQueue<>(N, (meeting m1, meeting m2) ->
                m1.end == m2.end ? m1.start - m2.start : m1.end - m2.end);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            queue.add(new meeting(s, e));
        }
        min();
        System.out.println(result);
    }
    static void min() {
        int end = 0;
        while (!queue.isEmpty()) {
            meeting m = queue.poll();
            if (m.start >= end) {
                end = m.end;
                result++;
            }
        }
    }
    static class meeting {
        int start, end;
        meeting (int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
