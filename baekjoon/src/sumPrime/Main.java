package sumPrime;

import java.io.*;
import java.util.*;

public class Main {
    static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        prime = new boolean[N + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = true;
        for (int i = 2; i * i <= N; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    prime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for(int i = 2; i <= N; i++){
            if(prime[i]) {
                primes.add(i);
            }
        }

        int count = 0;
        long currentSum = 0;  // 합이 int 범위를 초과할 수 있으므로 long으로
        int left = 0;

        for(int right = 0; right < primes.size(); right++){
            currentSum += primes.get(right);

            // 합이 N을 초과하면 left를 옮겨서 합을 줄임
            while(currentSum > N && left <= right){
                currentSum -= primes.get(left);
                left++;
            }

            // 조건에 맞으면 카운트 증가
            if(currentSum == N){
                count++;
            }
        }

        System.out.println(count);


    }
}