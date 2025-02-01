package com.ssafy.BinaryRepresentation;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/BinaryRepresentation.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int nowTestCase = 1; nowTestCase <= testCase; nowTestCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int a = (1 << N) - 1;
            if ((M & a) == a) {
                bw.write("#" + nowTestCase + " ON" + "\n");
            } else {
                bw.write("#" + nowTestCase + " OFF" + "\n");
            }

        }
        bw.flush();
    }
}
