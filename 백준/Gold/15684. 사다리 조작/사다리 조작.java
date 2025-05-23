import java.io.*;
import java.util.*;

public class Main {

    static int N, M, H;
    static boolean[][] ladderLines;
    static int[] dy = {0, 1};
    static int ans; //결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ans = 4;
        ladderLines = new boolean[H][N - 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            ladderLines[a][b] = true;
        }
        dfs(0, 0, 0);


        System.out.println(ans == 4 ? -1 : ans);
    }

    private static void dfs(int cnt, int x, int y) {
        if(cnt >= ans) {
            return;
        }
        if(check()) {
            ans = cnt;
            return;
        }
        for(int i = x; i < H; i++) {
            for(int j = i == x ? y : 0; j < N - 1; j++) {
                if (!ladderLines[i][j] &&
                        (j == 0 || !ladderLines[i][j - 1]) &&
                        (j + 1 >= N - 1 || !ladderLines[i][j + 1])) {
                    ladderLines[i][j] = true;
                    dfs(cnt + 1, i, j + 2);
                    ladderLines[i][j] = false;
                }
            }
        }

    }

    private static boolean check() {
        for(int i = 0; i < N; i++) {
            int now = i;
            for(int j = 0; j < H; j++) {
                if(now < N - 1 && ladderLines[j][now]) {
                    now++;
                } else if(now > 0 && ladderLines[j][now - 1]) {
                    now--;
                }
            }
            if(now != i) {
                return false;
            }
        }
        return true;
    }



}


