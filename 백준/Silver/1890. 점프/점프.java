
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] map; //맵
    static long[][] dp;
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};
    static long ans; //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new long[N][N];
        for(long[] row : dp){
            Arrays.fill(row, -1);
        }
        dp[N - 1][N - 1] = 1;
        ans = dfs(0, 0);
        System.out.println(ans);
    }

    private static long dfs(int row, int col){
        if(dp[row][col] != -1) return dp[row][col];
        dp[row][col] = 0;
        for(int d = 0; d < 2; d++) {
            int nr = row + (dr[d] * map[row][col]);
            int nc = col + (dc[d] * map[row][col]);
            if(inValid(nr, nc)) dp[row][col] += dfs(nr, nc);
        }
        return dp[row][col];
    }

    private static boolean inValid(int row, int col){
        return row >= 0 && row < N && col >= 0 && col < N;
    }



}


