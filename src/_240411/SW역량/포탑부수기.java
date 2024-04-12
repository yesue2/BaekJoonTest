package _240411.SW역량;

import java.io.*;
import java.util.*;

public class 포탑부수기 {
    static int N, M, K, result, attackerN, attackerM, targetN, targetM, minD;
    static int[][] map;
    static int[][] attacker;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<Integer> minPath;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];  // 포탑 저장
        attacker = new int[N][M];  // 각 포탑에 가장 마지막 공격 저장
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {   // 각 포탑의 마지막 공격 초기화
            for (int j = 0; j < M; j++) {
                attacker[i][j] = -1;
            }
        }
        repeat();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0)
                    result = Math.max(map[i][j], result);
            }
        }
        System.out.println(result);
    }

    static void repeat() {
        for (int i = 0; i < K; i++) {
            System.out.println("------------------"+ i + "번째 공격");
            setTarget();  // 타겟 세팅
            setAttacker();  // 공격자 세팅
            attacker[attackerN][attackerM] = i;  // 공격자 공격 시기 저장
            System.out.println("공격자 : " + attackerN + " " + attackerM);
            System.out.println("공격자 공격력 " + map[attackerN][attackerM]);
            System.out.println("타겟 : " + targetN + " " + targetM);

            minPath = new ArrayList<>();
            ArrayList<Integer> path = new ArrayList<>();
            minD = Integer.MAX_VALUE;
            visited = new boolean[N][M];
            dfs(attackerN, attackerM, 0, path);
            System.out.println("최단거리 : " + minPath.size());
            for (int j = 0; j < minPath.size(); j++) {
                System.out.println(minPath.get(j));
            }

            if (minPath.size() == 0) pAttack();  // 포탄 공격
            else rAttack();  // 레이저 공격
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    System.out.print(visited[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
            if (end()) return;  // 하나의 포탑 빼고 모두 부셔졌으면 끝내기

            addScore();  // 공격에 관련 없던 포탑의 수 모두 +1

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    System.out.print(map[j][k] + " ");
                }
                System.out.println();
            }
        }
    }

    static void setAttacker() {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && map[i][j] < min) {
                    min = map[i][j];    // 숫자가 가장 낮은 포탑의 값
                    attackerN = i;   // 숫자가 가장 낮은 포탑의 N값
                    attackerM = j;   // 숫자가 가장 낮은 포탑의 M값
                } else if (map[i][j] > 0 && map[i][j] == min) {  // 숫자 중복 시
                    if (attacker[attackerN][attackerM] < attacker[i][j]) {  // 가장 최근에 공격자였던 포탑으로 저장
                        attackerN = i;
                        attackerM = j;
                    } else if (attacker[attackerN][attackerM] == attacker[i][j]) {  // 공격자 시기 중복 시
                        if (attackerN + attackerM < i + j) {  // N+M이 가장 큰 포탑으로 저장
                            attackerN = i;
                            attackerM = j;
                        } else if (attackerN + attackerM == i + j) {  //N+M 중복 시
                            if (attackerM < j) {  // M이 가장 큰 포탑으로 저장
                                attackerN = i;
                                attackerM = j;
                            }
                        }
                    }
                }
            }
        }
        map[attackerN][attackerM] += N + M;  // 선정된 공격자 공격력 증가
    }

    static void setTarget() {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && map[i][j] > max) {
                    max = map[i][j];    // 숫자가 가장 높은 포탑의 값
                    targetN = i;   // 숫자가 가장 높은 포탑의 N값
                    targetM = j;   // 숫자가 가장 높은 포탑의 M값
                    System.out.println("타겟의 max의 좌표 : " + i + " " + j);
                    System.out.println("타겟의 max : " + max);
                } else if (map[i][j] > 0 && map[i][j] == max) {  // 숫자 중복 시
                    if (attacker[targetN][targetM] > attacker[i][j]) {  // 공격자였을 때가 가장 오래된 포탑으로 저장
                        System.out.println("attacker[targetN][targetM]의 공격자 시기 : " + attacker[targetN][targetM]);
                        System.out.println("attacker[i][j]의 공격자 시기 : " + attacker[i][j]);
                        targetN = i;
                        targetM = j;
                    } else if (attacker[targetN][targetM] == attacker[i][j]) {  // 공격자 시기 중복 시
                        System.out.println("타겟의 중복되는 공격자 시기 : " + attacker[targetN][targetM]);
                        if (targetN + targetM > i + j) {  // N+M이 가장 작은 포탑으로 저장
                            targetN = i;
                            targetM = j;
                        } else if (targetN + targetM == i + j) {  // N+M 중복 시
                            if (targetM > j) {  // M이 가장 작은 포탑으로 저장
                                targetN = i;
                                targetM = j;
                            }
                        }
                    }
                }
            }
        }
    }

    static void dfs(int cx, int cy, int d, ArrayList<Integer> path) {
        if (cx == targetN && cy == targetM) {
            if (minD > d) {  // 최단경로 저장
                minD = d;
                minPath.clear();
                minPath.addAll(path);
                System.out.println("최단 거리 : " + minD);
            } else if (minD == d)  // 최단경로가 두 개 이상일 때
                updateMinPath(path);  // 경로 비교 후 우선순위 경로로 업데이트
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + cx;
            int ny = dy[i] + cy;
            System.out.println("바뀌기 전 : " + nx + " " + ny);
            if (nx < 0) nx += N;
            else if (nx >= N) nx -= N;
            if (ny < 0) ny += M;
            else if (ny >= M) ny -= M;
            if (!visited[nx][ny] && map[nx][ny] > 0) {
                visited[nx][ny] = true;
                path.add(i);
                System.out.println("cnt : " + (d + 1));
                dfs(nx, ny, d + 1, path);
                visited[nx][ny] = false;
                path.remove(path.size() - 1);
            }
        }
        System.out.println("d : " + d);
    }

    static void updateMinPath(ArrayList<Integer> path) {
        for (int i = 0; i < minPath.size(); i++) {
            int min = minPath.get(i);
            int cur = path.get(i);
            if (min != cur) {
                if (min > cur) {
                    minPath.clear();
                    minPath.addAll(path);
                    break;
                } else {
                    break;
                }
            }
        }
    }

    static void rAttack() {
        System.out.println("레이저 공격 시작");
        visited = new boolean[N][M];
        map[targetN][targetM] = map[targetN][targetM] - map[attackerN][attackerM];  // 목표 포탑 공격
        visited[targetN][targetM] = true;
        visited[attackerN][attackerM] = true;

        int nx = attackerN;
        int ny = attackerM;
        for (int j = 0; j < minPath.size() - 1; j++) {  // 경로 포탑 공격
            nx = nx + dx[minPath.get(j)];
            ny = ny + dy[minPath.get(j)];
            System.out.println("공격할 nx, ny : " + nx + " " + ny);
            System.out.println();
            if (nx < 0)
                nx += N;
            else if (ny < 0)
                ny += M;
            else if (nx >= N)
                nx -= N;
            else if (ny >= M)
                ny -= M;
            map[nx][ny] = map[nx][ny] - map[attackerN][attackerM] / 2;
            visited[nx][ny] = true;
        }
    }

    static void pAttack() {
        System.out.println("포탄 공격 시작");
        visited = new boolean[N][M];
        map[targetN][targetM] -= map[attackerN][attackerM];  // 목표 포탑 공격
        visited[targetN][targetM] = true;
        visited[attackerN][attackerM] = true;

        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int i = 0; i < 8; i++) {  // 주변 8개 포탑 공격
            int nx = dx[i] + targetN;
            int ny = dy[i] + targetM;
            System.out.println("포탄 공격 주변 : " + nx + " " + ny);

            if ((nx >= N && ny >= M) || (nx >= N && ny < 0) || (nx < 0 && ny < 0) || (nx < 0 && ny >= M)) {
                continue;
            } else if (nx < 0) {
                if (map[nx + N][ny] > 0) {  // 바꿔서 갈 수 있을 때
                    nx += N;
                    System.out.println("포탄 공격 주변 바뀌기 후 : " + nx + " " + ny);
                } else continue;  // 바꿔도 부셔져 있을 때
            } else if (ny < 0) {
                if (map[nx][ny + M] > 0) {
                    ny += M;
                    System.out.println("포탄 공격 주변 바뀌기 후 : " + nx + " " + ny);
                } else continue;
            } else if (nx >= N) {
                if (map[nx - N][ny] > 0) {
                    nx -= N;
                    System.out.println("포탄 공격 주변 바뀌기 후 : " + nx + " " + ny);
                } else continue;
            } else if (ny >= M) {
                if (map[nx][ny - M] > 0) {
                    ny -= M;
                    System.out.println("포탄 공격 주변 바뀌기 후 : " + nx + " " + ny);
                } else continue;
            }
            if (map[nx][ny] > 0) {
                map[nx][ny] = map[nx][ny] - map[attackerN][attackerM] / 2;
                visited[nx][ny] = true;
            }
        }
    }

    static void addScore() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    map[i][j] += 1;
                }
            }
        }
    }

    static boolean end() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0)
                    cnt++;
            }
        }
        if (cnt == 1)
            return true;
        return false;
    }
}
