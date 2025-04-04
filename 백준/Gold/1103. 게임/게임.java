import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[][] board;
    static int[][] dp;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) == 'H' ? -1 : line.charAt(j) - '0';
            }
        }
        dp = new int[N][M];
        visited = new boolean[N][M];
        dfs(0, 0, 1);

        System.out.println(ans);
    }

    private static void dfs(int x, int y, int depth) {
        ans = Math.max(ans, depth);
        visited[x][y] = true;
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d] * board[x][y];
            int ny = y + dy[d] * board[x][y];
            if(inMap(nx, ny) && board[nx][ny] != -1 && dp[nx][ny] < depth + 1) {
                if(visited[nx][ny]) {
                    ans = -1;
                    return;
                }
                dp[nx][ny] = depth + 1;
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1);
                if(ans == -1) {
                    return;
                }
                visited[nx][ny] = false;
            }
        }
        visited[x][y] = false;
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}

