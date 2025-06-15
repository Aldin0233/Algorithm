

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static final int INF = Integer.MAX_VALUE;
    static List<Edge> edges;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        boolean isCycle = false;

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for(int i = 0; i < N - 1; i++) {
            for(Edge e : edges) {
                //이미 방문한 곳에서 출발하는 모든 노드 간선에 대하여
                if(dist[e.from] != INF && dist[e.to] > dist[e.from] + e.weight)
                    dist[e.to] = dist[e.from] + e.weight;
            }
        }

        for(Edge e : edges) { //갱신이 일어나면 음수 사이클
            if(dist[e.from] != INF && dist[e.to] > dist[e.from] + e.weight) {
                isCycle = true;
                break;
            }
        }

        if(isCycle) {
            ans.append("-1");
        } else {
            for(int i = 2; i <= N; i++) {
                if(dist[i] == INF) {
                    ans.append("-1\n");
                } else {
                    ans.append(dist[i]).append("\n");
                }
            }
        }

        System.out.println(ans);
    }


}

class Edge {
    int from, to;
    int weight;
    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

