package com.ssafy.codetreeJudger;

import java.io.*;
import java.util.*;

class Prob implements Comparable<Prob> {
    String url;
    int start;
    int p;

    int end = Integer.MAX_VALUE;
    int jId;

    Prob (int start, int p, String url) {
        this.start = start;
        this.p = p;
        this.url = url;
    }

    @Override
    public int compareTo(Prob o) {
        return this.p - o.p;
    }
}

public class Main {
    static int Q, N;

    // url
    static HashSet<String> waitingSet;
    static PriorityQueue<Prob> waitingQueue;
    // url
    static HashSet<String> judgingSet;
    static HashMap<Integer, Prob> judgingMap;
    // url, start + 3 * gap
    static HashMap<String, Integer> historyMap;
    static ArrayDeque<Prob> history;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/codetreeJudger.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        waitingSet = new HashSet<>();
        waitingQueue = new PriorityQueue<>();

        judgingSet = new HashSet<>();
        judgingMap = new HashMap<>();

        historyMap = new HashMap<>();
        history = new ArrayDeque<>();


        Q = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        N = Integer.parseInt(st.nextToken());
        String initUrl = st.nextToken();
        Prob initProb = new Prob(0, 1, initUrl);
        waitingQueue.add(initProb);

        String url;
        int start;
        int p;

        int end;
        for (int iter = 0; iter < Q - 1; iter++) {
            st = new StringTokenizer(br.readLine());
            int commend = Integer.parseInt(st.nextToken());

            switch (commend) {
                case 200:
                    start = Integer.parseInt(st.nextToken());
                    p = Integer.parseInt(st.nextToken());
                    url = st.nextToken();

                    if (waitingSet.contains(url)) {
                        break;
                    }

                    Prob prob = new Prob(start, p, url);
                    waitingQueue.add(prob);

                    waitingSet.add(url);
                    break;
                case 300:
                    start = Integer.parseInt(st.nextToken());
                    PriorityQueue<Prob> tmpQ = new PriorityQueue<>();
                    while (!waitingQueue.isEmpty()) {
                        Prob nowProb = waitingQueue.poll();
                        nowProb.start = start;
                        url = nowProb.url;
                        if (judgingSet.contains(url)) {
                            tmpQ.add(nowProb);
                            continue;
                        }
                        String historyUrl = url.split("/")[0];

                        if (historyMap.containsKey(historyUrl)
                                && start < historyMap.get(historyUrl) ) {
                            tmpQ.add(nowProb);
                            continue;
                        }
                        waitingSet.remove(url);
                        judgingSet.add(url);
                        for (int jId = 1; jId <= N; jId++) {
                            if (judgingMap.containsKey(jId)) {
                                continue;
                            }
                            judgingMap.put(jId, nowProb);
                            break;
                        }
                        break;
                    }
                    while (!waitingQueue.isEmpty()) {
                        Prob nowProb = waitingQueue.poll();
                        tmpQ.add(nowProb);
                    }
                    waitingQueue = tmpQ;
                    break;
                case 400:
                    end = Integer.parseInt(st.nextToken());
                    int jId = Integer.parseInt(st.nextToken());

                    if (!judgingMap.containsKey(jId)) {
                        break;
                    }

                    prob = judgingMap.get(jId);
                    prob.end = end;

                    start = prob.start;
                    url = prob.url;
                    int gap = end - start;

                    judgingSet.remove(url);
                    judgingMap.remove(jId);

                    String[] splitUrl = url.split("/");
                    String historyUrl = splitUrl[0];

                    historyMap.put(historyUrl, start + (3 * gap));
                    break;
                case 500:
                    bw.write(waitingQueue.size() + "\n");
                    break;
            }
        }
        bw.flush();
    }
}