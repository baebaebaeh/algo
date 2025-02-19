package com.ssafy.minimizeHospitalDistance;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, hospitalCnt, peopleCnt;
    static int min = Integer.MAX_VALUE;
    static List<int[]> hospitalLocations = new ArrayList<>(),
            peopleLocations= new ArrayList<>();
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/minimizeHospitalDistance.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    peopleLocations.add(new int[]{row, col});
                } else if (x == 2) {
                    hospitalLocations.add(new int[]{row, col});
                }
            }
        }
        hospitalCnt = hospitalLocations.size();
        peopleCnt = peopleLocations.size();

        combination(new ArrayList<>(), 0);
        System.out.println(min);
    }

    private static void combination(List<Integer> temp, int start) {
        if (temp.size() == m) {
            int[] minArr = new int[peopleCnt];
            Arrays.fill(minArr, Integer.MAX_VALUE);
            for (int i = 0; i < m; i++) {
                int hospitalIdx = temp.get(i);
                for (int j = 0; j < peopleCnt; j++) {
                    int peopleIdx = j;
                    int[] hospitalLocation = hospitalLocations.get(hospitalIdx);
                    int[] peopleLocation = peopleLocations.get(peopleIdx);

                    int distance = Math.abs(hospitalLocation[0] - peopleLocation[0])
                            + Math.abs(hospitalLocation[1] - peopleLocation[1]);

                    minArr[j] = Math.min(minArr[j], distance);
                }
            }
            int sum = 0;
            for (int j = 0; j < peopleCnt; j++) {
                sum += minArr[j];
            }
            if (sum < min) {
                min = sum;
            }
            return;
        }
        for (int i = start; i < hospitalCnt; i++) {
            temp.add(i);
            combination(temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}