import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int A, B, C;
    static int[][] map;
    static int[][] prefixSum;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        prefixSum = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j]
                        + prefixSum[i][j - 1]
                        - prefixSum[i - 1][j - 1]
                        + map[i - 1][j - 1];
            }
        }
        ans = Integer.MAX_VALUE;
        int addAB = A + B;
        int addAC = A + C;
        int addBC = B + C;
        int firstWayRLimit = N - A + 1;
        int firstWayLLimit = M - addBC + 1;
        for(int i = 1; i <= firstWayRLimit; i++) {
            for(int j = 1; j <= firstWayLLimit; j++) {
                int area = calculateQuadrangle(i, i + A - 1, j, j + addBC - 1);
                ans = Math.min(ans, area);
            }
        }
        int secondWayRLimit = N - addAB + 1;
        int secondWayLLimit = M - addAC + 1;
        for(int i = 1; i <= secondWayRLimit; i++) {
            for(int j = 1; j <= secondWayLLimit; j++) {
                int area = calculateQuadrangle(i, i + A - 1, j, j + C - 1);
                area += calculateQuadrangle(i + A, i + addAB - 1, j + C, j + addAC - 1);
                ans = Math.min(ans, area);
            }
        }
        int thirdWayRLimit = N - addAC + 1;
        int thirdWayLLimit = M - addAB + 1;
        for(int i = 1; i <= thirdWayRLimit; i++) {
            for(int j = 1; j <= thirdWayLLimit; j++) {
                int area = calculateQuadrangle(i, i + A - 1, j, j + B - 1);
                area += calculateQuadrangle(i + A, i + addAC - 1, j + B, j + addAB - 1);
                ans = Math.min(ans, area);
            }
        }

        System.out.println(ans);
    }

    private static int calculateQuadrangle(int r1, int r2, int c1, int c2) {
        return prefixSum[r2][c2] - prefixSum[r1 - 1][c2] - prefixSum[r2][c1 - 1] + prefixSum[r1 - 1][c1 - 1];
    }

}






