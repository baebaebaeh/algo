package com.ssafy.octagonalChair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] chair = new int[6];
    static boolean[] visited;

    // pointer의 7이 12시를 가리킴.
    // pointer의 0이 11시를 가리킴
    static int[] pointer = new int[6];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/octagonalChair.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 6; i++) {
            pointer[i] = 7;
        }

        StringTokenizer st;
        for (int i = 1; i < 5; i++) {
            chair[i] = Integer.parseInt(br.readLine(), 2);
        }

        int count = Integer.parseInt(br.readLine());

        for (int iter = 0; iter < count; iter++) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            boolean isClockwise = Integer.parseInt(st.nextToken()) == 1;
            visited = new boolean[6];
            rotateChair(target, isClockwise);
        }

        int ans = calculation();
        System.out.println(ans);
    }
    // target은 의자 번호
    // isClockwise는 시계방향인지 아닌지
    private static void rotateChair(int target, boolean isClockwise) {
        // 의자번호를 벗어나거나 방문했었으면 return
        if (visited[target] || target == 0 || target == 5) {
            return;
        }
        // 방문시킴
        visited[target] = true;

        // 현재 테이블의 왼쪽 오른쪽 포인트 설정
        int rightPoint = (pointer[target] + 6) % 8;
        int leftPoint = (pointer[target] + 2) % 8;

        // 왼쪽테이블의 오른쪽 포인트
        // 오른쪽테이블의 왼쪽 포인트 설정
        int leftTableRightPoint = (pointer[target - 1] + 6) % 8;
        int rightTableLeftPoint = (pointer[target + 1] + 2) % 8;

        // 왼쪽테이블의 오른쪽 포인트에 해당되는 값이
        // 현재 테이블의 왼쪽 포인트에 해당되는 값이랑 다를때
        // 왼쪽 테이블 돌려 현재의 반대방향으로
        if (((chair[target - 1] & (1 << leftTableRightPoint)) != 0) ^ ((chair[target] & (1 << leftPoint)) != 0)) {
            rotateChair(target - 1, !isClockwise);
        }

        // 위와 반대
        if (((chair[target] & (1 << rightPoint)) != 0) ^ ((chair[target + 1] & (1 << rightTableLeftPoint)) != 0)) {
            rotateChair(target + 1, !isClockwise);
        }

        // 포인터 시계방향이면 1더하기
        // 포인터 반시계방향이면 7더하기
        if (isClockwise) {
            pointer[target] += 1;
        } else {
            pointer[target] += 7;
        }
    }

    private static int calculation() {
        int ans = 0;
        for (int target = 1; target <= 4; target++) {
            int point = pointer[target] % 8;
            if ((chair[target] & (1 << point)) != 0) {
                int sum = 1;
                for (int i = 0; i < target - 1; i++) {
                    sum *= 2;
                }
                ans += sum;
            }
        }
        return ans;
    }
}