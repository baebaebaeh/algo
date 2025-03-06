package com.ssafy.tosteggmold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    
    static int[][] map;
    static boolean[][] visited;
    static int n, L, R, ans = -1;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1}; // 상하좌우
    static boolean separate = true;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/tosteggmold.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        
        map = new int[n][n];
        visited = new boolean[n][n];



        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 입력 끝
        while (separate) {
            separate = false;
            ans++;

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (!visited[row][col]) {
                        bfs(row, col);
//                        for (int i = 0; i < n; i++) {
//                            for (int j = 0; j < n; j++) {
//                                System.out.print(visited[i][j] + " ");
//                            }
//                            System.out.println();
//                        }
//                        System.out.println();

//                        for (int i = 0; i < n; i++) {
//                            for (int j = 0; j < n; j++) {
//                                System.out.print(map[i][j] + " ");
//                            }
//                            System.out.println();
//                        }
//                        System.out.println();
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    visited[j][i] = false;
                }
            }

        }
        bw.write(ans + "");
        bw.flush();

    }

    private static void bfs(int row, int col) {
        visited[row][col] = true;

        ArrayDeque<int[]> deque2 = new ArrayDeque<>();
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        int count = 0;
        int sum = 0;

        deque.add(new int[] {row , col});
        deque2.add(new int[] {row , col});

        while (!deque.isEmpty()) {
            int[] now = deque.poll();
            int nowRow = now[0];
            int nowCol = now[1];

            count++;
            sum += map[nowRow][nowCol];

            for (int d = 0; d < 4; d++) {

                int nextRow = nowRow + dr[d];
                int nextCol = nowCol + dc[d];


                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                    continue;
                }
                int diff = Math.abs(map[nowRow][nowCol] - map[nextRow][nextCol]);
                if (diff > R || diff < L || visited[nextRow][nextCol]) {
                    continue;
                }
                separate = true;
                int[] next = {nextRow, nextCol};

                visited[nextRow][nextCol] = true;

                deque.add(next);
                deque2.add(next);
            }
        }
        int avg = sum / count;
        while (!deque2.isEmpty()) {
            int[] now = deque2.poll();
            map[now[0]][now[1]] = avg;
        }


    }
}