import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] P;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0 ; i< M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }
        makeUnion();
        int count = 0;
        int sum = 0;
        while(!pq.isEmpty() || count < N - 1){
            Edge e = pq.poll();
            int aRef = findRef(e.a);
            int bRef = findRef(e.b);
            if(aRef == bRef){
                continue;
            }
            union(aRef, bRef);
            sum += e.c;
            count++;
        }
        System.out.println(sum);
    }

    private static void makeUnion() {
        P = new int[N+1];
        for(int i = 1 ; i <=N; i++) {
            P[i] = i;
        }
    }

    private static int findRef(int x) {
        if(P[x] == x) return x;
        return P[x] = findRef(P[x]);
    }

    private static void union(int a, int b) {
        P[a] = b;
    }

}

class Edge implements Comparable<Edge> {
    int a, b, c;
    Edge(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    @Override
    public int compareTo(Edge o) {
        return this.c - o.c;
    }
}