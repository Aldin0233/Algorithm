import java.io.*;
import java.util.*;

public class Main {

    static int T, N;
    static int[] dp;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[11];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            ans.append(solve(N)).append("\n");
        }
        System.out.println(ans);
    }

    private static int solve(int n) {
        if(dp[n] != -1) {
            return dp[n];
        }
        return dp[n] = solve(n - 3) + solve(n - 2) + solve(n - 1);
    }

}


