package _240417.이분탐색;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //BFS에서 배열의 위치 정보 관련 클래스
    static class Info{
        int r, c;
        public Info(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static int[][] map; // 입력 배열 저장 배열
    static int[] dr = {-1, 1, 0, 0}; //상하좌우 행 변경값
    static int[] dc = {0, 0, -1, 1}; //상하좌우 열 변경값
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int max = -1;
        int min = 201;
        map = new int[N][N];
        //입력값 저장
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);	//최대값
                min = Math.min(min, map[i][j]);	//최소값
            }
        }
        //이분탐색 진행
        int result = search(max, min, N);
        //최소값 BufferedWriter 저장
        bw.write(String.valueOf(result));
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //이분 탐색을 통해 최소값을 찾는 함수
    static int search(int max, int min, int N){
        int start = 0;
        int end = max - min;
        int result = 201;
        //이분 탐색 진행
        while(start <= end){
            //중간값 구하기
            int mid = (start + end) / 2;
            boolean flag = false;
            //mid의 차이의 모든 경우 BFS 진행
            for(int i = min; i+mid <= max; i++){
                //범위 정보 설정
                int s = i;
                int e = i + mid;
                //시작 위치 범위에 포함되는 지
                if(map[0][0] >= s && map[0][0] <= e){
                    //BFS 진행
                    if(bfs(s, e, N)){	//(N, N) 도달 성공시
                        System.out.println("s, e : " + s + " " + e);
                        flag = true;
                        break;
                    }
                }
            }
            //도달 성공
            if(flag){
                end = mid-1;
                result = Math.min(result, mid);
            }else{		//도달 실패
                start = mid+1;
            }
        }
        return result;
    }
    //BFS을 진행하는 함수
    static boolean bfs(int s, int e, int N){
        Queue<Info> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        //시작값 저장
        q.add(new Info(0, 0));
        visited[0][0] = true;
        //BFS 진행
        while(!q.isEmpty()){
            Info cur = q.poll();
            //(N, N) 도달시
            if(cur.r == N-1 && cur.c == N-1){
                return true;
            }
            //상하좌우 인접한 공간 탐색
            for(int i=0;i<4;i++){
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                //s ≤ map[nr][nc] ≤ e 범위에 포함되는 기준으로 탐색
                if(inSpace(nr, nc, N) && !visited[nr][nc] && map[nr][nc] >= s && map[nr][nc] <= e){
                    q.add(new Info(nr, nc));
                    System.out.println("nx, ny : " + nr + " " + nc);
                    visited[nr][nc] = true;
                }
            }
        }
        return false;
    }
    //이동하려는 칸이 배열에 존재하는 칸인지 확인하는 함수
    static boolean inSpace(int r, int c, int N){
        //존재할 때
        if(r >= 0 && r < N && c >= 0 && c < N){
            return true;
        }
        return false;
    }
}