
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
//    static int[][] scores;
    static int[] dp;
    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[1 << 5];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] scores = new int[5];
            for(int role = 0; role < 5; role++) {
                scores[role] = Integer.parseInt(st.nextToken());
            }
            int[] nextDp = new int[1 << 5];
            for(int mask  = 0; mask < 1 << 5; mask++) {
                if(dp[mask] == -1) continue;
                nextDp[mask] = Math.max(nextDp[mask], dp[mask]);
                for(int role = 0; role < 5; role++) {
                    if((mask & (1 << role)) != 0) continue;
                    int newMask = mask | (1 << role);
                    nextDp[newMask] = Math.max(nextDp[newMask], dp[mask] + scores[role]);
                }
            }
            dp = nextDp;
        }


        System.out.println(dp[(1 << 5) - 1]);
    }

}

