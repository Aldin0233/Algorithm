
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] weights, ballWeights;
    static boolean[][] dp;

    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weights = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        ballWeights = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            ballWeights[i] = Integer.parseInt(st.nextToken());
        }

        makeDpTable();
        checkAns();
        System.out.println(ans);
    }

    private static void makeDpTable() {
        dp = new boolean[N + 1][40001];
        dp[0][0] = true;
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < 40001; j++) {
                if(dp[i - 1][j]) {
                    dp[i][j] = true; //추 추가 X
                    if(j + weights[i] <= 40000) //무게 추가
                        dp[i][j + weights[i]] = true;
                    dp[i][Math.abs(j - weights[i])] = true; //차이값
                }
            }
        }
    }

    private static void checkAns() {
        for(int w : ballWeights) {
            if(w <= 40000 && dp[N][w]) ans.append("Y ");
            else ans.append("N ");
        }
    }


}