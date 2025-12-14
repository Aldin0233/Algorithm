import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int TC;
    static int N, M, W;
    static List<Edge> edges;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        TC = Integer.parseInt(st.nextToken());
        test: while(TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            List<Edge> edges = new ArrayList<>();
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }
            for(int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, -T));
            }
            long[] dist = new long[N + 1];

            for(int i = 1; i <= N; i++) {
                boolean updated = false;
                for(Edge e : edges) {
                    if(dist[e.to] > dist[e.from] + e.w) {
                        dist[e.to] = dist[e.from] + e.w;
                        updated = true;
                        if(i == N) {
                            ans.append("YES\n");
                            continue test;
                        }
                    }
                }
                if(!updated) break;
            }
            ans.append("NO\n");
        }
        System.out.println(ans);
    }

}

class Edge {
    int from, to;
    int w;
    public Edge(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }


}


