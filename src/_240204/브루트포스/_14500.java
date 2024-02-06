package _240204.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14500 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] num = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(one(num, N, M));
    }

    public static int one (int[][] num, int N, int M) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 3; j++) {
                int sum = num[i][j] + num[i][j+1] + num[i][j+2] + num[i][j+3];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 3; i++) {
            for (int j = 0; j < M; j++) {
                int sum = num[i][j] + num[i + 1][j] + num[i + 2][j] + num[i + 3][j];
                max = Math.max(max, sum);
            }
        }
        two(num, N, M, max);
        return max;
    }

    public static int two (int[][] num, int N, int M, int max) {
        for (int i = 0; i <= N - 2; i++) {
            for (int j = 0; j <= M-2; j++) {
                int sum = num[i][j] + num[i][j+1] + num[i+1][j] + num[i+1][j+1];
                max = Math.max(max, sum);
            }
        }
        three(num, N, M, max);
        return max;
    }

    public static int three (int[][] num, int N, int M, int max) {
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 2; j++) {
                int sum = num[i][j] + num[i][j+1] + num[i][j+2] + num[i+1][j+2];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 1; j++) {
                int sum = num[i+2][j] + num[i+2][j+1] + num[i+1][j+1] + num[i][j+1];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 2; j++) {
                int sum = num[i][j] + num[i+1][j] + num[i+1][j+1] + num[i+1][j+2];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 1; j++) {
                int sum = num[i][j] + num[i+1][j] + num[i+2][j] + num[i][j+1];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 2; j++) {
                int sum = num[i][j] + num[i][j+1] + num[i][j+2] + num[i+1][j];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 1; j++) {
                int sum = num[i][j] + num[i+1][j] + num[i+2][j] + num[i+2][j+1];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 2; j++) {
                int sum = num[i+1][j] + num[i+1][j+1] + num[i+1][j+2] + num[i][j+2];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 1; j++) {
                int sum = num[i][j] + num[i][j+1] + num[i+1][j+1] + num[i+2][j+1];
                max = Math.max(max, sum);
            }
        }
        four(num, N, M, max);
        return max;
    }

    public static int four (int[][] num, int N, int M, int max) {
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 1; j++) {
                int sum = num[i+1][j] + num[i+2][j] + num[i][j+1] + num[i+1][j+1];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 1; j++) {
                int sum = num[i][j] + num[i+1][j] + num[i+1][j+1] + num[i+2][j+1];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 2; j++) {
                int sum = num[i+1][j] + num[i+1][j+1] + num[i][j+1] + num[i][j+2];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < 2; j++) {
                int sum = num[i][j] + num[i][j+1] + num[i+1][j+1] + num[i+1][j+2];
                max = Math.max(max, sum);
            }
        }
        five(num, N, M, max);
        return max;
    }

    public static int five (int[][] num, int N, int M, int max) {
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 2; j++) {
                int sum = num[i][j] + num[i][j+1] + num[i][j+2] + num[i+1][j+1];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 1; j++) {
                int sum = num[i][j] + num[i+1][j] + num[i+2][j] + num[i+1][j+1];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 2; j++) {
                int sum = num[i+1][j] + num[i][j+1] + num[i+1][j+1] + num[i+1][j+2];
                max = Math.max(max, sum);
            }
        }
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 1; j++) {
                int sum = num[i+1][j] + num[i+1][j+1] + num[i][j+1] + num[i+2][j+1];
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}
