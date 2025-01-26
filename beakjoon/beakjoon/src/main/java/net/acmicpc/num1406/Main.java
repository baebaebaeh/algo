package net.acmicpc.num1406;

import java.io.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/num1406.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        for (int testcase = 0; testcase < 3; testcase++) {
        List<String> list = new LinkedList<>();
        int nowIdx = 0;
        StringTokenizer st;

        String[] initStrArr = br.readLine().split("");
        int initStrCount = initStrArr.length;
        for (int i = 0; i < initStrCount; i++) {
            list.add(initStrArr[i]);
            nowIdx++;
        }

        int commendCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < commendCount; i++) {

            st = new StringTokenizer(br.readLine());
            String commend = st.nextToken();

            switch (commend) {
                case "L":
                    if (nowIdx > 0) {
                        nowIdx--;
                    }
                    break;
                case "D":
                    if (nowIdx < list.size()) {
                        nowIdx++;
                    }
                    break;
                case "B":
                    if (nowIdx > 0) {
                        list.remove(nowIdx - 1);
                        nowIdx--;
                    }
                    break;
                case "P":
                    String addStr = st.nextToken();
                    list.add(nowIdx, addStr);
                    nowIdx++;
                    break;
            }
            // switch
        }
        //for
        String[] ans = list.toArray(new String[0]);
        for (int i = 0; i < ans.length; i++) {
            bw.write(ans[i]);
        }
//            bw.write("\n");
        bw.flush();
    }
}
//}
