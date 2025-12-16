import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] clearTime;
    static int[][] dp;
    static int MAX = 1_000_000_000;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        clearTime = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                clearTime[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0] = clearTime[0].clone();
        for (int i = 1; i < N; i++) {
            Arrays.fill(dp[i], MAX);
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < M; k++) {
                    if(j == k) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + clearTime[i][j]);
                }
            }
        }
        int minResult = MAX;
        for (int i = 0; i < M; i++) {
            minResult = Math.min(minResult, dp[N - 1][i]);
        }
        ans.append(minResult);
        System.out.println(ans);
    }

}



