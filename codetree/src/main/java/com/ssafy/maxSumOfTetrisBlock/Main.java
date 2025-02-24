package com.ssafy.maxSumOfTetrisBlock;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans = Integer.MIN_VALUE;
    static int[][] map;
    // [13개][0 = row, 1 = col][0 ~ 3]
    static int[][][] graph = {
            //직선
            {{0, 0, 0, 0}, {0, 1, 2, 3}},
            {{0, -1, -2, -3}, {0, 0, 0, 0}},
            //네모
            {{0, 0, 1, 1}, {0, 1, 0, 1}},
            //니은
            {{0, 1, 2, 2}, {0, 0, 0, 1}},
            {{0, 0, 0, -1}, {0, 1, 2, 2}},
            {{0, 0, -1, -2}, {0, 1, 1, 1}},
            {{0, 0, 0, 1}, {0, 1, 2, 0}},
            //뱀
            {{0, 1, 1, 2}, {0, 0, 1, 1}},
            {{0, 0, 1, 1}, {0, 1, 1, 2}},
            //ㅏ
            {{0, 1, 1, 2}, {0, 0, 1, 0}},
            {{0, 0, -1, 0}, {0, 1, 1, 2}},
            {{0, -1, -1, -2}, {0, 0, -1, 0}},
            {{0, 0, 1, 0}, {0, 1, 1, 2}}
    };

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("data/maxSumOfTetrisBlock.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < m; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                out : for (int iter = 0; iter < 13; iter++) {
                    int sum = 0;
                    for (int d = 0; d < 4; d++) {
                        int nextRow = row + graph[iter][0][d];
                        int nextCol = col + graph[iter][1][d];
                        if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
                            continue out;
                        }
                        sum += map[nextRow][nextCol];
                    }
                    if (sum > ans) {
                        // System.out.println("iter = " + iter);
                        // System.out.println("row = " + row);
                        // System.out.println("col = " + col);
                        // System.out.println();
                        ans = sum;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}