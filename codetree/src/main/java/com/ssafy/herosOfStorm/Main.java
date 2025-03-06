package com.ssafy.herosOfStorm;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, t, upStartRow, upStartCol, downStartRow, downStartCol;
    static int[][] map;
    static int[][] dustStore;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/heroOfStorm.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dustStore = new int[n][m];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < m; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == -1) {
                    list.add(new int[]{row, col});
                }
            }
        }
        int iteration = 0;
        while (iteration < t) {
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    if (map[row][col] == -1) {
                        continue;
                    }
                    int dust = map[row][col] / 5;
                    int numberOf = 0;
                    for (int d = 0; d < 4; d++) {
                        int nextRow = row + dr[d];
                        int nextCol = col + dc[d];
                        if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m || map[nextRow][nextCol] == -1) {
                            continue;
                        }
                        numberOf++;
                        dustStore[nextRow][nextCol] += dust;
                    }
                    map[row][col] -= numberOf * dust;
                }
            }

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    map[row][col] += dustStore[row][col];
                    dustStore[row][col] = 0;
                }
            }

            int[] upStorm = list.get(0);
            int[] downStorm = list.get(1);
            upStartRow = upStorm[0] - 1;
            upStartCol = upStorm[1];
            downStartRow = downStorm[0] + 1;
            downStartCol = downStorm[1];

            for (int iter = 0; iter < upStorm[0] - 1; iter++) {
                int d = 0;
                windup(upStartRow, upStartCol, d);
            }
            for (int iter = 0; iter < m - 1; iter++) {
                int d = 3;
                windup(upStartRow, upStartCol, d);
            }
            for (int iter = 0; iter < upStorm[0]; iter++) {
                int d = 1;
                windup(upStartRow, upStartCol, d);
            }
            for (int iter = 0; iter < m - 2; iter++) {
                int d = 2;
                windup(upStartRow, upStartCol, d);
            }
            map[upStartRow][upStartCol] = 0;


            for (int iter = 0; iter < n - 1 - downStorm[0] - 1; iter++) {
                int d = 1;
                winddown(downStartRow, downStartCol, d);
            }
            for (int iter = 0; iter < m - 1; iter++) {
                int d = 3;
                winddown(downStartRow, downStartCol, d);
            }
            for (int iter = 0; iter < n - 1 - downStorm[0]; iter++) {
                int d = 0;
                winddown(downStartRow, downStartCol, d);
            }
            for (int iter = 0; iter < m - 2; iter++) {
                int d = 2;
                winddown(downStartRow, downStartCol, d);
            }
            map[downStartRow][downStartCol] = 0;
            iteration++;
        }


        int ans = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (map[row][col] != -1) {
                    ans += map[row][col];
                }
            }
        }

        System.out.println(ans);
    }

    private static void winddown(int startRow, int startCol, int d) {
        int nextRow = startRow + dr[d];
        int nextCol = startCol + dc[d];
        map[downStartRow][downStartCol] = map[nextRow][nextCol];
        downStartRow = nextRow;
        downStartCol = nextCol;
    }

    private static void windup(int startRow, int startCol, int d) {
        int nextRow = startRow + dr[d];
        int nextCol = startCol + dc[d];
        map[upStartRow][upStartCol] = map[nextRow][nextCol];
        upStartRow = nextRow;
        upStartCol = nextCol;
    }
}








