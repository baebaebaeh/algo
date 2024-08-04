import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	// long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
	//// 실험할 코드 추가
	// long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
	// long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
	// System.out.println("시간차이(m) : "+secDiffTime);
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("data/input17413.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String S = br.readLine();
		int temp = 0;
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == '<') {
				if (i > 0) {
					for (int j = i - 1; j >= temp; j--) {
						bw.write(S.charAt(j));
					}
				}
				while (S.charAt(i) != '>') {
					bw.write(S.charAt(i));
					i++;
				}
				bw.write('>');
				temp = i + 1;
				continue;
			}
			if (S.charAt(i) == ' ') {
				if (i > 0) {
					for (int j = i - 1; j >= temp; j--) {
						bw.write(S.charAt(j));
					}
				}
				bw.write(' ');
				temp = i + 1;
				continue;
			}
			if (i > 0 && i == S.length() - 1) {
				for (int j = i; j >= temp; j--) {
					bw.write(S.charAt(j));
				}
			}
		}
		bw.flush();
	}
}