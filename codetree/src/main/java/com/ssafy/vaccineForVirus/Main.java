package com.ssafy.vaccineForVirus;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, min = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static List<int[]> hospitals;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/vaccineForVirus.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 입력받기 시작
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        hospitals = new ArrayList<>();

        int hospitalIdx = 0;
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == 2) {
                    hospitals.add(new int[]{row, col});
                    hospitalIdx++;
                }
            }
        }
        // 입력받기 끝

        int start = 0;
        int selectCount = 0;
        int[] selectIdx = new int[M];
        combinationAndBfs(start, selectCount, selectIdx);
        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);
    }

    private static void combinationAndBfs(int start, int selectCount, int[] selectIdx) {
        if (selectCount == M) {



            int tmpMin = myBfs(selectIdx);
            if (tmpMin < min) {
                min = tmpMin;
//                for (int i = 0; i < M; i++) {
//                    System.out.print(selectIdx[i]);
//                }
//                System.out.println();
            }
            return;
        }
        for (int idx = start; idx < hospitals.size(); idx++) {
            selectIdx[selectCount] = idx;
            combinationAndBfs(idx + 1, selectCount + 1, selectIdx);
        }
    }

    private static int myBfs(int[] selectIdx) {
        visited = new boolean[N][N];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int maxCount = 0;
        for (int iter = 0; iter < M; iter++) {
            int idx = selectIdx[iter];
            int row = hospitals.get(idx)[0];
            int col = hospitals.get(idx)[1];
            q.add(new int[]{row, col, 0});
            visited[row][col] = true;
        }
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int row = now[0];
            int col = now[1];
            int count = now[2];
            for (int d = 0; d < 4; d++) {
                int nextRow = row + dr[d];
                int nextCol = col + dc[d];
                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N
                        || visited[nextRow][nextCol] || map[nextRow][nextCol] == 1) {
                    continue;
                }
                visited[nextRow][nextCol] = true;
                q.add(new int[]{nextRow, nextCol, count + 1});
            }
            if (maxCount < count && map[row][col] != 2) {
                maxCount = count;
            }
        }

        out : for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    maxCount = Integer.MAX_VALUE;
                    break out;
                }
            }
        }
        return maxCount;
    }
}










