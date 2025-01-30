package net.acmicpc.num23309;

import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/num23309.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] uniqNums = new int[1000001];
        int maxNum = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            uniqNums[Integer.parseInt(st.nextToken())] = maxNum++; // maxNum 넣고 올림
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int createdAt;
            switch (st.nextToken()) {
                case "BN":
                    createdAt = uniqNums[Integer.parseInt(st.nextToken())] + 1;
                    if (createdAt > 1000000) createdAt = 1;
                    uniqNums[Integer.parseInt(st.nextToken())] = createdAt;
                    maxNum++;
                    break;
                case "BP":
                    createdAt = uniqNums[Integer.parseInt(st.nextToken())] - 1;
                    if (createdAt < 1) createdAt = 1;
                    uniqNums[Integer.parseInt(st.nextToken())] = createdAt;
                    break;
            }
        }

    }
}
