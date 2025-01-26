package net.acmicpc.num1327;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
class Main {

    static class PermutationState {
        List<Integer> permutation;
        int steps;

        PermutationState(List<Integer> permutation, int steps) {
            this.permutation = permutation;
            this.steps = steps;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("data/num1327.txt"));
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        List<Integer> initial = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            initial.add(scanner.nextInt());
        }

        int result = bfs(N, K, initial);
        System.out.println(result);
    }

    private static int bfs(int N, int K, List<Integer> initial) {
        List<Integer> sorted = new ArrayList<>(initial);
        Collections.sort(sorted);

        Queue<PermutationState> queue = new LinkedList<>();
        Set<List<Integer>> visited = new HashSet<>();

        queue.add(new PermutationState(initial, 0));
        visited.add(initial);

        while (!queue.isEmpty()) {
            PermutationState current = queue.poll();

            // Check if the current permutation is sorted
            if (current.permutation.equals(sorted)) {
                return current.steps;
            }

            // Try reversing every possible K-length subarray
            for (int i = 0; i <= N - K; i++) {
                List<Integer> nextPermutation = new ArrayList<>(current.permutation);
                reverseSubarray(nextPermutation, i, i + K - 1);

                if (!visited.contains(nextPermutation)) {
                    queue.add(new PermutationState(nextPermutation, current.steps + 1));
                    visited.add(nextPermutation);
                }
            }
        }

        // If we cannot sort the array
        return -1;
    }

    private static void reverseSubarray(List<Integer> list, int start, int end) {
        while (start < end) {
            int temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }
}
