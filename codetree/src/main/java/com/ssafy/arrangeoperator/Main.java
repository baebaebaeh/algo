package com.ssafy.arrangeoperator;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n,
            min = Integer.MAX_VALUE,
            max = Integer.MIN_VALUE,
            plus, minus, multi;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/arrangeoperator.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /*
        3^10 ~= 60000
        브루트 포스 충분히 가능하다고 생각
         */

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int iter = 0; iter < n; iter++) {
            arr[iter] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        plus = Integer.parseInt(st.nextToken());
        minus = Integer.parseInt(st.nextToken());
        multi = Integer.parseInt(st.nextToken());

        dfs(0, 0, 0, arr[0]);

        bw.write(min + " " + max);
        bw.flush();
    }

    private static void dfs(int nplus, int nminus, int nmulti, int now) {
        int step = nplus + nminus + nmulti;
        if (step == n - 1) {
            if (now < min) min = now;
            if (now > max) max = now;
            return;
        }

        if (nplus < plus) {
            int next = now + arr[step + 1];
            dfs(nplus + 1, nminus, nmulti, next);
        };
        if (nminus < minus) {
            int next = now - arr[step + 1];
            dfs(nplus, nminus + 1, nmulti, next);
        };
        if (nmulti < multi) {
            int next = now * arr[step + 1];
            dfs(nplus, nminus, nmulti + 1, next);
        };
    }
}