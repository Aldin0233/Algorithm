
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] N개의햄버거;
    static boolean[][] dp;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        N개의햄버거 = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int 전체햄버거최대효용 = 0;
        for (int i = 0; i < N; i++) {
            N개의햄버거[i] = Integer.parseInt(st.nextToken());
            전체햄버거최대효용 += N개의햄버거[i];
        }
        dp = new boolean[전체햄버거최대효용 + 1][전체햄버거최대효용 + 1];
        dp[0][0] = true;
        for(int 햄버거 : N개의햄버거) {
            boolean[][] next = new boolean[전체햄버거최대효용 + 1][전체햄버거최대효용 + 1];
            for(int 관우 = 0; 관우 <= 전체햄버거최대효용; 관우++) {
                for(int 막내 = 0; 막내 <= 전체햄버거최대효용; 막내++) {
                    if(!dp[관우][막내]) continue;
                    if(관우 + 햄버거 <= 전체햄버거최대효용) next[관우 + 햄버거][막내] = true;
                    if(막내 + 햄버거 <= 전체햄버거최대효용) next[관우][막내 + 햄버거] = true;
                    next[관우][막내] = true; //기존 거 유지
                }
            }
            dp = next;
        }

        ans = 0;
        for(int 관우 = 0; 관우 <= 전체햄버거최대효용; 관우++) {
            for(int 막내 = 0; 막내 <= 관우; 막내++) {
                if(!dp[관우][막내]) continue;
                int 철환 = 전체햄버거최대효용 - 관우 - 막내;
                if(철환 >= 막내) {
                    ans = Math.max(ans, 막내);
                }
            }
        }

        System.out.println(ans);
    }

}









