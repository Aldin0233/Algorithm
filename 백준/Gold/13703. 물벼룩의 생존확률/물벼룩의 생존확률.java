import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K, N;
    static long[][] dp;
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new long[N + 1][K + N + 1]; //시간, 깊이
        dp[0][K] = 1;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= K + N; j++) {
                if(j > 1) dp[i][j] += dp[i - 1][j - 1];
                if(j < K + N) dp[i][j] += dp[i - 1][j + 1];
            }
        }
        long ans = 0;
        for(int i = 1; i <= K + N; i++) {
            ans += dp[N][i];
        }
        System.out.println(ans);
    }



}




