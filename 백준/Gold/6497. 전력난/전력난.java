import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[] p;
    private static long sum;
    private static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while(N != 0 && M != 0) {
            make();
            sum = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for(int i = 0 ; i < M ; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                pq.add(new Node(x, y, z));
                sum += z;
            }
            int tmp = 0;
            long finalCost = 0;
            while(!pq.isEmpty() || tmp < N - 1) {
                Node node = pq.poll();
                int a = find(node.x);
                int b = find(node.y);
                if(a == b) {
                    continue;
                }
                union(a, b);
                finalCost += node.z;
                tmp++;
            }
            ans.append(sum - finalCost).append("\n");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
        }

        System.out.println(ans);
    }

    public static void make() {
        p = new int[N];
        for(int i = 1; i < N; i++) {
            p[i] = i;
        }
    }

    public static int find(int x) {
        if(p[x] == x) { return x; }
        return p[x] = find(p[x]);
    }

    public static void union(int a, int b) {
        p[a] = b;
    }

}

class Node implements Comparable<Node> {
    int x, y, z;
    Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public int compareTo(Node o) {
        return z - o.z;
    }
}