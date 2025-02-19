import java.io.*;
import java.util.*;

public class Main {

    public static int T, C, M, ans;
    public static Map<Integer, List<Edge>> edges;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ans = -1;
            edges = new HashMap<>();
            for(int i = 1; i <= C; i++) {
                edges.put(i, new ArrayList<>());
            }
            for(int i = 1 ; i < C; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());
                edges.get(a).add(new Edge(b, dist));
            }
            dfs(1, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int v, int dist) {
        if(dist >= M) {
            ans = Math.max(ans, dist);
        }
        for(Edge e: edges.get(v)) {
            dfs(e.B, dist + e.dist);
        }
    }
}

class Edge {
    int B, dist;
    public Edge(int B, int dist) {
        this.B = B;
        this.dist = dist;
    }
}



