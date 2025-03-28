package com.ssafy.medusawarrior;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Medusa {
    int nowRow;
    int nowCol;
    ArrayDeque<int[]> ways;

    public Medusa (int nowRow, int nowCol, ArrayDeque<int[]> ways) {
        this.nowRow = nowRow;
        this.nowCol = nowCol;
        this.ways = ways;
    }
}

public class Main {
    static int n, m;
    static int meduRow, meduCol, homeRow, homeCol;

    static int[] orthogonalDr = {-1, 1, 0, 0};
    static int[] orthogonalDc = {0, 0, -1, 1};

    static int[] diagonalDr = {-1, -1, 1, 1};
    static int[] diagonalDc = {-1, 1, 1, -1};

    static int[][] warriorCoords, map;
    static ArrayDeque<int[]> ways;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/medusawarrior.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        warriorCoords = new int[m][2];

        st = new StringTokenizer(br.readLine());
        meduRow = Integer.parseInt(st.nextToken());
        meduCol = Integer.parseInt(st.nextToken());
        homeRow = Integer.parseInt(st.nextToken());
        homeCol = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int iter = 0; iter < m; iter++) {
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            warriorCoords[iter][0] = row;
            warriorCoords[iter][1] = col;
        }

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        ways = findWays();

        if (ways.isEmpty()) {
            System.out.println(-1);
        } else {
            while (!ways.isEmpty()) {
                moveMedusa();
                int d = findMedusaDirection();
            }
        }

    }

    private static ArrayDeque<int[]> findWays() {
        boolean[][] visited = new boolean[n][n];
        visited[meduRow][meduCol] = true;

        ArrayDeque<int[]> initWays = new ArrayDeque<>();
        initWays.add(new int[] {meduRow, meduCol});
        Medusa initMedusa = new Medusa(meduRow, meduCol, initWays);

        ArrayDeque<Medusa> deque = new ArrayDeque<>();
        deque.add(initMedusa);

        while (!deque.isEmpty()) {
            Medusa medusa = deque.poll();
            int nowRow = medusa.nowRow;
            int nowCol = medusa.nowCol;
            ArrayDeque<int[]> ways = medusa.ways;


            for (int d = 0; d < 4; d++) {
                int nextRow = nowRow + orthogonalDr[d];
                int nextCol = nowCol + orthogonalDc[d];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n
                        || map[nextRow][nextCol] == 1 || visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;

                medusa.nowRow = nextRow;
                medusa.nowCol = nextCol;

                ArrayDeque<int[]> newWays = new ArrayDeque<>(ways);

                newWays.add(new int[]{nextRow, nextCol});

                if (nextRow == homeRow && nextCol == homeCol) {
                    return newWays;
                }
                Medusa newMedusa = new Medusa(nextRow, nextCol, newWays);

                deque.add(newMedusa);
            }

        }


        return new ArrayDeque<>();
    }

    private static void moveMedusa() {
        int[] way = ways.poll();
        meduRow = way[0];
        meduCol = way[1];

        System.out.println(meduRow + " " + meduCol);

    }

    private static int findMedusaDirection() {
        int direction = 0;
        int max = -1;
        for (int d = 0; d < 4; d++) {
            int tempMax = -1;



            if (max < tempMax) {
                direction = d;
            }
        }
        return direction;
    }
}