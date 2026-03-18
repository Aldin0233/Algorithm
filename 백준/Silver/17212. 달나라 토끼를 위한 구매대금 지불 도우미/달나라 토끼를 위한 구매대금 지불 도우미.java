import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
//    static int[][] scores;
    static int[] dp;
    static final int[] MONEY = {1, 2, 5, 7};
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, 1_000_000_000);
        dp[0] = 0;
        for (int m = 0; m < 4; m++) {
            for(int i = MONEY[m]; i <= N; i++) {
                dp[i] = Math.min(dp[i], dp[i - MONEY[m]] + 1);
            }
        }

        System.out.println(dp[N]);
    }

}

