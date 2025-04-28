package ACMcreaft;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int idx;
    int cost;

    @Override
    public int compareTo(Node o) {
        return o.cost - this.cost;
    }

    public Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}

public class DijkstraV1 {
    static List<List<Node>> graph;
    static List<List<Node>> rgraph;
    static int T, N, K, W;
    static int[] cost, dist;
    public static void main (String args[]) throws Exception {
        System.setIn(new FileInputStream("data/ACMcreaft.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for (int iter1 = 0; iter1 < T; iter1++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cost = new int[N + 1];
            dist = new int[N + 1];
            Arrays.fill(dist, Integer.MIN_VALUE);

            graph = new ArrayList<>();
            rgraph = new ArrayList<>();
            for (int iter2 = 0; iter2 <= N; iter2++) {
                graph.add(new ArrayList<>());
                rgraph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int iter2 = 1; iter2 <= N; iter2++) {
                cost[iter2] = Integer.parseInt(st.nextToken());
            }

            for (int iter2 = 0; iter2 < K; iter2++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                graph.get(X).add(new Node(Y, cost[Y]));
                rgraph.get(Y).add(new Node(X, cost[X]));
            }

            W = Integer.parseInt(br.readLine());


            ArrayList<Integer> rootIdx = new ArrayList<>();
            for (int i = 0; i < rgraph.size(); i++) {
                if (rgraph.get(i).size() == 0) {
                    rootIdx.add(i);
                }
            }

            dijkstra(dist, rootIdx);
            bw.write(dist[W] + "\n");
        }
        bw.flush();
    }

    private static void dijkstra(int[] dist, ArrayList<Integer> starts) {
        ArrayDeque<Node> d = new ArrayDeque<>();
        for (int i = 0; i < starts.size(); i++) {
            int start = starts.get(i);
            d.add(new Node(start, cost[start]));
            dist[start] = cost[start];
        }
        while(!d.isEmpty()) {
            Node node = d.poll();
            int nowIdx = node.idx;
            int nowCost = node.cost;
            for (Node node2 : graph.get(nowIdx)) {
                int nextIdx = node2.idx;
                int nextCost = node2.cost + nowCost;
                if (dist[nextIdx] < nextCost) {
                    dist[nextIdx] = nextCost;
                    d.add(new Node(nextIdx, nextCost));
                }
            }
        }
    }
}