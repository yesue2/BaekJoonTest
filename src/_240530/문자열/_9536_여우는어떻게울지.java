package _240530.문자열;

import java.io.*;
import java.util.*;

public class _9536_여우는어떻게울지 {
    static int t;
    static ArrayList<String> anotherSound;
    static ArrayList<String> soundList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String tmp = br.readLine();
            String[] sound = tmp.split(" ");
            soundList = new ArrayList<>(Arrays.asList(sound));  // Arrays.asList(): 주어진 배열을 고정 크기 리스트로 변환
            anotherSound = new ArrayList<>();

            while (true) {
                tmp = br.readLine();
                if (tmp.equals("what does the fox say?")) break;
                String[] tmpStr = tmp.split(" ");
                anotherSound.add(tmpStr[2]);
            }

            checkFox();

            for (String soundPrint : soundList) {
                System.out.print(soundPrint + " ");
            }
            System.out.println();
        }
    }

    static void checkFox() {
        soundList.removeIf(sound -> anotherSound.contains(sound));
    }
}
