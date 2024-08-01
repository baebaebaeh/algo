import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("data/input8958.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			char[] arr = st.nextToken().toCharArray();
			int j = 0;
			int ans = 0;
			int contin = 1;
			while (j < arr.length) {
				if (arr[j] == 'O') {
					ans += contin++;
					j++;
					continue;
				}
				contin = 1;
				j++;
			}
			bw.write(ans + "\n");
		}
		bw.flush();
	}
}