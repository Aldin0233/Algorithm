import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, D, E;
    static int[] heights;
    static long[] distFromHome, distFromKoreaUniv;
    static long MAX = Long.MAX_VALUE;
    static long MIN = Long.MIN_VALUE;
    static Map<Integer, List<Edge>> edges;
    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        heights = new int[N];
        edges = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
            edges.put(i, new ArrayList<>());
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int n = Integer.parseInt(st.nextToken());
            int from = heights[a] < heights[b] ? a : b;
            int to = heights[a] < heights[b] ? b : a;
            edges.get(from).add(new Edge(to, n));
        }
        calDistFromHome();
        calDistFromKoreaUniv();
        ans = MIN;
        for(int i = 1; i < N - 1; i++) {
            if(distFromKoreaUniv[i] != MAX && distFromHome[i] != MAX) {
                long dist = distFromHome[i] + distFromKoreaUniv[i];
                ans = Math.max(ans, (long) heights[i] * E - dist * D);
            }
        }

        System.out.println(ans == MIN ? "Impossible" : ans);
    }

    private static void calDistFromHome() {
        PriorityQueue<State> pq = new PriorityQueue<>();
        distFromHome = new long[N];
        Arrays.fill(distFromHome, MAX);
        pq.add(new State(0, 0));
        calDist((PriorityQueue<State>) pq, distFromHome);
    }

    private static void calDistFromKoreaUniv() {
        PriorityQueue<State> pq = new PriorityQueue<>();
        distFromKoreaUniv = new long[N];
        Arrays.fill(distFromKoreaUniv, MAX);
        pq.add(new State(N - 1, 0));
        calDist((PriorityQueue<State>) pq, distFromKoreaUniv);
    }

    private static void calDist(PriorityQueue<State> pq, long[] distFrom) {
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if(distFrom[cur.now] <= cur.dist) continue;
            distFrom[cur.now] = cur.dist;
            for (Edge edge : edges.get(cur.now)) {
                long newDist = cur.dist + edge.dist;
                if(distFrom[edge.to] <= newDist) continue;
                pq.add(new State(edge.to, newDist));
            }
        }
    }

}

class State implements Comparable<State> {
    int now;
    long dist;

    State(int now, long dist) {
        this.now = now;
        this.dist = dist;
    }

    public int compareTo(State o) {
        return Long.compare(this.dist, o.dist);
    }
}

class Edge {
    int to;
    int dist;
    public Edge(int to, int dist) {
        this.to = to;
        this.dist = dist;
    }
}



