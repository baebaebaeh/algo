package com.ssafy.maxSumOfTetrisBlock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSame(Pair p) {
        return this.x == p.x && this.y == p.y;
    }
}

public class Main2 {
    public static final int DIR_NUM = 4;
    public static final int MAX_NUM = 200;

    public static int n, m;
    public static int[][] grid = new int[MAX_NUM][MAX_NUM];
    public static int maxSum;

    public static ArrayList<Pair> visitedPos = new ArrayList<>();

    public static int[] dx = new int[]{-1, 1, 0, 0};
    public static int[] dy = new int[]{0, 0, -1, 1};

    // 주어진 위치가 격자를 벗어나는지 여부를 반환합니다.
    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    // 주어진 위치로 이동할 수 있는지 여부를 확인합니다.
    public static boolean canGo(int x, int y) {
        if(!inRange(x, y))
            return false;

        Pair pos = new Pair(x, y);
        for(int i = 0; i < visitedPos.size(); i++)
            if(visitedPos.get(i).isSame(pos))
                return false;

        return true;
    }

    // 주어진 위치에 대하여 가능한 모든 모양을 탐색하며 최대 합을 반환합니다.
    public static void getMaxSum(int cnt, int sum) {
        if(cnt == 4) {
//            for (Pair visitedPo : visitedPos) {
//                System.out.println("visitedPos = " + visitedPo.x + ", " + visitedPo.y);
//            }
//            System.out.println();
            maxSum = Math.max(maxSum, sum);
            return;
        }

        // 지금까지 방문한 위치들을 탐색하면서 인접한 위치로 이동할 수 있는지를 확인합니다.
        for(int i = 0; i < visitedPos.size(); i++) {
            int x = visitedPos.get(i).x;
            int y = visitedPos.get(i).y;
            for(int dir = 0; dir < DIR_NUM; dir++) {
                int newX = x + dx[dir];
                int newY = y + dy[dir];

                if(canGo(newX, newY)) {
                    visitedPos.add(new Pair(newX, newY));
                    getMaxSum(cnt + 1, sum + grid[newX][newY]);
                    visitedPos.remove(visitedPos.size() - 1);
                }
            }
        }

        return;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("data/maxSumOfTetrisBlock.txt"));
        Scanner sc = new Scanner(System.in);

        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();

        // 격자의 각 위치에 대하여 탐색하여줍니다.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visitedPos.add(new Pair(i, j));
                getMaxSum(1, grid[i][j]);
                visitedPos.remove(visitedPos.size() - 1);
            }
        }

        System.out.print(maxSum);
    }
}
