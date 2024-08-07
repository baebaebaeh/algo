import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	public static void main(String args[]) throws IOException {
		//System.setIn(new FileInputStream("swea/input1984.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int iter = 1; iter <= T; iter++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int max = 0;
			int min = 10000;
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				int now = Integer.parseInt(st.nextToken());
				sum += now;
				if (now > max) max = now;
				if (now < min) min = now;
			}
			sum = sum - (max + min);
			int ans = sum / 8;
			if (sum % 8 >= 4) ans += 1;
			bw.write("#" + iter + " " + ans + "\n");
		}
		bw.flush();
//		bw.write("#" + iter + " " + ans + "\n");
//		bw.flush();
	}
}