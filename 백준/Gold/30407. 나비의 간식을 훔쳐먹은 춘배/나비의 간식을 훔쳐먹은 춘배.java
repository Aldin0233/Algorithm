import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int H, D, K;
    static int[] damages;
    static int[][][] dp;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        damages = new int[N];
        for (int i = 0; i < N; i++) {
            damages[i] = Integer.parseInt(br.readLine());
        }
        searchTurn();
        System.out.println(ans);
    }

    private static void searchTurn() {
        dp = new int[N + 1][N + 1][2]; //턴수, 거리 멀어지기 선택 수, 놀래키기 사용 수
        for(int[][] arr : dp)
            for(int[] row : arr)
                Arrays.fill(row, -1);
        dp[0][0][0] = H;

        for(int i = 0 ; i < N; i++) {
            for(int m = 0 ; m <= i ; m++) {
                for(int used = 0; used < 2; used++) {
                    int curHp = dp[i][m][used];
                    if(curHp <= 0) continue;
                    int dmg;
                    //웅크렸을 때
                    dmg = calDamage(m, damages[i]) / 2;
                    dp[i + 1][m][used] = Math.max(dp[i + 1][m][used], curHp - dmg);

                    //네발로 걸었을 때
                    dmg = calDamage(m + 1, damages[i]);
                    dp[i + 1][m + 1][used] = Math.max(dp[i + 1][m + 1][used], curHp - dmg);
                    
                    //깜짝 놀래키기 사용하지 않았을 때
                    if(used == 0 && i + 1 < N) {
                        dmg = calDamage(m, damages[i]); //이번 데미지는 그대로 받음
                        dp[i + 2][m + 1][1] = Math.max(dp[i + 2][m + 1][1], curHp - dmg); //다음턴 데미지는 무시니 무조건 거리 벌어지는 게 이득
                    }
                }
            }
        }
        ans = -1;
        for (int m = 0; m <= N; m++) {
            for(int used = 0; used < 2; used++) {
                ans = Math.max(ans , dp[N][m][used]);
            }
        }
    }

    private static int calDamage(int m, int nowDamage) {
        int nowDistance = D + m * K;
        if(nowDistance < nowDamage) {
            return nowDamage - nowDistance;
        }
        return 0;
    }


}



