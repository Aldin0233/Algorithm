import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M, A, B;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        Map<Integer, List<Edge>> map = new HashMap<>();
        for(int i = 1 ; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        StringTokenizer st;
        for(int i = 0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map.get(start).add(new Edge(end, cost));
        }
        PriorityQueue<CityCost> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        A  = Integer.parseInt(st.nextToken());
        B  = Integer.parseInt(st.nextToken());
        pq.add(new CityCost(A, 0));
        boolean[] visited = new boolean[N+1];
        int ans = 0;
        while(!pq.isEmpty()) {
            CityCost c = pq.poll();
            if(visited[c.city]) {
                continue;
            }
            if(c.city == B) {
                ans = c.moveCost;
            }
            visited[c.city] = true;
            
            for(Edge e : map.get(c.city)) {
                if(visited[e.city]) continue;
                pq.add(new CityCost(e.city, e.cost + c.moveCost));
            }

        }
        System.out.println(ans);

    }


}

class Edge {
    int city;
    int cost;
    Edge(int city, int cost) {
        this.city = city;
        this.cost = cost;
    }
}

class CityCost implements Comparable<CityCost> {
    int city;
    int moveCost;
    public CityCost(int city, int moveCost) {
        this.city = city;
        this.moveCost = moveCost;
    }
    public int compareTo(CityCost o) {
        return moveCost - o.moveCost;
    }
}
