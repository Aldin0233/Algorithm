
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] info, memo;
    static int ansSide;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        info = new int[N][M];
        memo = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                info[i][j] = line.charAt(j) - '0';
            }
        }

        ansSide = 0;

        for(int i = 0; i < N; i++) {
            memo[i][0] = info[i][0];
            if(memo[i][0] == 1) ansSide = 1;
        }
        for(int i = 0; i < M; i++) {
            memo[0][i] = info[0][i];
            if(memo[0][i] == 1) ansSide = 1;
        }

        for(int i = 1; i < N; i++) {
            for(int j = 1; j < M; j++) {
                if(info[i][j] == 1) {
                    memo[i][j] = Math.min(Math.min(memo[i - 1][j], memo[i][j - 1]), memo[i - 1][j - 1]) + 1;
                    ansSide = Math.max(ansSide, memo[i][j]);
                }
            }
        }


        System.out.println(ansSide * ansSide);
    }



}

