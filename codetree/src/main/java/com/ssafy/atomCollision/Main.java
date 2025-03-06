package com.ssafy.atomCollision;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class Main {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    static int[][] atoms;

    static int n, m, k;
    static List<List<ArrayDeque<Integer>>> map1;
    static List<List<ArrayDeque<Integer>>> map2;
    static boolean isMap2 = false;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/atomCollision.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());


        map1 = new ArrayList<>();
        map2 = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            map2.add(new ArrayList<>());
            map1.add(new ArrayList<>());
            for (int col = 0; col < n; col++) {
                map2.get(row).add(new ArrayDeque<>());
                map1.get(row).add(new ArrayDeque<>());
            }
        }

        atoms = new int[m][3];

        for (int iter = 0; iter < m; iter++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            atoms[iter][0] = m;
            atoms[iter][1] = s;
            atoms[iter][2] = d;

            map1.get(row).get(col).add(iter);
        }

        moveAtom();
        sumAndSeparate();
    }

    public static void sumAndSeparate() {
        List<List<ArrayDeque<Integer>>> prevMap;
        if (isMap2) {
            prevMap = map2;
        } else {
            prevMap = map1;
        }
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                ArrayDeque<Integer> searchDeque = prevMap.get(row).get(col);
                int dequeSize = searchDeque.size();
                int sumMass = 0;
                int sumSpeed = 0;
                int sumDirection = 0;

                boolean isOdd = false;
                boolean isEven = false;

                boolean isOrthogonal = false;

                if (dequeSize <= 1) {
                    continue;
                }

                for (int iter = 0; iter < dequeSize; iter++) {
                    int atomIndex = searchDeque.poll();
                    int m = atoms[atomIndex][0];
                    int s = atoms[atomIndex][1];
                    int d = atoms[atomIndex][2];

                    sumMass += m;
                    sumSpeed += s;
                    sumDirection += d;

                    int nextRow = (row + dr[d]) % 8;
                    int nextCol = (col + dc[d]) % 8;
                }
                int mass = sumMass / 5;
                int speed = sumSpeed / dequeSize;
                if (sumDirection % 2 == 0) {

                }
            }
        }
    }

    public static void moveAtom() {
        List<List<ArrayDeque<Integer>>> prevMap;
        List<List<ArrayDeque<Integer>>> nextMap;
        if (isMap2) {
            prevMap = map2;
            nextMap = map1;
            isMap2 = false;
        } else {
            prevMap = map1;
            nextMap = map2;
            isMap2 = true;
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                ArrayDeque<Integer> searchDeque = prevMap.get(row).get(col);
                while (!searchDeque.isEmpty()) {
                    int atomIndex = searchDeque.poll();
                    int m = atoms[atomIndex][0];
                    int s = atoms[atomIndex][1];
                    int d = atoms[atomIndex][2];

                    int nextRow = (row + dr[d]) % 8;
                    int nextCol = (col + dc[d]) % 8;

                    nextMap.get(nextRow).get(nextCol).add(atomIndex);
                }
            }
        }
    }
}
//