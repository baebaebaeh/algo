package airport;

import java.io.*;
import java.util.*;

public class segment {
    static class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[n * 4];
            build(arr, 1, 0, n - 1);
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int mid = (start + end) / 2;
            build(arr, node * 2, start, mid);
            build(arr, node * 2 + 1, mid + 1, end);
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        private int query(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }

        private int query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return 0;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            int leftSum = query(node * 2, start, mid, left, right);
            int rightSum = query(node * 2 + 1, mid + 1, end, left, right);
            return Math.max(leftSum, rightSum);
        }

        private void update(int idx, int value) {
            update(1, 0, n - 1, idx, value);
        }

        private void update(int node, int start, int end, int idx, int value) {
            if (start == end) {
                tree[node] = value;
                return;
            }
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update (node * 2, start, mid, idx, value);
            } else {
                update (node * 2 + 1, mid + 1, end, idx, value);
            }
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    static int G, P, N;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/airport.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        int[] arr = new int[G + 1];
        for (int iter = 0; iter <= G; iter++) {
            arr[iter] = iter;
        }
        SegmentTree st = new SegmentTree(arr);

        for (int iter = 0; iter < G; iter++) {
            int gi = Integer.parseInt(br.readLine());
            int a = st.query(1, gi);
            if (a == 0) break;
            st.update(a, 0);
            N++;
        }
        System.out.println(N);
    }
}