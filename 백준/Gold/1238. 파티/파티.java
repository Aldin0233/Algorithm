

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, X;
    static int[][] edges;
    static int[][] cost;
    static final int INF = Integer.MAX_VALUE;

    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        edges = new int[N][N];
        for (int[] row : edges) {
            Arrays.fill(row, INF);
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            edges[a][b] = c;
        }

        cost = new int[N][N];

        for(int i = 0; i < N; i++) {
            checkCost(i, cost[i], X);
        }

        ans = -1;

        for(int i = 0; i < N; i++) {
            ans = Math.max(ans, cost[i][X] + cost[X][i]);
        }

        System.out.println(ans);
    }

    private static void checkCost(int startCity, int[] arr, int count) {
        boolean[] visited = new boolean[N];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.add(new int[]{startCity, 0});
        while(!pq.isEmpty()) {
            int[] curCity = pq.poll();
            int city = curCity[0];
            if(visited[city]) continue;
            int cost = curCity[1];
            visited[city] = true;
            arr[city] = cost;
            if(startCity != X && city == X) {
                return;
            }
            for(int i = 0; i < N; i++) {
                if(!visited[i] && edges[city][i] != INF) {
                    pq.add(new int[]{i, cost + edges[city][i]});
                }
            }
        }
    }


}


