import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] forest;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        forest = new int[N][N];

        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++) {
                forest[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][N];

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }

        System.out.print(ans);
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] != 0) return dp[x][y];

        dp[x][y] = 1;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (inMap(nx, ny) && forest[nx][ny] > forest[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
            }
        }

        return dp[x][y];
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }


}