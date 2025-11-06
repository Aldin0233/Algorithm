import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int[] dp;
    static final String WATER = "water";
    static final String GIMBOB = "minigimbob";
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(N <= K) {
            System.out.println(GIMBOB);
            return;
        }
        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i <= N; i++) {
            if(dp[i] > K) continue;
            if(i + 1 <= N) dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            int wand = i + (i / 2);
            if(wand <= N) dp[wand] = Math.min(dp[wand], dp[i] + 1);
        }
        //단조 증가되지만 0, 1에서 제자리 뛰기가 가능하기 때문에 K번 안에 N까지 도달만 가능하면 통과 가능
        System.out.println(dp[N] <= K ? GIMBOB : WATER);
    }

}