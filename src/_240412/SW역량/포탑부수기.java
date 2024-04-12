package _240412.SW역량;

import java.io.*;
import java.util.*;

class Node {
    int x, y;
    List<Node> path;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.path = new ArrayList<>();
    }
}
public class 포탑부수기 {
    static int N, M, K, result, attackerN, attackerM, targetN, targetM;
    static int[][] map;
    static int[][] attacker;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<Node> minPath;

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
            System.out.println("------------------" + i + "번째 공격");
            setTarget();  // 타겟 세팅
            setAttacker();  // 공격자 세팅
            attacker[attackerN][attackerM] = i;  // 공격자 공격 시기 저장
            System.out.println("공격자 : " + attackerN + " " + attackerM);
            System.out.println("공격자 공격력 " + map[attackerN][attackerM]);
            System.out.println("타겟 : " + targetN + " " + targetM);
            System.out.println("타겟 값 : " + map[targetN][targetM]);

            minPath = new ArrayList<>();
            visited = new boolean[N][M];
            bfs();

            System.out.println("최단거리 : " + minPath.size());
            for (int j = 0; j < minPath.size(); j++) {
                System.out.print(minPath.get(j).x + " " + minPath.get(j).y + ", ");
            }
            System.out.println();

            visited = new boolean[N][M];
            map[targetN][targetM] -= map[attackerN][attackerM];  // 목표 포탑 공격
            visited[targetN][targetM] = true;
            visited[attackerN][attackerM] = true;
            if (!minPath.isEmpty()) rAttack();   // 레이저 공격
            else pAttack();  // 포탄 공격
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

    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(attackerN, attackerM));
        visited[attackerN][attackerM] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;

            if (x == targetN && y == targetM) {  // 목표 지점 도달 시 최단 경로 업데이트
                cur.path.add(cur);
                minPath = new ArrayList<>(cur.path);
                return;
            }

            for (int i = 0; i < 4; i++) {  // 방향 우선순위에 따라 순회
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0) nx += N;  // 격자 넘어가면 반대편으로 넘어가기
                else if (nx >= N) nx -= N;
                if (ny < 0) ny += M;
                else if (ny >= M) ny -= M;

                if (!visited[nx][ny] && map[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    Node next = new Node(nx, ny);
                    next.path.addAll(cur.path);
                    next.path.add(cur);
                    queue.add(next);
                }
            }
        }
    }

    static void rAttack() {
        System.out.println("레이저 공격 시작");
        for (int j = 1; j < minPath.size() - 1; j++) {  // 경로 포탑 공격
            int nx = minPath.get(j).x;
            int ny = minPath.get(j).y;
            System.out.println("공격할 nx, ny : " + nx + " " + ny);
            map[nx][ny] -= map[attackerN][attackerM] / 2;
            visited[nx][ny] = true;
        }
    }

    static void pAttack() {
        System.out.println("포탄 공격 시작");
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int i = 0; i < 8; i++) {  // 주변 8개 포탑 공격
            int nx = dx[i] + targetN;
            int ny = dy[i] + targetM;

            // 격자 넘어가면 반대편으로 넘어가기
            if ((nx >= N && ny >= M) || (nx >= N && ny < 0) || (nx < 0 && ny < 0) || (nx < 0 && ny >= M)) continue;
            if (nx < 0) nx += N;
            else if (nx >= N) nx -= N;
            if (ny < 0) ny += M;
            else if (ny >= M) ny -= M;
            System.out.println("주변 공격 : " + nx + " " + ny);

            if ((nx == attackerN && ny == attackerM) || map[nx][ny] <= 0) continue;
            map[nx][ny] -= map[attackerN][attackerM] / 2;
            visited[nx][ny] = true;

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

    static void addScore() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    map[i][j] += 1;
                } else if (map[i][j] <= 0) {
                    map[i][j] = 0;
                }
            }
        }
    }
}
