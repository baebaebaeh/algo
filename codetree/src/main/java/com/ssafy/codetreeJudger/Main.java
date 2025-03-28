package com.ssafy.cubeRounding;

import java.io.*;
import java.util.StringTokenizer;

class Cube {
    int up = 0;
    int down = 0;
    int left = 0;
    int right = 0;
    int front = 0;
    int back = 0;

    public int rollRight(int num) {
        //오른쪽으로 구르고
        int tmp = this.right;
        this.right = this.back;
        this.back = this.left;
        this.left = this.front;
        this.front = tmp;
        // 칸의 숫자가 0이면
        if (num == 0) {
            // 주사위 바닥값 반환
            return this.back;
        } else {
            // 0이 아니면 주사위 바닥값을 칸의 값으로 바꾸고 0반환
            this.back = num;
            return 0;
        }
    }

    public int rollDown(int num) {
        //오른쪽으로 구르고
        int tmp = this.down;
        this.down = this.back;
        this.back = this.up;
        this.up = this.front;
        this.front = tmp;
        // 칸의 숫자가 0이면
        if (num == 0) {
            // 주사위 바닥값 반환
            return this.back;
        } else {
            // 0이 아니면 주사위 바닥값을 칸의 값으로 바꾸고 0반환
            this.back = num;
            return 0;
        }
    }

    public int rollLeft(int num) {
        //오른쪽으로 구르고
        int tmp = this.left;
        this.left = this.back;
        this.back = this.right;
        this.right = this.front;
        this.front = tmp;
        // 칸의 숫자가 0이면
        if (num == 0) {
            // 주사위 바닥값 반환
            return this.back;
        } else {
            // 0이 아니면 주사위 바닥값을 칸의 값으로 바꾸고 0반환
            this.back = num;
            return 0;
        }
    }

    public int rollUp(int num) {
        //오른쪽으로 구르고
        int tmp = this.up;
        this.up = this.back;
        this.back = this.down;
        this.down = this.front;
        this.front = tmp;
        // 칸의 숫자가 0이면
        if (num == 0) {
            // 주사위 바닥값 반환
            return this.back;
        } else {
            // 0이 아니면 주사위 바닥값을 칸의 값으로 바꾸고 0반환
            this.back = num;
            return 0;
        }
    }
}


public class Main {
    static int n, m, cubeRow, cubeCol, k;
    static int[][] map;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/cubeRounding.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Cube cube = new Cube();

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cubeRow = Integer.parseInt(st.nextToken());
        cubeCol = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < m; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int iter = 0; iter < k; iter++) {
            int commend = Integer.parseInt(st.nextToken()) - 1;
            int nextCubeRow = cubeRow + dr[commend];
            int nextCubeCol = cubeCol + dc[commend];
            if (nextCubeRow < 0 || nextCubeRow >= n || nextCubeCol < 0 || nextCubeCol >= m) {
                continue;
            }
            if (commend == 0) {
                map[nextCubeRow][nextCubeCol] = cube.rollRight(map[nextCubeRow][nextCubeCol]);
            } else if (commend == 1) {
                map[nextCubeRow][nextCubeCol] = cube.rollLeft(map[nextCubeRow][nextCubeCol]);
            } else if (commend == 2) {
                map[nextCubeRow][nextCubeCol] = cube.rollUp(map[nextCubeRow][nextCubeCol]);
            } else if (commend == 3) {
                map[nextCubeRow][nextCubeCol] = cube.rollDown(map[nextCubeRow][nextCubeCol]);
            }
            cubeRow = nextCubeRow;
            cubeCol = nextCubeCol;
            System.out.println(cube.front);
        }



    }
}