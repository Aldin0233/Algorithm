import java.io.*;
import java.util.*;

public class Main {

    static int N, M, T;
    static double[][] screen;
    static int[][] newScreen;
    static boolean[][] visited;
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        screen = new double[N][M];
        newScreen = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                screen[i][j] = calAvg(st);
            }
        }
        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                newScreen[i][j] = screen[i][j] >= T ? 1 : 0;
            }
        }
        visited = new boolean[N][M];
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M ; j++) {
                if(!visited[i][j] && newScreen[i][j] == 1) {
                    bfs(i, j);
                    ans++;
                }
            }
        }
        System.out.println(ans);

    }

    private static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;
        while(!q.isEmpty()) {
            int[] point = q.poll();
            int x = point[0], y = point[1];
            for(int d = 0 ; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if(inMap(nx, ny) && newScreen[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static double calAvg(StringTokenizer st) {
        return ((double) (Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()))) / 3;
    }

}

