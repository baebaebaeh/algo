package com.ssafy.dragonCurve;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// LinkedList는 128ms
// ArrayList는 101ms
// 최악의 경우
// add -> 1 + 1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 = 2048
// get -> 1 + 1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 + 1024 + 2048(54줄) + 2048(55줄) = 2048 * 3
// get이 3배 더 많아서 get이 O(1)인 ArrayList 사용
public class Main {
    static int n, ans;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/dragonCurve.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        map = new int[101][101];
        ans = 0;

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            map[row][col] = 1;

            list.add(d);

            for (int j = 0; j < g; j++) {
                int listSize = list.size();
                for (int k = 0; k < listSize; k++) {
                    int nowD = list.get(listSize - k - 1);
                    int nextD = (nowD + 1) % 4;
                    list.add(nextD);
                }
            }

            for (int j = 0; j < list.size(); j++) {
                row = row + dr[list.get(j)];
                col = col + dc[list.get(j)];
                map[row][col] = 1;
            }
        }
        for (int r = 0; r < 100; r++) {
            for (int c = 0; c < 100; c++) {
                if (map[r][c] == 1 && map[r][c + 1] == 1 && map[r + 1][c] == 1 && map[r + 1][c + 1] == 1) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}