package com.ssafy.ride;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
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
            // 순서대로 넣을사람의 index를 뽑는다.
            int nowStudent = order[i];
            // n * n * 4 * 4
            // 모든 map의 사방탐색의 사방탐색
            // 해당 학생이 좋아하는 수가 높은 좌표들을 추출
            coords = findMostPrefer(nowStudent);
            // 위 좌표들을 사방탐색해 비어있는 좌표가 많은 좌표들을 추출
            coords = findEmpty(coords);
            // 애초에 행이 낮은 순서, 열이 낮은 순서로 계산했기때문에 0번째가 3번 4번조건에 맞다.
            int[] coord = coords.get(0);
            realMap[coord[0]][coord[1]] = nowStudent;

        }
        // 계산
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

















