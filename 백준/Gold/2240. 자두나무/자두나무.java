
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int T, W;

    static int[] fallInfo;
    static int[][] dp;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        fallInfo = new int[T + 1];
        dp = new int[T + 1][W + 1];

        for(int i = 1; i <= T; i++) {
            fallInfo[i] = Integer.parseInt(br.readLine());
        }

        for(int t = 1; t <= T; t++) {
            dp[t][0] = dp[t - 1][0] + (fallInfo[t] == 1 ? 1 : 0);
        }

        //홀수면 2번 나무 짝수면 1번 나무
        for(int w = 1; w <= W; w++) {
            for(int t = 1; t <= T; t++) {
                dp[t][w] = Math.max(dp[t - 1][w] + (fallInfo[t] % 2 != w % 2 ? 1 : 0), dp[t - 1][w - 1] + (fallInfo[t] % 2 != w % 2 ? 1 : 0));
            } // 가만 있기, 이전 타임에서 움직이기 중 최댓값
        }
        ans = 0;
        for(int w = 0; w <= W; w++) {
            ans = Math.max(ans, dp[T][w]);
        }


        System.out.println(ans);
    }






}
