
import java.util.*;
import java.io.*;

public class Main {
	static int mapR, mapC, range, ans, tmpAns;
	static int[][] map;
	static int[][] tmpMap;
	static int[][] oneStep = new int[3][2]; // 궁수가 한스텝에 쏠 [r, c] * 3명
	static boolean[][] visited;
	static int[] sel = new int[3];
	static int[] dr = { 0, -1, 0 };
	static int[] dc = { -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("data/input17135.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		mapR = Integer.parseInt(st.nextToken());
		mapC = Integer.parseInt(st.nextToken());
		range = Integer.parseInt(st.nextToken());
		map = new int[mapR][mapC];
		tmpMap = new int[mapR][mapC];
		visited = new boolean[mapR][mapC];

		ans = 0;

		for (int i = 0; i < mapR; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mapC; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0, 0);
		System.out.println(ans);
	}

	static void comb(int start, int startIdx) {
		if (startIdx == 3) {
			// map복사
			for (int i = 0; i < mapR; i++) {
				for (int j = 0; j < mapC; j++) {
					tmpMap[i][j] = map[i][j];
				}
			}
			// [[첫번재 궁수가 죽인 좌표][두번째][세번째]]
			// 를 -1,-1로 바꿔줌 이건 쏠곳을 못찾았다는 뜻
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 2; j++) {
					oneStep[i][j] = -1;
				}
			}
			tmpAns = 0;
			// 한칸씩 올라가면서 체크
			for (int i = mapR - 1; i >= 0; i--) {
				for (int j = 0; j < 3; j++) {
					// 각각의 궁수 위치에서 dfs돌리고
					dfs(i, sel[j], j);
					// tmpMap에서 oneStep의 좌표를 넣어보고
					// 1이면 0으로 바꾸고 ans++

					// visited 다시 false로 바꿔줌
					for (int j2 = 0; j2 < mapR; j2++) {
						Arrays.fill(visited[j2], false);
					}
				}
//				System.out.println(Arrays.deepToString(oneStep));
				for (int k = 0; k < 3; k++) {
					if (oneStep[k][0] != -1 && tmpMap[oneStep[k][0]][oneStep[k][1]] == 1) {
						tmpAns++;
						tmpMap[oneStep[k][0]][oneStep[k][1]] = 0;
					}
				}
//				for (int il = 0; il < mapR; il++) {
//					System.out.println(Arrays.toString(tmpMap[il]));
//				}
//				System.out.println();
				if (tmpAns > ans)
					ans = tmpAns;
			}
//			System.out.println(tmpAns);
//			for (int i = 0; i < mapR; i++) {
//				System.out.println(Arrays.toString(tmpMap[i]));
//			}
//			System.out.println();
			return;
		}
		for (int i = start; i <= mapC - 3 + startIdx; i++) {
			sel[startIdx] = i;
			comb(i + 1, startIdx + 1);
		}
	}

	static void dfs(int row, int col, int order) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { row, col, 1 });
		visited[row][col] = true;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0];
			int c = now[1];
			int step = now[2];
			if (step > range)
				return;

			if (tmpMap[r][c] == 1) {
//				oneStep[order] = new int[] { r, c };
				oneStep[order] = now;
				return;
			}

			for (int d = 0; d < 3; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr >= 0 && nr < mapR && nc >= 0 && nc < mapC && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc, step + 1 });
				}
			}
		}
	}
}