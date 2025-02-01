package com.ssafy.NewCureForInsomnia;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/NewCureForInsomnia.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
//        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int nowTestCase = 1; nowTestCase <= testCase; nowTestCase++) {
            int N = Integer.parseInt(br.readLine());
            int b = 0;
            int i = 1;
            while (true) {
                int next = N * i;
                while (next != 0) {
                    int rest = next % 10;
                    b = b | 1 << rest;
                    next = next / 10;
                }
                if (b == 1023) break;
                i++;
            }
            bw.write("#" + nowTestCase + " " + N * i + "\n");
        }
        bw.flush();
    }
}
