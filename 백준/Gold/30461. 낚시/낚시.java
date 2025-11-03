import java.io.*;
import java.util.*;

public class Main {

    static int N, M, Q;
    static int[][] map;
    static int[][] prefixDiagSum;
    static int[][] prefixTriSum;
    static int[] lastCatchedDepth;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        prefixDiagSum = new int[N][M];
        prefixTriSum = new int[N][M];
        lastCatchedDepth = new int[M];
        Arrays.fill(lastCatchedDepth, -1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                prefixDiagSum[i][j] = map[i][j];
                if(i > 0 && j > 0) {
                    prefixDiagSum[i][j] += prefixDiagSum[i - 1][j - 1];
                }
                prefixTriSum[i][j] = prefixDiagSum[i][j];
                if(i > 0) {
                    prefixTriSum[i][j] += prefixTriSum[i - 1][j];
                }
            }
        }
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            ans.append(prefixTriSum[r][c]).append('\n');
        }

        System.out.println(ans);
    }

}