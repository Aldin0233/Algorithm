import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static long ans;
    static int[][] map;
    static long[][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1L);
        }
        ans = dfs(0, 0);
        System.out.println(ans);
    }

    private static long dfs(int x, int y) {
        if(x == N - 1 && y == M - 1) {
            return 1;
        }
        if(dp[x][y] != -1) return dp[x][y];
        dp[x][y] = 0;
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(inMap(nx, ny) && map[nx][ny] < map[x][y]) {
                dp[x][y] += dfs(nx, ny);
            }
        }
        return dp[x][y];
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }


}

