package com.ssafy.samPizzaSchool;

import java.io.*;
import java.util.*;

class Flour implements Comparable<Flour> {
    int x;
    int y;
    int value;
    Flour(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
    @Override
    public int compareTo(Flour flour) {
        if(this.x != flour.x) {
            return this.x - flour.x;
        }
        return this.y - flour.y;
    }
}

public class Main {
    static int n, k, maxRoll;
    static PriorityQueue<Flour> pq;
    static int[] rollArr, foldArr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/samPizzaSchool.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        maxRoll = arrInit();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(new Flour(i, 0, Integer.parseInt(st.nextToken())));
        }

        addFlour();
        roll();

        for (int i = 0; i < n; i++) {
            Flour flour = pq.poll();
            System.out.println(flour.x + " " + flour.y + " " + flour.value);
        }

    }

    private static int arrInit() {
        pq = new PriorityQueue<>();
        rollArr = new int[7];
        //[1, 2, 4, 8, 16, 32, 64]
        int rollNum = 1;
        int maxRoll = 0;
        for (int i = 0; i < 7; i++) {
            rollArr[i] = rollNum;
            rollNum *= 2;
            if (rollNum <= n) {
                maxRoll++;
            }
        }
        foldArr = new int[2];
        int tmp = n / 2;
        foldArr[0] = tmp;
        foldArr[1] = tmp + (tmp / 2);
        return maxRoll;
    }

    private static void addFlour() {
        ArrayDeque<Flour> q = new ArrayDeque<>();
        PriorityQueue<Flour> pq2 = new PriorityQueue<>();
        int min = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            Flour flour = pq.poll();
            if (flour.value == min) {
                min = flour.value;
                q.add(flour);
            } else if (flour.value < min) {
                min = flour.value;
                q = new ArrayDeque<>();
                q.add(flour);
            }
            pq2.add(flour);
        }
        while(!q.isEmpty()) {
            Flour flour = q.poll();
            flour.value += 1;
        }
        pq = pq2;
    }

    private static void roll() {
        ArrayDeque<Flour> stack = new ArrayDeque<>();
        for (int i = 0; i < maxRoll; i++) {
            while(!pq.isEmpty() && pq.peek().x < rollArr[i]) {
                Flour flour = pq.poll();
                int x = flour.x + 1;
                int y = flour.y + 1;
                int rollX = rollArr[i];
                int rollY = 1;
                int newX = rollX + (y - rollY);
                int newY = rollY + (rollX - x);
                flour.x = newX;
                flour.y = newY;
                stack.add(flour);
            }
            while(!stack.isEmpty()) {
                Flour flour = stack.poll();
                pq.add(flour);
            }
        }
    }
}
