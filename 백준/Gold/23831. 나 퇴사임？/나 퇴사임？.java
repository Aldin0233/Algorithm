import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int A, B;
    static int[][] gainSatisfaction;
    static int[][][] dp;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        gainSatisfaction = new int[N + 1][4];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                gainSatisfaction[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //dp [] 날짜, [] A 휴게실 사용 횟수 A 넘기면 안됨, [] B 학습의지 판단 횟수 B가 넘으면 의미 없음, [] 전날 휴게실 사용 여부
        dp = new int[A + 1][B + 1][2];
        for(int[][] arr: dp) {
            for(int[] row: arr) {
                Arrays.fill(row, -1);
            }
        }
        dp[0][0][0] = 0;
        for(int i = 1; i <= N; i++) {
            int[][][] newDp = new int[A + 1][B + 1][2];
            for(int[][] arr : newDp) for(int[] row: arr) Arrays.fill(row, -1);
            int studyGain = Math.max(gainSatisfaction[i][0], gainSatisfaction[i][1]);
            int restGain = gainSatisfaction[i][3];
            int loungeGain = gainSatisfaction[i][2];
            for(int a = 0; a <= A; a++) {
                for(int b = 0; b <= B; b++) {
                    for(int prev = 0; prev <= 1; prev++) {
                        if(dp[a][b][prev] == -1) continue;
                        int cur = dp[a][b][prev];
                        newDp[a][Math.min(b + 1, B)][0] = Math.max(newDp[a][Math.min(b + 1, B)][0], cur + studyGain);
                        if(a + 1 <= A) {
                            newDp[a + 1][b][0] = Math.max(newDp[a + 1][b][0], cur + restGain);
                        }
                        if(prev == 0) {
                            newDp[a][b][1] = Math.max(newDp[a][b][1], cur + loungeGain);
                        }
                    }
                }
            }
            dp = newDp;
        }
        int ans = 0;
        for(int a = 0; a <= A; a++) {
            ans = Math.max(ans, dp[a][B][0]);
            ans = Math.max(ans, dp[a][B][1]);
        }

        System.out.println(ans);
    }





}




