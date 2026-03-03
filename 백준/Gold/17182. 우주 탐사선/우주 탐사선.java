import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static int[][] edges;
    static long[][] minTimeForRest;
    static long INF = 1_000_000_000_000L;
//    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edges = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                edges[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int mid = 0; mid < N; mid++) {
            for(int from = 0; from < N; from++) {
                for(int to = 0; to < N; to++) {
                    edges[from][to] = Math.min(edges[from][to], edges[from][mid] + edges[mid][to]);
                }
            }
        }
        minTimeForRest = new long[N][1 << N];
        for(long[] row : minTimeForRest) Arrays.fill(row, -1L);

        long result = dfs(K, 1 << K);

        System.out.println(result);

    }

    static long dfs(int cur, int visitedMask) {
        if(visitedMask == (1 << N) - 1) return 0;
        if(minTimeForRest[cur][visitedMask] != -1) return minTimeForRest[cur][visitedMask];

        long result = INF;
        for(int i = 0; i < N; i++) {
            if((visitedMask & (1 << i)) != 0) continue;
            result = Math.min(result, edges[cur][i] + dfs(i, visitedMask | (1 << i)));
        }
        return minTimeForRest[cur][visitedMask] = result;
    }



}

