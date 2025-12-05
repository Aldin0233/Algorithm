import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int num;
    static StringBuilder ans = new StringBuilder();
    static String[] dp;
    static final int[] cost = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    static final String INF = "~~~~~~~~~~~~~~~~~~~~~~";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        buildDp();
        for (int i = 0; i < T; i++) {
            num = Integer.parseInt(br.readLine());
            //min
            ans.append(dp[num]).append(" ");
            //max
            ans.append(num % 2 == 0 ? "1".repeat(num / 2) : "7" + "1".repeat((num - 2) / 2)).append("\n");
        }

        System.out.println(ans);
    }

    private static void buildDp() {
        dp = new String[101];
        Arrays.fill(dp, INF);
        dp[0] = "";
        for(int n = 1; n <= 100; n++) {
            for(int d = 0; d <= 9; d++) {
                int c = cost[d];
                if(n - c < 0 || dp[n - c].equals(INF)) continue;
                if(dp[n - c].isEmpty() && d == 0) continue;
                String cand = dp[n - c] + d;
                if(compare(cand, dp[n])) dp[n] = cand;
            }
        }
    }

    private static boolean compare(String a, String b) {
        if(a.length() != b.length()) return a.length() < b.length();
        return a.compareTo(b) < 0;
    }


}


