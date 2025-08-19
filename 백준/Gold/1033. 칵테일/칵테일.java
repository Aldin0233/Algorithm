
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Map<Integer, List<Edge>> edges = new HashMap<>();
    static long[] numerator, denominator; //분자, 분모
    static long[] ingredients;
    static boolean[] visited;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long p = Long.parseLong(st.nextToken());
            long q = Long.parseLong(st.nextToken());
            long g = gcd(p, q);
            p /= g;
            q /= g;
            edges.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, p, q));
            edges.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, q, p));
        }
        visited = new boolean[N];
        numerator = new long[N];
        numerator[0] = 1;
        denominator = new long[N];
        denominator[0] = 1;
        //0번을 기준점으로 1 : 1 로 두고 나머지를 분자와 분모로 비율 계산 하겠다.
        dfs(0);
        long lcmAll = 1;
        ingredients = new long[N];
        for(int i = 0; i < N; i++) {
            lcmAll = lcm(lcmAll, denominator[i]); //현재 전체 분모의 최소공배수
        }
        for(int i = 0; i < N; i++) {
            ingredients[i] = numerator[i] * (lcmAll / denominator[i]);
        }
        long G = ingredients[0];
        for(int i = 1; i < N; i++) {
            G = gcd(G, ingredients[i]); //전체의 최대 공약수
        }
        for(int i = 0; i < N; i++) {
            ingredients[i] /= G;
            ans.append(ingredients[i]).append(" ");
        }
        System.out.println(ans);
    }

    private static void dfs(int u) {
        visited[u] = true;
        for(Edge e : edges.get(u)) {
            if(!visited[e.v]) {
                numerator[e.v] = numerator[u] * e.q;
                denominator[e.v] = denominator[u] * e.p;
                long g = gcd(numerator[e.v], denominator[e.v]);
                numerator[e.v] /= g;
                denominator[e.v] /= g;
                dfs(e.v);
            }
        }
    }

    private static long gcd(long a, long b) {
        while(b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }



}

class Edge {
    int v;
    long p, q;
    public Edge(int v, long p, long q) {
        this.v = v;
        this.p = p;
        this.q = q;
    }
}



