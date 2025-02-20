import java.io.*;
import java.util.*;

public class Main {

    public static int N, ans;
    public static String medInfo;
    public static int[][] dp;
    public static char[] bld = {'B', 'L', 'D'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        medInfo = br.readLine();
        dp = new int[N * 3][N * 3];
        for (int i = 0; i < N * 3; i++) {
            Arrays.fill(dp[i], -1);
        }
        ans = dfs(0, (N * 3) - 1, 2);
        System.out.println(ans);
    }

    public static int dfs(int i, int j, int prevState) {
        if(i > j) return 0;
        if(dp[i][j] != -1) return dp[i][j];

        int needState = (prevState + 1) % 3;
        int res = 0;
        if(medInfo.charAt(i) == bld[needState]) {
            res = 1 + dfs(i + 1, j, needState);
        }
        if(medInfo.charAt(j) == bld[needState]) {
            res = Math.max(res, 1 + dfs(i, j - 1, needState));
        }
        dp[i][j] = res;
        return res;
    }

    private static char next(char now) {
        switch(now) {
            case 'B':
                return 'L';
            case 'L':
                return 'D';
            case 'D':
                return 'B';
        }
        return 0;
    }
}





