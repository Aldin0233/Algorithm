import java.io.*;
import java.util.*;

public class Main {

    static int N, E, first, second;
    static Map<Integer, List<Edge>> edges;
    static boolean[][] visited;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new HashMap<>();
        for(int i = 0 ; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.putIfAbsent(a, new ArrayList<>());
            edges.putIfAbsent(b, new ArrayList<>());
            edges.get(a).add(new Edge(b, c));
            edges.get(b).add(new Edge(a, c));
        }
        visited = new boolean[N + 1][4]; //0은 어느 정점도 밟지 않은 상태, 1은 첫번째 밟은 상태, 2는 두번째 밟은 상태, 3은 둘 다 밟은 상태
        st = new StringTokenizer(br.readLine());
        first = Integer.parseInt(st.nextToken());
        second = Integer.parseInt(st.nextToken());
        ans = search();
        System.out.println(ans);
    }

    private static int search() {
        PriorityQueue<NowVisit> pq = new PriorityQueue<>();
        boolean firstVisited = false;
        boolean secondVisited = false;
        if(first == 1) {
            firstVisited = true;
        }
        if(second == N) {
            secondVisited = true;
        }
        pq.add(new NowVisit(1, 0, firstVisited, secondVisited));
        while(!pq.isEmpty()) {
            NowVisit nowVisit = pq.poll();
            int state = (nowVisit.firstVisited ? 1 : 0) + (nowVisit.secondVisited ? 2 : 0);
            if(visited[nowVisit.nowLocation][state]) continue;
            visited[nowVisit.nowLocation][state] = true;
            if(nowVisit.nowLocation == N && state == 3) {
                return nowVisit.nowCost;
            }
            for(Edge e : edges.getOrDefault(nowVisit.nowLocation, new ArrayList<>())) {
                int newState = state;
                boolean fv = nowVisit.firstVisited, sv = nowVisit.secondVisited;
                if(e.dest == first && !fv) {
                    newState += 1;
                    fv = true;
                } else if(e.dest == second && !sv) {
                    newState += 2;
                    sv = true;
                }
                if(!visited[e.dest][newState]) {
                    pq.offer(new NowVisit(e.dest, nowVisit.nowCost + e.dist, fv, sv));
                }
            }
        }
        return -1;
    }



}

class NowVisit implements Comparable<NowVisit> {
    int nowLocation;
    int nowCost;
    boolean firstVisited;
    boolean secondVisited;

    public NowVisit(int nowLocation, int nowCost, boolean firstVisited, boolean secondVisited) {
        this.nowLocation = nowLocation;
        this.nowCost = nowCost;
        this.firstVisited = firstVisited;
        this.secondVisited = secondVisited;
    }

    public int compareTo(NowVisit o) {
        return nowCost - o.nowCost;
    }
}

class Edge {
    int dest;
    int dist;

    public Edge(int dest, int dist) {
        this.dest = dest;
        this.dist = dist;
    }

}
