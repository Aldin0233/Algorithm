import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static Map<Integer, List<Edge>> edges;
    static List<Long>[] findRouteCost;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edges = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            edges.put(i, new ArrayList<>());
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.get(a).add(new Edge(b, c));
//            edges.get(b).add(new Edge(a, c)); 단방향임
        }
        findRouteCost = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            findRouteCost[i] = new ArrayList<>();
        }
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(1, 0));
        while(!pq.isEmpty()) {
            State s = pq.poll();
            if(findRouteCost[s.curCity].size() >= K) continue;
            findRouteCost[s.curCity].add(s.time);
            for(Edge e : edges.get(s.curCity)) {
                pq.add(new State(e.to, s.time + e.time));
            }
        }
        for(int i = 1; i <= N; i++) {
            if(findRouteCost[i].size() < K) ans.append(-1);
            else ans.append(findRouteCost[i].get(K - 1));
            ans.append('\n');
        }

        System.out.println(ans);
    }

    static class State implements Comparable<State> {
        int curCity;
        long time;
        State(int curCity, long time) {
            this.curCity = curCity;
            this.time = time;
        }

        public int compareTo(State o) {
            return Long.compare(time, o.time);
        }
    }

    static class Edge {
        int to;
        int time;
        public Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

}




