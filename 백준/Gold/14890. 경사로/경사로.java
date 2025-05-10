import java.util.*;
import java.io.*;

public class Main {

    static int N, L;
    static int[][] map;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        // Read the map values
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;

        for (int i = 0; i < N; i++) {
            int[] column = new int[N];
            for (int j = 0; j < N; j++) {
                column[j] = map[j][i];
            }
            ans += isValidPath(map[i]) + isValidPath(column);
        }

        System.out.println(ans);
    }

    private static int isValidPath(int[] path) {
        boolean[] visited = new boolean[N];
        for (int i = 1; i < N; i++) {
            if (Math.abs(path[i] - path[i - 1]) > 1) return 0;
            if (path[i] < path[i - 1]) {
                for (int j = i; j < i + L; j++) {
                    if (j >= N || path[j] != path[i] || visited[j]) return 0;
                    visited[j] = true;
                }
            } else if (path[i] > path[i - 1]) {
                for (int j = i - 1; j >= i - L; j--) {
                    if (j < 0 || path[j] != path[i - 1] || visited[j]) return 0;
                    visited[j] = true;
                }
            }
        }
        return 1;
    }
}
