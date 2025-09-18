import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static boolean[] dp;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int t = 1; t <= 3; t++) {
            N = Integer.parseInt(br.readLine());
            int[][] things = new int[N][2];
            int total = 0;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                things[i][0] = Integer.parseInt(st.nextToken());
                things[i][1] = Integer.parseInt(st.nextToken());
                total += things[i][0] * things[i][1];
            }

            if(total % 2 == 1) {
                ans.append("0\n");
                continue;
            }
            int target = total / 2;
            dp = new boolean[target + 1];
            dp[0] = true;

            for(int i = 0; i < N; i++) {
                int coin = things[i][0];
                int cnt = things[i][1];

                int k = 1;
                while(cnt > 0) {
                    int use = Math.min(k, cnt);
                    int val = coin * use;

                    for(int sum = target;  sum >= val; sum--) {
                        if(dp[sum - val]) dp[sum] = true;
                    }

                    cnt -= use;
                    k <<= 1;
                }
            }

            ans.append(dp[target] ? "1\n" : "0\n");
        }

        System.out.println(ans);
    }

}







