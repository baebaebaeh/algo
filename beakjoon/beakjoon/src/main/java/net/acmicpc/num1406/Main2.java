package net.acmicpc.num1406;

import java.io.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main2 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/num1406.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayDeque<String> front = new ArrayDeque<>();
        ArrayDeque<String> back = new ArrayDeque<>();

        String[] initStrArr = br.readLine().split("");

        for (String str : initStrArr) {
            front.add(str);
        }

        int commendCount = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < commendCount; i++) {
            st = new StringTokenizer(br.readLine());
            String commend = st.nextToken();

            switch (commend) {
                case "L":
                    if (!front.isEmpty()) {
                        back.addFirst(front.pollLast());
                    }
                    break;
                case "D":
                if (!back.isEmpty()) {
                        front.add(back.pollFirst());
                    }
                    break;
                case "B":
                    if (!front.isEmpty()) {
                        front.pollLast();
                    }
                    break;
                case "P":
                    String addStr = st.nextToken();
                    front.add(addStr);
                    break;
            }
            //switch
        }
        //for
//        while (!front.isEmpty()) {
//            bw.write(front.poll());
//        }
        int frontSize = front.size();
        for (int i = 0; i < frontSize; i++) {
            bw.write(front.poll());
        }
        int backSize = back.size();
        for (int i = 0; i < backSize; i++) {
            bw.write(back.poll());
        }
        bw.flush();
    }
}
