import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, A, B, C;
    static int[][] costs;
    static Map<Integer, List<Edge>> edges = new HashMap<>();
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.computeIfAbsent(a, k -> new ArrayList<>()).add(new Edge(b, c));
            edges.computeIfAbsent(b, k -> new ArrayList<>()).add(new Edge(a, c));
        }
        for(int i = 1; i <= 20; i++) {
            PriorityQueue<State> pq = new PriorityQueue<>();
            pq.offer(new State(A, 0));
            boolean[] visited = new boolean[N + 1];
            while(!pq.isEmpty()) {
                State s = pq.poll();
                if(visited[s.destination]) continue;
                visited[s.destination] = true;
                if(s.destination == B) {
                    System.out.println(i);
                    return;
                }
                for(Edge edge : edges.get(s.destination)) {
                    if(visited[edge.to] || edge.cost > i) continue;
                    int newCost = s.nowCost + edge.cost;
                    if(newCost <= C) {
                        pq.offer(new State(edge.to, newCost));
                    }
                }
            }
        }
        System.out.println(-1);
    }

    static class State implements Comparable<State> {
        int destination;
        int nowCost;
        State(int destination, int nowCost) {
            this.destination = destination;
            this.nowCost = nowCost;
        }

        public int compareTo(State o) {
            return nowCost - o.nowCost;
        }

    }

    static class Edge {
        int to;
        int cost;
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

}
