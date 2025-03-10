import java.io.*;
import java.util.*;

public class Main {
    static int N, mafia, ans = 0;
    static int[] guilty;
    static int[][] R;
    static boolean[] dead;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        guilty = new int[N];
        dead = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            guilty[i] = Integer.parseInt(st.nextToken());
        }

        R = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        mafia = Integer.parseInt(br.readLine());

        dfs(N, 0);
        System.out.println(ans);
    }

    static void dfs(int alive, int nights) {
        if (dead[mafia] || alive == 1) {
            ans = Math.max(ans, nights);
            return;
        }

        if (alive % 2 == 0) {
            for (int i = 0; i < N; i++) {
                if (dead[i] || i == mafia) continue;

                dead[i] = true;
                for (int j = 0; j < N; j++) {
                    guilty[j] += R[i][j];
                }
                dfs(alive - 1, nights + 1);
                for (int j = 0; j < N; j++) {
                    guilty[j] -= R[i][j];
                }
                dead[i] = false;
            }
        } else {
            int kill = -1, maxGuilt = -1;
            for (int i = 0; i < N; i++) {
                if (!dead[i] && guilty[i] > maxGuilt) {
                    maxGuilt = guilty[i];
                    kill = i;
                }
            }
            dead[kill] = true;
            dfs(alive - 1, nights);
            dead[kill] = false;
        }
    }
}
