package postmanHanSangDuck;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, startx, starty;
    static char[][] charmap;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/postmanHanSangDuck.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                switch (str[j]) {
                    case "P" :
                        startx = i;
                        starty = j;
                        charmap[i][j] = 'P';
                        break;
                    case "K" :
                        charmap[i][j] = 'K';
                    case "." :

                }
            }
        }

        StringTokenizer st;


    }
}