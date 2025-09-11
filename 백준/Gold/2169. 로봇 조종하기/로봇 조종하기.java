import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] arr;
    static int[][] dp;
    static final int[] dr = {1, 0, 0};
    static final int[] dc = {0, -1, 1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = Integer.MIN_VALUE;
        dp = new int[N][M];
        dp[0][0] = arr[0][0];
        search();
        System.out.println(dp[N - 1][M - 1]);
    }

    private static void search() {
        for(int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + arr[0][j];
        }
        for(int i = 1; i < N; i++) {
            int[] fromLeft = new int[M];
            int[] fromRight = new int[M];
            
            //왼쪽에서 못옴 위에서 내려온 거에 더함
            fromLeft[0] = dp[i - 1][0] + arr[i][0];
            for(int j = 1; j < M; j++) {
                fromLeft[j] = Math.max(fromLeft[j - 1], dp[i - 1][j]) + arr[i][j];
            }
            //오른쪽에서 못옴 위에서 내려온 거에 더함
            fromRight[M - 1] = dp[i - 1][M - 1] + arr[i][M - 1];
            for(int j = M - 2; j >= 0; j--) {
                fromRight[j] = Math.max(fromRight[j + 1], dp[i - 1][j]) + arr[i][j];
            }
            for(int j = 0; j < M; j++) dp[i][j] = Math.max(fromLeft[j], fromRight[j]);
        }
    }


}






