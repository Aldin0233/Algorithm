import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int sx, sy;
    static char[][] map;
    static int max;
//    static StringBuilder ans = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'R') {
                    sx = i;
                    sy = j;
                }
            }
        }
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        dp[sx] = 0;
        max = -1;
        for(int j = sy + 1; j < M; j++) {
            int[] nDp = new int[N];
            Arrays.fill(nDp, -1);
            for(int i = 0; i < N; i++) {
                if(dp[i] == -1) continue;
                if(i > 0) {
                    int up = i - 1;
                    checkNextState(dp, nDp, i, j, up);
                }
                if(i < N - 1) {
                    int down = i + 1;
                    checkNextState(dp, nDp, i, j, down);
                }
                checkNextState(dp, nDp, i, j, i);
            }
            dp = nDp;
        }
        System.out.println(max);

    }

    private static void checkNextState(int[] dp, int[] nDp, int i, int j, int cur) {
        switch (map[cur][j]) {
            case '#': break;
            case 'C': nDp[cur] = Math.max(nDp[cur], dp[i] + 1); break;
            case 'O': max = Math.max(max, dp[i]);
            case '.': nDp[cur] = Math.max(nDp[cur], dp[i]); break;
        }
    }

}

