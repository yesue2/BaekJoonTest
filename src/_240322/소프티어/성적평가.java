package _240322.소프티어;


import java.util.*;
import java.io.*;

public class 성적평가 {
    static class Contest {
        int idx, score;

        Contest(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Contest[][] contest = new Contest[4][N];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                contest[i][j] = new Contest(j, Integer.parseInt(st.nextToken()));
            }
        }

        // 합계 점수 저장
        for (int i = 0; i < N; i++) {
            contest[3][i] = new Contest(i, contest[0][i].score + contest[1][i].score + contest[2][i].score);
        }

        // 점수 내림차순 정렬
        for (Contest[] c : contest) {
            Arrays.sort(c, new Comparator<Contest>() {
                @Override
                public int compare(Contest o1, Contest o2) {
                    return Integer.compare(o2.score, o1.score);
                }
            });
        }

        int[][] rank = new int[4][N];
        for (int i = 0; i < 4; i++) {
            int rankCnt = 0;
            int sameScoreCnt = 1;
            int preScore = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                int curScore = contest[i][j].score;
                if (curScore < preScore) {
                    preScore = curScore;
                    if (sameScoreCnt > 1) {
                        rankCnt += sameScoreCnt;
                        sameScoreCnt = 1;
                    } else
                        rankCnt++;

                    rank[i][contest[i][j].idx] = rankCnt;
                } else {
                    sameScoreCnt++;
                    rank[i][contest[i][j].idx] = rankCnt;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                bw.write(String.valueOf(rank[i][j]));
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}
