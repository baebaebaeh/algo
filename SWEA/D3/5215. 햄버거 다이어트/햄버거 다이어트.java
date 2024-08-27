
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
	static BufferedReader br;
	static BufferedWriter bw;
	static int N, L, ans;
	static int[] tast;
	static int[] calorie;

	public static void main(String args[]) throws IOException {
		//System.setIn(new FileInputStream("swea/input5215.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int iter = 1; iter <= T; iter++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			ans = 0;
			tast = new int[N];
			calorie = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				tast[i] = Integer.parseInt(st.nextToken());
				calorie[i] = Integer.parseInt(st.nextToken());
			}
			subset();
			bw.write("#" + iter + " " + ans + "\n");
		}
		bw.flush();
	}

	private static void subset() {
		int sumCal = 0;
		int sumTast = 0;
		for (int i = 0; i < (1 << N); i++) {
			sumCal = 0;
			sumTast = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) != 0) {
					sumCal += calorie[j];
					sumTast += tast[j];
				}
			}
			if (sumCal <= L && sumTast > ans) {
				ans = sumTast;
			}
		}
	}

}