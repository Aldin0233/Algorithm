import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] costInfo;
    static final int INF = 1_000_000_000;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costInfo = new int[N][3];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                costInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        checkDp();
        System.out.println(ans);
    }

    private static void checkDp() {
        int[][] dp = new int[N][3];
        dp[0][0] = dp[0][1] = dp[0][2] = INF;
        ans = INF;
        for(int j = 0; j < 3; j++) {
            dp[0][j] = costInfo[0][j];
            for(int i = 1; i < N - 1; i++) {
                dp[i][0] = costInfo[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = costInfo[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = costInfo[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            }
            ans = Math.min(ans, findMin(dp, j));
            dp[0][j] = INF;
        }
    }

    private static int findMin(int[][] dp, int fixStart) {
        int lIdx = N - 2;
        int usedOne = (fixStart + 1) % 3;
        int usedTwo = (fixStart + 2) % 3;
        int usedOneMin = Math.min(dp[lIdx][fixStart], dp[lIdx][usedTwo]) + costInfo[N - 1][usedOne];
        int usedTwoMin = Math.min(dp[lIdx][fixStart], dp[lIdx][usedOne]) + costInfo[N - 1][usedTwo];
        return Math.min(usedOneMin, usedTwoMin);
    }

}

