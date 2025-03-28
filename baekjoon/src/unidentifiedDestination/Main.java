package unidentifiedDestination;

import java.io.*;
import java.util.*;
class Node implements Comparable<Node> {
    int toIdx;
    int cost;
    boolean visited = false;
    public Node(int toIdx, int cost) {
        this.toIdx = toIdx;
        this.cost = cost;
    }
    @Override
    public int compareTo(Node o) {
        return cost - o.cost;
    }
}

public class Main {
    static int T, n, m, t, s, g, h;
    static List<List<Node>> graph;
    static int[] destination, costS, costG, costH;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/unidentifiedDestination.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
            }

            destination = new int[t];
            costS = new int[n + 1];
            costG = new int[n + 1];
            costH = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                costS[i] = Integer.MAX_VALUE;
                costG[i] = Integer.MAX_VALUE;
                costH[i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < t; i++) {
                destination[i] = Integer.parseInt(br.readLine());
            }
            // 입력받기 끝


            dijkstra(s, costS);
            dijkstra(g, costG);
            dijkstra(h, costH);
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int idx = 0; idx < t; idx++) {
                int de = destination[idx];
                if (costS[de] == Integer.MAX_VALUE) {
                    continue;
                }
                int a = costS[de];

                if (costS[g] == Integer.MAX_VALUE || costG[de] == Integer.MAX_VALUE) {
                    continue;
                }
                if (costS[g] + costG[de] == a) {
                    pq.add(de);
                    continue;
                }

                if (costH[de] == Integer.MAX_VALUE || costS[h] == Integer.MAX_VALUE) {
                    continue;
                }
                if (costS[h] + costH[de] == a) {
                    pq.add(de);
                    continue;
                }
            }
            while (!pq.isEmpty()) {
                int de = pq.poll();
                bw.write(de + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }



    private static void dijkstra(int start, int[] cost) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        pq.add(new int[]{start, 0}); // 어디서, 지금까지 얼마?
        cost[start] = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowIdx = now[0];
            int nowCost = now[1];
            for (Node node : graph.get(nowIdx)) {
                int nextIdx = node.toIdx;
                int nextCost = node.cost;
                int totalCost = nowCost + nextCost;
                if (cost[nextIdx] > totalCost) {
                    cost[nextIdx] = totalCost;
                    pq.add(new int[]{nextIdx, totalCost});
                }
            }
        }
    }
}

















