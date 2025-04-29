package theSumOfTwoNumbers;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("data/theSumOfTwoNumbers.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] intArr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            intArr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(intArr);

        int left = 0;
        int right = n - 1;
        int ans = 0;

        while (true) {
            int sum = intArr[left] + intArr[right];
            if (left == right) break;
            if (sum < x) {
                left++;
                continue;
            }
            if (sum > x) {
                right--;
                continue;
            }
            ans++;
            left++;
        }
        bw.write(ans + "");
        bw.flush();
    }
}
