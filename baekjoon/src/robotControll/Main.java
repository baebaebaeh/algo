package robotControll;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] left, right;
    static int[][] value, valueSum;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/robotControll.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        value = new int[N][M];
        valueSum = new int[N][M];
        left = new int[M];
        right = new int[M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                value[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        init();
        for (int row = 1; row < N; row++) {
            dp(row);
        }
//        for (int row = 0; row < N; row++) {
//            System.out.println(Arrays.toString(valueSum[row]));
//        }
        int ans = valueSum[N - 1][M - 1];
//        int ans = findAns();
        System.out.println(ans);
    }


    private static void init() {
        int sum = 0;
        for (int col = 0; col < M; col++) {
            int row = 0;
            sum += value[row][col];
            valueSum[row][col] = sum;
        }
    }

    private static void dp(int row) {
        int sumRight = valueSum[row - 1][0] + value[row][0];
        right[0] = sumRight;
        int sumLeft = valueSum[row - 1][M - 1] + value[row][M - 1];
        left[M - 1] = sumLeft;
        right(row, 1);
        left(row, M - 2);

        for (int col = 0; col < M; col++) {
            valueSum[row][col] = Math.max(right[col], left[col]);
        }
    }
    private static void left(int row, int col) {
        if (col < 0) return;
        left[col] = Math.max(valueSum[row - 1][col], left[col + 1]) + value[row][col];
        left(row, col - 1);
    }
    private static void right(int row, int col) {
        if (col >= M) return;
        right[col] = Math.max(valueSum[row - 1][col], right[col - 1]) + value[row][col];
        right(row, col + 1);
    }

//    private static int findAns() {
//        int max = Integer.MIN_VALUE;
//        for (int row = 0; row < N; row++) {
//            for (int col = 0; col < M; col++) {
//                if (valueSum[row][col] > max) {
//                    max = valueSum[row][col];
//                }
//            }
//        }
//        return max;
//    }

}
