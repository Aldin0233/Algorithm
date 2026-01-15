import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static final long MOD = 1_000_000_009L;
    // S0 = other // S1 = A // S2 = AB // S3 = ABC // S4 = ABCB // S5 = ABA // S6 == ABAB
    static final int S0 = 0, S1 = 1, S2 = 2, S3 = 3, S4 = 4, S5 = 5, S6 = 6;
    static final int A = 0, B = 1, C = 2, OTHER = 3;
    //각각 새 인덱스가 붙었을 때 접미사의 최대 패턴
    static final int[][] nextState = {
            {S1, S0, S0, S0},
            {S1, S2, S0, S0},
            {S5, S0, S3, S0},
            {S1, S4, S0, S0},
            {S1, S0, -1, S0}, //금지 패턴
            {S1, S6, S0, S0},
            {S5, S0, -1, S0} //금지 패턴
    };
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int otherWays = K - 3;
        long[] dp = new long[7];
        dp[S0] = 1;
        for(int i = 0; i < N; i++) {
            long[] ndp = new long[7];
            for(int state = 0; state < 7; state++) {
                long cur = dp[state];
                if(cur == 0) continue;

                for(int ch = 0; ch < 3; ch++) {
                    int ns = nextState[state][ch];
                    if(ns == -1) continue; //금지
                    long v = ndp[ns] + cur;
                    ndp[ns] = (v >= MOD) ? v - MOD : v;
                }

                int ns = S0;
                ndp[ns] = (ndp[ns] + cur * otherWays) % MOD;
            }
            dp = ndp;
        }
        long ans = 0;
        for(int state = 0; state < 7; state++) {
            ans += dp[state];
            if(ans >= MOD) ans -= MOD;
        }
        System.out.println(ans);
    }

}




