package ACMcreaft;

import java.io.*;
import java.util.*;

public class TopologicalSortV1 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("data/ACMcreaft.txt"));
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        while(T-- > 0) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]); // 건물(노드) 수
            int K = Integer.parseInt(input[1]); // 규칙(간선) 수

            // 각 건물 짓는 데 걸리는 시간
            int[] buildTime = new int[N+1];
            String[] timeStr = br.readLine().split(" ");
            for(int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(timeStr[i-1]);
            }

            // 그래프(인접 리스트), 진입차수 배열
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }
            int[] inDegree = new int[N+1];

            // 간선 정보 입력
            for(int i = 0; i < K; i++) {
                String[] edge = br.readLine().split(" ");
                int X = Integer.parseInt(edge[0]);
                int Y = Integer.parseInt(edge[1]);
                // X -> Y
                graph.get(X).add(Y);
                inDegree[Y]++;
            }

            // 목표 건물 번호
            int W = Integer.parseInt(br.readLine());

            // dp[i] = i번째 건물을 완성하는 데 걸리는 최소 시간
            int[] dp = new int[N+1];

            // 진입차수 0인 노드를 큐에 넣으면서 초기화
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 1; i <= N; i++) {
                // 선행 건물이 없는 경우
                if(inDegree[i] == 0) {
                    queue.offer(i);
                    dp[i] = buildTime[i]; // 자기 건설 시간만 필요
                }
            }

            // BFS로 위상 정렬 수행 + dp 갱신
            while(!queue.isEmpty()) {
                int current = queue.poll();

                // current 건물 이후에 지어야 하는 건물들
                for(int next : graph.get(current)) {
                    // current를 만들었으니, next를 완성하는 시간 = max(dp[next], dp[current] + buildTime[next])
                    dp[next] = Math.max(dp[next], dp[current] + buildTime[next]);

                    // 진입차수 감소
                    inDegree[next]--;
                    // 새로 진입차수가 0이 된 노드는 큐에 삽입
                    if(inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            // 결과: 목표 건물 W를 짓는 데 걸리는 시간
            sb.append(dp[W]).append("\n");
        }

        System.out.print(sb);
    }
}