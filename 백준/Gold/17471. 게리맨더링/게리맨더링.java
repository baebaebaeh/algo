
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Node {
	int[] child;
	int num;
	int color = 0; // 0=red 1=blue
}

public class Main {
	static int N, R, red, blue, ans;
	static Node[] nodes;
	static int[] sel;
	static boolean[] visited;
	static boolean D;

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("data/input17471.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nodes = new Node[N + 1];
		visited = new boolean[N + 1];
		ans = Integer.MAX_VALUE;

		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node();
		}
		for (int i = 1; i <= N; i++) {
			nodes[i].num = sc.nextInt();
		}
		for (int i = 1; i <= N; i++) {
			int tmp = sc.nextInt();
			int[] arr = new int[tmp];
			for (int j = 0; j < tmp; j++) {
				arr[j] = sc.nextInt();
			}
			nodes[i].child = arr;
		}

		for (int i = 1; i <= N / 2; i++) {
			sel = new int[i];
			R = i;
			comb(1, 0);
		}
		if (ans == Integer.MAX_VALUE) {
			ans = -1;
		}
		System.out.println(ans);
	}

	static void comb(int start, int startIdx) {
		if (startIdx == R) {
			for (int i = 0; i < R; i++) {
				nodes[sel[i]].color = 1;
			}

			for (int i = 1; i <= N; i++) {
				if (nodes[i].color == 1) {
					bfs(i, 1);
					break;
				}
			}
			for (int i = 1; i <= N; i++) {
				if (nodes[i].color == 0) {
					bfs(i, 0);
					break;
				}
			}

			D = true;
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					D = false;
				}
			}
			blue = 0;
			red = 0;
			if (D) {
				for (int i = 1; i <= N; i++) {
					if (nodes[i].color == 1) {
						blue += nodes[i].num;
					}
					if (nodes[i].color == 0) {
						red += nodes[i].num;
					}
				}
				int tmp = Math.abs(blue - red);
				if (ans > tmp) {
					ans = tmp;
				}
			}
			Arrays.fill(visited, false);

			for (int i = 0; i < R; i++) {
				nodes[sel[i]].color = 0;
			}
			return;
		}
		for (int i = start; i <= N + 1 - R + startIdx; i++) {
			sel[startIdx] = i;
			comb(i + 1, startIdx + 1);
		}
	}

	private static void bfs(int idx, int color) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(nodes[idx]);
		visited[idx] = true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int i = 0; i < node.child.length; i++) {
				int nextIdx = node.child[i];
				Node nextNode = nodes[nextIdx];
				if (!visited[nextIdx] && nextNode.color == color) {
					visited[nextIdx] = true;
					q.add(nextNode);
				}
			}
		}
	}
}
