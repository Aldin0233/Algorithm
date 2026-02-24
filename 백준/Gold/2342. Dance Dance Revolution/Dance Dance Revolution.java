import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dp;
    static final int INF = Integer.MAX_VALUE;
//    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int order = Integer.parseInt(st.nextToken());
        dp = new int[5][5];
        for(int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;
        while(order != 0) {
            int[][] ndp = new int[5][5];
            for(int[] row : ndp) Arrays.fill(row, INF);
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(dp[i][j] == INF) continue;
                    int curPower = dp[i][j];
                    if(order != j) {
                        int cost = curPower + calMovePower(order, i);
                        ndp[order][j] = Math.min(ndp[order][j], cost);
                    }
                    if(order != i) {
                        int cost = curPower + calMovePower(order, j);
                        ndp[i][order] = Math.min(ndp[i][order], cost);
                    }
                }
            }
            dp = ndp;
            order = Integer.parseInt(st.nextToken());
        }

        int answer = INF;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                answer = Math.min(answer, dp[i][j]);
            }
        }

        System.out.println(answer);
    }

    static int calMovePower(int dest, int now) {
        if(dest == now) return 1;
        else if(now == 0) return 2;
        else if(now % 2 == dest % 2) return 4;
        else return 3;
    }



}

