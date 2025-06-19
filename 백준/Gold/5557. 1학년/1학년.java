

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static long[][] dp;

    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];

        dp = new long[N][21];

        arr[0] = Integer.parseInt(st.nextToken());
        dp[0][arr[0]] = 1;

        for(int i = 1; i < N - 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            for(int j = arr[i]; j <= 20; j++) {
                dp[i][j] += dp[i - 1][j - arr[i]];
            }
            for(int j = 20 - arr[i]; j >= 0; j--) {
                dp[i][j] += dp[i - 1][j + arr[i]];
            }
        }
        int target = Integer.parseInt(st.nextToken());

        ans = dp[N - 2][target];

        System.out.println(ans);
    }


}


