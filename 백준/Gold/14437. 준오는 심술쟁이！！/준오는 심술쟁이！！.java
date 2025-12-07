import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int S;

    static StringBuilder ans = new StringBuilder();
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        String pw = br.readLine();
        int pwLen = pw.length();
        int[] dp = new int[S + 1];
        dp[0] = 1;
        for(int i = 0; i < pwLen; i++) {
            int[] ndp = new int[S + 1];
            long window = 0;
            for(int t = 0; t <= S; t++) {
                window += dp[t];
                if(t > 25) {
                    window -= dp[t - 26];
                }

                window %= MOD;
                if(window < 0) window += MOD;

                ndp[t] = (int) window;
            }
            dp = ndp;
        }

        System.out.println(dp[S]);
    }

}


