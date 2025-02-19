package com.ssafy.ride;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][][] preferGraph;
    static int[][] preferMap, realMap;
    static int[] order;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/ride.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());


        order = new int[n * n];
        preferMap = new int[n * n][4];
        realMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(realMap[i], -1);
        }
        preferGraph = new int[n][n][n * n];

        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            order[i] = row;
            for (int col = 0; col < 4; col++) {
                preferMap[row][col] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        List<int[]> coords = new ArrayList<>();
        for (int i = 0; i < n * n; i++) {
            int nowStudent = order[i];
            coords = findMostPrefer(nowStudent);
            coords = findEmpty(coords);
            
            int[] coord = coords.get(0);
            realMap[coord[0]][coord[1]] = nowStudent;
            
            increasePrefer(coord);
        }

        int sum = calculate();
        System.out.println(sum);
    }

    private static int calculate() {
        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int preferCount = 0;
                int now = realMap[row][col];
                for (int k = 0; k < 4; k++) {
                    int nextRow = row + dr[k];
                    int nextCol = col + dc[k];
                    if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                        continue;
                    }

                    int next = realMap[nextRow][nextCol];
                    for (int index = 0; index < 4; index++) {
                        if (preferMap[now][index] == next) {
                            preferCount++;
                        }
                    }
                }

                int nowScore = 0;
                if (preferCount > 0) {
                    nowScore += 1;
                    for (int index = 1; index < preferCount; index++) {
                        nowScore *= 10;
                    }
                }
                sum += nowScore;
            }
        }
        return sum;
    }

    private static void increasePrefer(int[] coord) {
        int nowStudent = realMap[coord[0]][coord[1]];
        for (int j = 0; j < 4; j++) {
            int nowStudentPrefer = preferMap[nowStudent][j];
            for (int k = 0; k < 4; k++) {
                int nextCoordRow = coord[0] + dr[k];
                int nextCoordCol = coord[1] + dc[k];
                if (nextCoordRow < 0 || nextCoordRow >= n || nextCoordCol < 0 || nextCoordCol >= n) {
                    continue;
                }
                preferGraph[nextCoordRow][nextCoordCol][nowStudentPrefer]++;
            }
        }
    }

    private static List<int[]> findEmpty(List<int[]> coords) {
        int maxScore = -1;
        ArrayList<int[]> empty = new ArrayList<>();
        for (int iter = 0; iter < coords.size(); iter++) {
            int[] coord = coords.get(iter);
            int row = coord[0];
            int col = coord[1];
            int score = 4;
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dr[i];
                int nextCol = col + dc[i];
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || realMap[nextRow][nextCol] != -1) {
                    score -= 1;
                }
            }
            if (score > maxScore) {
                maxScore = score;
                empty.clear();
                empty.add(coord);
            } else if (score == maxScore) {
                empty.add(coord);
            }
        }
        return empty;
    }

    private static List<int[]> findMostPrefer(int nowStudent) {
        List<int[]> mostPrefer = new ArrayList<>();
        int max = 0;
        int[][] tempMap = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                for (int k = 0; k < 4; k++) {
                    if (realMap[row][col] == preferMap[nowStudent][k]) {
                        for (int index = 0; index < 4; index++) {
                            int nextRow = row + dr[index];
                            int nextCol = col + dc[index];
                            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                                continue;
                            }
                            tempMap[nextRow][nextCol]++;
                        }
                    }
                }
            }
        }
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (realMap[row][col] != -1) {
                    continue;
                }
                int prefer = tempMap[row][col];
                if (prefer > max) {
                    max = prefer;
                    mostPrefer.clear();
                    mostPrefer.add(new int[]{row, col});
                } else if (prefer == max) {
                    mostPrefer.add(new int[]{row, col});
                }
            }
        }

        return mostPrefer;
    }
}

















