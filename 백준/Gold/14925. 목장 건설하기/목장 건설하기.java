import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + map[i - 1][j - 1];
            }
        }

        System.out.println(findLargestZeroSquare());

    }

    private static int findLargestZeroSquare() {
        int maxSize = 0;

        int minSide = Math.min(N, M);
        for (int size = minSide; size >= 1; size--) {
            boolean found = false;

            for (int i = 1; i + size - 1 <= N; i++) {
                for (int j = 1; j + size - 1 <= M; j++) {
                    int total = getSum(i, j, i + size - 1, j + size - 1);
                    if (total == 0) {
                        maxSize = size;
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (found) break;
        }

        return maxSize;
    }

    private static int getSum(int x1, int y1, int x2, int y2) {
        return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
    }


}


