import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int C, D, M;
    static int[][] stockPrice;
    static int[][] stockPriceDiff;
    static int[][] dp;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        stockPrice = new int[D + 1][C];
        for(int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < D; j++) {
                stockPrice[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        for(int day = 0; day < D - 1; day++) {
            int[] dp = new int[M + 1];
            for(int i = 0; i < C; i++) {
                int cost = stockPrice[day][i];
                if(cost > M) continue;
                int gain = stockPrice[day + 1][i] - stockPrice[day][i];
                if(gain <= 0) continue;
                for(int money = cost; money <= M; money++) {
                    dp[money] = Math.max(dp[money], dp[money - cost] + gain);
                }
            }
            for(int money = 1; money <= M; money++) {
                dp[money] = Math.max(dp[money], dp[money - 1]);
            }
            M += dp[M];
        }
        System.out.println(M);
    }

}



