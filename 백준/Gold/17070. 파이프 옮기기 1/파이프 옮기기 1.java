
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] room;
    static int[][][] dp;

    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;
    static final int DIAGONAL = 2;

    static int ansScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        room = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                room[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][N][3];
        for(int[][] arr : dp)
            for(int[] row: arr)
                Arrays.fill(row, -1);


        ansScore = dfs(0, 1, HORIZONTAL);

        System.out.println(ansScore);
    }

    private static int dfs(int x, int y, int dir) {
        if(checkToDestination(x, y)) return 1; // 도착
        if(dp[x][y][dir] != -1) return dp[x][y][dir]; // 이미 탐색한 방향이면 반환

        int result = 0;

        if(dir % 2 == 0) { // 가로 혹은 대각선일 때
            if (canMoveVertical(x, y + 1)) {
                result += dfs(x, y + 1, HORIZONTAL);
            }
        }

        if(dir > 0) { // 세로 혹은 대각선일 때
            if (canMoveHorizontal(x + 1, y)) {
                result += dfs(x + 1, y, VERTICAL);
            }
        }

        if(canMoveDiagonal(x + 1, y + 1)) { //대각선으로는 다 갈 수 있음
            result += dfs(x + 1, y + 1, DIAGONAL);
        }

        return dp[x][y][dir] = result;
    }

    //도착지인지 확인
    private static boolean checkToDestination(int x, int y) {
        return x == N - 1 && y == N - 1;
    }

    //가로 갈 수 있는지 체크
    private static boolean canMoveVertical(int x, int ny) {
        return ny < N && room[x][ny] == 0;
    }

    //세로 갈 수 있는지 체크
    private static boolean canMoveHorizontal(int nx, int y) {
        return nx < N && room[nx][y] == 0;
    }

    //대각선 갈 수 있는지 체크
    private static boolean canMoveDiagonal(int nx, int ny) {
        return nx < N && ny < N && room[nx - 1][ny] == 0 && room[nx][ny - 1] == 0 && room[nx][ny] == 0;
    }

}

