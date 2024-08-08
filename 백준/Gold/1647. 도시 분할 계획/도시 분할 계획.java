import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int N, M;
    static int[] P;
    static PriorityQueue<Edge> pq;

    static long result;

    public static void main(String[] args) throws IOException {
        test();
    }

    private static void test() throws IOException {
        settingByInput();
        processDataByInput();
        testProcess();
    }
    private static void settingByInput() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        pq = new PriorityQueue<Edge>();
    }

    private static void processDataByInput() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        P = new int[N+1];
    }

    private static void testProcess() {
        make();
        int n = 1;
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int a = find(e.A);
            int b = find(e.B);
            if(a == b) {
                continue;
            }
            union(a, b);
            n++;
            if(n == N) { break; }
            result += e.C;
        }
        dataToOutput();
    }

    private static void dataToOutput() {
        System.out.println(result);
    }

    private static void make() {
        for(int i = 1; i <= N; i++) {
            P[i] = i;
        }
    }

    private static int find(int x) {
        if(P[x] == x) return x;
        return P[x] = find(P[x]);
    }

    private static void union(int a, int b) {
        P[b] = a;
    }
}

class Edge implements Comparable<Edge>{
    int A, B, C;

    Edge(int a, int b, int c) {
        A = a;
        B = b;
        C = c;
    }
    public int compareTo(Edge o) {
        return C - o.C;
    }
}