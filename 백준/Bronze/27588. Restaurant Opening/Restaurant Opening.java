import java.io.*;
import java.util.*;

public class Main {

    public static int ans;
    public static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] cost = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < N; k++) {
                    for(int l = 0; l < M; l++) {
                        cost[i][j] += (Math.abs(i - k) + Math.abs(j - l)) * map[k][l];
                    }
                }
            }
        }
        ans = 987654321;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                ans = Math.min(ans, cost[i][j]);
            }
        }

        System.out.println(ans);
    }



}

