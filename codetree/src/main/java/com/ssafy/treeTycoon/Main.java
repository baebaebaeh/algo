package com.ssafy.treeTycoon;

import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] tree;
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};
    static int[] diagonalR = {-1, -1, 1, 1};
    static int[] diagonalC = {1, -1, -1, 1};

    static ArrayDeque<int[]> deque = new ArrayDeque<>();

    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/treeTycoon.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        tree = new int[n][n];


        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                tree[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        supplementsInit();

        for (int iter = 0; iter < m; iter++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int p = Integer.parseInt(st.nextToken());

            moveSupplements(d, p);

            giveSupplements();

            createSupplements();
        }

        int ans = sumTree();

        System.out.println(ans);
    }

    private static int sumTree() {
        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                sum += tree[row][col];
            }
        }
        return sum;
    }

    private static void createSupplements() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (tree[row][col] >= 2 && !visited[row][col]) {
                    tree[row][col] -= 2;
                    deque.add(new int[] {row, col});
                }
            }
        }
    }

    private static void giveSupplements() {
        // 방문배열 생성
        visited = new boolean[n][n];

        ArrayDeque<int[]> temp = new ArrayDeque<>();

        int count = deque.size();
        for (int iter = 0; iter < count; iter++) {
            int[] now = deque.poll();
            int nowRow = now[0];
            int nowCol = now[1];

            tree[nowRow][nowCol] += 1;

            // 방문 시킨다.
            visited[nowRow][nowCol] = true;
            temp.add(now);
        }
        for (int iter = 0; iter < count; iter++) {
            int[] now = temp.poll();
            int nowRow = now[0];
            int nowCol = now[1];
            for (int d = 0; d < 4; d++) {
                int nextRow = nowRow + diagonalR[d];
                int nextCol = nowCol + diagonalC[d];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                    continue;
                }
                // 먼저 증가시켜도 되나 싶었는데 어차피 1이상이라 괜찮다.
                if (tree[nextRow][nextCol] >= 1) {
                    tree[nowRow][nowCol] += 1;
                }
            }
        }
    }

    private static void moveSupplements(int d, int p) {
        int count = deque.size();
        for (int iter = 0; iter < count; iter++) {
            int[] now = deque.poll();
            int nowRow = now[0];
            int nowCol = now[1];

            int nextRow = nowRow + (dr[d] * p);
            int nextCol = nowCol + (dc[d] * p);

            nextRow += n;
            nextCol += n;

            nextRow %= n;
            nextCol %= n;

            deque.add(new int[] {nextRow, nextCol});
        }
    }


    private static void supplementsInit() {
        deque.add(new int[] {n - 1, 0});
        deque.add(new int[] {n - 1, 1});
        deque.add(new int[] {n - 2, 0});
        deque.add(new int[] {n - 2, 1});
    }
}

















