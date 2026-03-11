import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    //무방향으로 봐야함
    static List<Edge> edges;

    static int[] P;

    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        int start = 0;
        for(int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c == 0));
        }
        int min = minSearch();
        int max = maxSearch();
        System.out.println(max * max - min * min);
    }

    static int minSearch() {
        edges.sort((e1, e2) -> Boolean.compare(e1.isUp, e2.isUp));
        makeP();
        int upCnt = 0;
        int picked = 0;
        for(Edge e : edges) {
            if(union(e.a, e.b)) {
                if(e.isUp) upCnt++;
                picked++;
                if(picked == N) break;
            }
        }
        return upCnt;
    }

    static int maxSearch() {
        edges.sort((e1, e2) -> Boolean.compare(e2.isUp, e1.isUp));
        makeP();
        int upCnt = 0;
        int picked = 0;
        for(Edge e : edges) {
            if(union(e.a, e.b)) {
                if(e.isUp) upCnt++;
                picked++;
                if(picked == N) break;
            }
        }
        return upCnt;
    }

    static void makeP() {
        P = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            P[i] = i;
        }
    }

    static int find(int x) {
        if(x == P[x]) return x;
        else return P[x] = find(P[x]);
    }

    static boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return false;
        P[ra] = rb;
        return true;
    }

    static class Edge {
        int a, b;
        boolean isUp;
        public Edge(int a, int b, boolean isUp) {
            this.a = a;
            this.b = b;
            this.isUp = isUp;
        }
    }

}

