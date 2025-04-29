package partialSum;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 로컬 테스트를 위한 코드, 제출시에는 주석 처리 필요
         System.setIn(new FileInputStream("data/partialSum.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = 1;
        int sum = arr[1];
        int minLength = Integer.MAX_VALUE;

        // 투 포인터 알고리즘 적용
        while (right <= n) {
            if (sum >= s) {
                // 현재 부분합이 S 이상이면 길이 갱신
                minLength = Math.min(minLength, right - left + 1);
                // 왼쪽 포인터를 이동시켜 더 짧은 길이 탐색
                sum -= arr[left];
                left++;
            } else {
                // 현재 부분합이 S보다 작으면 오른쪽 포인터 이동
                right++;
                if (right <= n) {
                    sum += arr[right];
                }
            }
        }

        // 조건을 만족하는 부분수열이 없는 경우
        if (minLength == Integer.MAX_VALUE) {
            bw.write("0");
        } else {
            bw.write(String.valueOf(minLength));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}