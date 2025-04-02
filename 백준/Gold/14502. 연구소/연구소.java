import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int result = Integer.MIN_VALUE;
    static List<int[]> virusStart = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    virusStart.add(new int[]{i, j});
                }
            }
        }
        searchComb(0, 0);
        System.out.println(result);
    }

    private static void searchComb(int now, int wallCnt) {
        if(wallCnt == 3) {
            bfs();
            return;
        }
        for(int i = now; i < N * M; i++) {
            int x = i / M, y = i % M;
            if(map[x][y] == 0) {
                map[x][y] = 1;
                searchComb(i + 1, wallCnt + 1);
                map[x][y] = 0;
            }
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>(virusStart);

        int[][] copy = new int[N][M];
        for(int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (inMap(nx, ny) && copy[nx][ny] == 0) {
                    copy[nx][ny] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(copy[i][j] == 0) {
                    cnt++;
                }
            }
        }

        result = Math.max(result, cnt);
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

}