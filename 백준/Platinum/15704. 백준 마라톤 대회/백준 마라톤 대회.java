import java.io.*;
import java.util.*;


public class Main {

    static int N, M, K;
    static Map<Integer, List<Edge>> graph = new HashMap<>();
    static int[] costs;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(new Edge(b, c, t));
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(new Edge(a, c, t));
        }
        int lo = 1, hi = 40_000;
        // hi 최대값 바로 통과한다 치고 C == 1일때, 40000 - 1000 ^ 2 여유
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            if(check(mid)) {
                ans = Math.max(ans, mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean check(int people) {
        boolean[] visited = new boolean[N + 1];
        costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{1, 0});
        costs[1] = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[0] == N) return costs[N] <= K;
            if(visited[cur[0]]) continue; //이미 탐색됨
            visited[cur[0]] = true;
            for(Edge e : graph.getOrDefault(cur[0], new ArrayList<>())) {
                long newCost = costs[cur[0]] + calCost(e, people);
                //최대 예산 초과
                if(newCost > K) continue;
                costs[e.to] = Math.min(costs[e.to], (int) newCost);
                pq.add(new int[]{e.to, costs[e.to]});
            }
        }
        //도달 못함
        return false;
    }

    private static long calCost(Edge e, int people) {
        if(people <= e.limit) {
            return 0;
        } else {
            long diff = people - e.limit;
            return e.cost * diff * diff;
        }
    }





}

class Edge {
    int to, cost, limit;
    public Edge(int to, int cost, int limit) {
        this.to = to;
        this.cost = cost;
        this.limit = limit;
    }
}

