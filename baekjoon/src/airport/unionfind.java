package airport;

import java.io.*;
import java.util.StringTokenizer;

public class unionfind {
    static int G, P, ans;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/airport.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        G = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        parent = new int[G + 1];

        // 부모배열 초기화
        // [0, 1, 2, 3, 4] 형식임
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        for (int iter = 0; iter < P; iter++) {
            st = new StringTokenizer(br.readLine());
            int gi = Integer.parseInt(st.nextToken());

            int insert = find(gi);
            if (insert == 0) {
                break;
            }
            union(insert - 1, insert);
            ans++;
        }
        System.out.println(ans);
    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return false;
        }
        if (x <= y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
        return true;
    }

    private static int find(int gi) {
        if (parent[gi] == gi) {
            return gi;
        }
        return parent[gi] = find(parent[gi]);
    }
}