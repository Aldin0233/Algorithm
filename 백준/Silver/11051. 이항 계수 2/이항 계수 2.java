import java.io.*;
import java.util.*;

class Main {

    static int N, K;
    static int[][] dp;
    static final int div = 10007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][K + 1];

        System.out.println(dfs(N, K));
    }

    private static int dfs(int n, int k) {
        if (dp[n][k] > 0) {
            return dp[n][k];
        }

        if (k == 0 || n == k) {
            return dp[n][k] = 1;
        }

        return dp[n][k] = (dfs(n - 1, k - 1) + dfs(n - 1, k)) % div;
    }
}