package com.ssafy.autonomousDriving;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m, d, ans;
    static int[] nowCoord = new int[2];
    // 상좌하우
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static boolean[][] visited;
    static boolean[][] isPavement;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/autonomousDriving.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n][m];
        isPavement = new boolean[n][m];

        st = new StringTokenizer(br.readLine());

        nowCoord[0] = Integer.parseInt(st.nextToken());
        nowCoord[1] = Integer.parseInt(st.nextToken());

        d = Integer.parseInt(st.nextToken());

        // 입력 오류 초기설정
        if (d == 1) {
            d = 3;
        } else if (d == 3) {
            d = 1;
        }

        // 도로 받기
        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < m; col++) {
                int tmp = Integer.parseInt(st.nextToken());
                isPavement[row][col] = tmp == 1;
            }
        }


        // 입력값 받기 끝


        // 실제 로직 시작
        myInit();
        while (true) {
            boolean canMove = fourWaySearch();
            if (!canMove) {
                boolean canBackward = backward();
                if (!canBackward) {
                    break;
                }
            } else {
                forward();
            }
        }
        System.out.println(ans);
    }

    private static void myInit() {
        // 지나간 면적 +
        ans++;
        // 방문했다.
        visited[nowCoord[0]][nowCoord[1]] = true;
    }

    private static void forward() {
        // 현재 방향으로 한칸움직이고
        nowCoord[0] += dr[d];
        nowCoord[1] += dc[d];
        // 방문했다.
        visited[nowCoord[0]][nowCoord[1]] = true;
        // 지나간 면적 +
        ans++;
    }

    private static boolean backward() {
        // 뒤로 돌았!
        int tmpD = (d + 2) % 4;
        int nextRow = nowCoord[0] + dr[tmpD];
        int nextCol = nowCoord[1] + dc[tmpD];

        // 뒤가 인도인지 확인
        if (isPavement[nextRow][nextCol]) {
            return false;
        }

        // 인도가 아니면 뒤로 이동
        nowCoord[0] = nextRow;
        nowCoord[1] = nextCol;
        return true;
    }

    private static boolean fourWaySearch() {
        boolean canMove = false;
        // 네방향 확인
        for (int iter = 1; iter <= 4; iter++) {
            // 현재 바라보는 방향에서 정면 왼쪽 뒤 오른쪽 순으로 확인
            int tmpD = (d + iter) % 4;
            int nextRow = nowCoord[0] + dr[tmpD];
            int nextCol = nowCoord[1] + dc[tmpD];
            // 인도거나 방문 했으면 넘겨
            if (isPavement[nextRow][nextCol] || visited[nextRow][nextCol]) {
                continue;
            }
            // 위 통과했으면 방향 설정
            d = tmpD;
            // 움직일 수 있다 반환
            canMove = true;
            return canMove;
        }
        // 움직일 수 없다 반환
        return canMove;
    }
}