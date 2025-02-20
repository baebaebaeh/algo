package com.ssafy.matrixNumberPlay;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int r, c, k, maxR = 3, maxC = 3;
    static int[][] map = new int[100][100];
    static int[] count;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a[1] == b[1]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            }
    );
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/matrixNumberPlay.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 100 * 100  * 100 = 100만 할만한듯

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int row = 0; row < 3; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 3; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int sec = 0;
        while (sec <= 100) {
            if (map[r - 1][c - 1] == k) {
                break;
            }
            if (maxR >= maxC) {
                for (int row = 0; row < maxR; row++) {
                    calRow(row);
                }
            } else {
                for (int col = 0; col < maxC; col++) {
                    calCol(col);
                }
            }
            sec++;
        }
        if (sec == 101) {
            sec = -1;
        }
        System.out.println(sec);


    }

    private static void calRow(int row) {
        count = new int[101];
        for (int col = 0; col < maxC; col++) {
            if (map[row][col] != 0) {
                count[map[row][col]]++;
            }
        }
        for (int iter = 0; iter < 101; iter++) {
            if (count[iter] == 0) {
                continue;
            }
            pq.add(new int[] { iter, count[iter] });
        }
        for (int tmpCal = 0; tmpCal < maxC; tmpCal++) {
            map[row][tmpCal] = 0;
        }
        int idx = 0;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            map[row][idx] = arr[0];
            map[row][idx + 1] = arr[1];
            idx += 2;
            if (idx == 100) {
                break;
            }
        }
        if (idx > maxC) {
            maxC = idx;
        }
        pq.clear();
    }

    private static void calCol(int col) {
        count = new int[101];
        for (int row = 0; row < maxR; row++) {
            if (map[row][col] != 0) {
                count[map[row][col]]++;
            }
        }
        for (int iter = 0; iter < 101; iter++) {
            if (count[iter] == 0) {
                continue;
            }
            pq.add(new int[] { iter, count[iter] });
        }
        for (int tmpRow = 0; tmpRow < maxR; tmpRow++) {
            map[tmpRow][col] = 0;
        }
        int idx = 0;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            map[idx ][col] = arr[0];
            map[idx + 1][col] = arr[1];
            idx += 2;
            if (idx == 100) {
                break;
            }
        }
        if (idx > maxR) {
            maxR = idx;
        }
        pq.clear();
    }
}