import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    static int[] start;
    static List<int[]> deserterArr;
    static List<int[]> nodes;

    static int[][] map;

    static long[][] dp;

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        deserterArr = new ArrayList<>();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    deserterArr.add(new int[]{i, j});
                } else if(map[i][j] == -1) {
                    start = new int[]{i, j};
                }
            }
        }
        if(deserterArr.size() == 0) {
            System.out.println(0);
            return;
        }
        nodes = new ArrayList<>();
        nodes.add(start);
        nodes.addAll(deserterArr);
        int K = nodes.size();
        long[][] dists = new long[K][];
        for(int i = 0; i < K; i++) {
            dists[i] = dijkstra(nodes.get(i)[0], nodes.get(i)[1]);
        }
        int D = nodes.size() - 1;
        int FULL = (1 << D) - 1;
        dp = new long[1 << D][K];
        for(int i = 0; i <= FULL; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        dp[0][0] = 0;
        for(int mask = 0; mask <= FULL; mask++) {
            for(int i = 0; i < K; i++) {
                if(dp[mask][i] == Long.MAX_VALUE) continue;
                for(int j = 1; j < K; j++) {
                    int bit = 1 << (j - 1);
                    if((mask & bit) != 0) continue;

                    int nextMask = mask | bit;
                    dp[nextMask][j] = Math.min(dp[nextMask][j], dp[mask][i] + dists[i][j]);
                }
            }
        }
        long ans = Long.MAX_VALUE;
        for(int i = 1; i < K; i++) {
            if(dp[FULL][i] == Long.MAX_VALUE) continue;
            ans = Math.min(ans, dp[FULL][i] + dists[i][0]);
        }
        System.out.println(ans);
    }

    private static long[] dijkstra(int sr, int sc) {
        long[][] dist = new long[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[2]));
        pq.offer(new long[]{sr, sc, 0});
        dist[sr][sc] = 0;
        while(!pq.isEmpty()) {
            long[] cur = pq.poll();
            int r = (int) cur[0];
            int c = (int) cur[1];
            long cost = cur[2];
            if(cost > dist[r][c]) continue;
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(!isValid(nr, nc)) continue;
                long nextCost = cost;
                if(map[nr][nc] > 0) {
                    nextCost += map[nr][nc];
                }
                if(dist[nr][nc] > nextCost) {
                    dist[nr][nc] = nextCost;
                    pq.offer(new long[]{nr, nc, nextCost});
                }
            }
        }
        long[] res = new long[nodes.size()];
        for(int i = 0 ; i < nodes.size(); i++) {
            res[i] = dist[nodes.get(i)[0]][nodes.get(i)[1]];
        }
        return res;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }



}

