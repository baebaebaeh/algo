package com.swea.day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class swea1216 {
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("swea/input1216.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int iter = 1; iter < 11; iter++) {
			br.readLine();
			int maxGap = 0;
			char[][] map = new char[100][];
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				map[i] = st.nextToken().toCharArray();
			}

			for (int i = 0; i < 100; i++) {
				char prev = 'X';
				for (int j = 0; j < 100; j++) {
					int gap = 0;
					while (j - gap >= 0 && j + gap < 100 && map[i][j - gap] == map[i][j + gap]) {
						gap += 1;
					}
					gap = (gap-1) * 2 + 1;
					if (maxGap < gap) maxGap = gap;
					gap = 0;
					while (prev == map[i][j] && j - 1 - gap >= 0 && j + gap < 100
							&& map[i][j - 1 - gap] == map[i][j + gap]) {
						gap += 1;
					}
					prev = map[i][j];
					gap = (gap-1) * 2 + 2;
					if (maxGap < gap) maxGap = gap;
				}
			}

			for (int j = 0; j < 100; j++) {
				char prev = 'X';
				for (int i = 0; i < 100; i++) {
					int gap = 0;
					while (i - gap >= 0 && i + gap < 100 && map[i - gap][j] == map[i + gap][j]) {
						gap += 1;
					}
					gap = (gap-1) * 2 + 1;
					if (maxGap < gap) maxGap = gap;
					gap = 0;
					while (prev == map[i][j] && i - 1 - gap >= 0 && i + gap < 100
							&& map[i - 1 - gap][j] == map[i + gap][j]) {
						gap += 1;
					}
					prev = map[i][j];
					gap = (gap-1) * 2 + 2;
					if (maxGap < gap) maxGap = gap;
				}
			}
			bw.write(maxGap + "\n");
		}
		bw.flush();
	}
}