package _240406.SW역량;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _23290_마법사상어와복제 {
    static ArrayList<ArrayList<Integer>> list;
    static int[] shark;
    static ArrayList<ArrayList<Integer>> replicaList;
    static int sx, sy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        shark = new int[2];
        replicaList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                list.get(i).add(0);
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (list.get(x - 1).get(y - 1) == 0)
                list.get(x - 1).remove(y - 1);
            list.get(x - 1).add(y - 1, d);
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j) + " ");
            }
            System.out.println();
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;

        for (int i = 0; i < S; i++) {
            replicaList = list;
            fishMove();
        }
    }
    static void fishMove() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matchFishMove(i, j, 0)) {

                }
            }
        }
    }
    static boolean matchFishMove(int i, int j, int cnt) {
        if (checkFishMove(i, j))
            return true;
        else if (cnt == 8)
            return false;
        if (list.get(i).get(j) == 1) {
            list.get(i).get(j)
        }
    }
    static boolean checkFishMove(int i, int j) {
        if (list.get(i).get(j) > 0)
    }
}
