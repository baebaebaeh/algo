package networkConnection;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int T, N, sum = 0;
    static int[] parent, dist;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/networkConnection.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int iter = 0; iter < T; iter++) {
            N = Integer.parseInt(br.readLine());

            parent = new int[N + 1];
            dist = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }
            out: while (true) {
                st = new StringTokenizer(br.readLine());
                String commend = st.nextToken();
                int a1;
                int a2;
                switch (commend) {
                    case "E":
                        a1 = Integer.parseInt(st.nextToken());
                        find(a1);
                        int ans = dist[a1];
                        bw.write(ans + "\n");
                        break;
                    case "I":
                        a1 = Integer.parseInt(st.nextToken());
                        a2 = Integer.parseInt(st.nextToken());
                        union(a1, a2);
                        break;
                    case "O":
                        break out;
                }
            }
        }
        bw.flush();
    }

    private static boolean union(int x, int y) {
        dist[x] = Math.abs(x - y) % 1000;
        if (x == y) return false;
        parent[x] = y;
        return true;
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        int p = parent[a];
        parent[a] = find(p);
        dist[a] += dist[p];
        return parent[a];
    }
}