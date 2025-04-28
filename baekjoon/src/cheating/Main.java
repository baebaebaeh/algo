package cheating;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] dp;
    static int[] prevStates;
    static int[] curStates;
    static int prevSize, curSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            System.out.println(solve());
        }
    }

    static int solve() {
        // 각 행의 가능한 모든 상태를 계산
        int maxState = 1 << M;
        prevStates = new int[maxState];
        curStates = new int[maxState];
        dp = new int[N][maxState];

        // 첫 번째 행에 대한 가능한 상태 계산
        prevSize = 0;
        for (int state = 0; state < maxState; state++) {
            if (isValidState(0, state)) {
                prevStates[prevSize++] = state;
                dp[0][state] = countBits(state);
            }
        }

        // 다음 행부터 DP 수행
        for (int row = 1; row < N; row++) {
            curSize = 0;
            for (int i = 0; i < prevSize; i++) {
                int prevState = prevStates[i];

                for (int state = 0; state < maxState; state++) {
                    if (isValidState(row, state) && !hasAdjacent(prevState, state)) {
                        dp[row][state] = Math.max(dp[row][state], dp[row-1][prevState] + countBits(state));

                        if (curSize == 0 || curStates[curSize-1] != state) {
                            curStates[curSize++] = state;
                        }
                    }
                }
            }

            // prevStates와 curStates 교체
            int[] temp = prevStates;
            prevStates = curStates;
            curStates = temp;
            prevSize = curSize;
        }

        // 마지막 행에서 최대값 찾기
        int result = 0;
        for (int i = 0; i < prevSize; i++) {
            result = Math.max(result, dp[N-1][prevStates[i]]);
        }

        return result;
    }

    // 해당 상태가 현재 행에서 유효한지 검사
    static boolean isValidState(int row, int state) {
        for (int col = 0; col < M; col++) {
            if ((state & (1 << col)) != 0) {
                // 학생이 앉을 수 있는 자리인지 확인
                if (map[row][col] == 'x') return false;

                // 왼쪽 대각선에 다른 학생이 있는지 확인
                if (col > 0 && (state & (1 << (col-1))) != 0) return false;

                // 오른쪽 대각선에 다른 학생이 있는지 확인
                if (col < M-1 && (state & (1 << (col+1))) != 0) return false;
            }
        }
        return true;
    }

    // 이전 행의 상태와 현재 행의 상태가 서로 맞물리지 않는지 확인
    static boolean hasAdjacent(int prevState, int curState) {
        for (int col = 0; col < M; col++) {
            if ((curState & (1 << col)) != 0) {
                // 위쪽 왼쪽/오른쪽 대각선에 다른 학생이 있는지 확인
                if (col > 0 && (prevState & (1 << (col-1))) != 0) return true;
                if (col < M-1 && (prevState & (1 << (col+1))) != 0) return true;
            }
        }
        return false;
    }

    // 상태에서 비트 1의 개수를 세는 함수 (학생 수)
    static int countBits(int state) {
        int count = 0;
        while (state > 0) {
            count += state & 1;
            state >>= 1;
        }
        return count;
    }
}