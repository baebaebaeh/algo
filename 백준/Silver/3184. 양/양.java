import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
	//// 실험할 코드 추가
	// long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
	// long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
	// System.out.println("시간차이(m) : "+secDiffTime);
	static int sheep;
	static int wolf;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int d = 0;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("data/input3184.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sheep = 0;
		wolf = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			String string = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = string.charAt(c);
			}
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				bfs(r, c);
			}
		}
		bw.write(sheep + " " + wolf);
		bw.flush();
	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		;
		int tempSheep = 0;
		int tempWolf = 0;
		int[] arr = { r, c };
		boolean out = false;
		queue.add(arr);
		while (!queue.isEmpty()) {
			arr = queue.poll();
			if (map[arr[0]][arr[1]] == '#')
				continue;
			for (d = 0; d < 4; d++) {
				if (arr[0] + dr[d] < 0 || arr[0] + dr[d] >= map.length || arr[1] + dc[d] < 0
						|| arr[1] + dc[d] >= map[0].length) {
					out = true;
					continue;
				}
				if (map[arr[0] + dr[d]][arr[1] + dc[d]] != '#') {
					int[] tempArr = { arr[0], arr[1] };
					tempArr[0] += dr[d];
					tempArr[1] += dc[d];
					queue.add(tempArr);
				}
			}
			if (map[arr[0]][arr[1]] == 'v')
				tempWolf++;
			if (map[arr[0]][arr[1]] == 'o')
				tempSheep++;
			map[arr[0]][arr[1]] = '#';
		}
		if (out == true)
			return;
		if (out == false) {
			if (tempWolf >= tempSheep)
				tempSheep = 0;
			if (tempSheep > tempWolf)
				tempWolf = 0;
			sheep += tempSheep;
			wolf += tempWolf;
		}
	}
}