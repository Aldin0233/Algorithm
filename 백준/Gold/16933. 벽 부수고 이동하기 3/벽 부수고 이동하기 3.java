import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static String[] map;
    static int[][][] visited;
    static int ans = -1;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new String[N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
        }
        visited = new int[N][M][2];
        for (int[][] layer : visited) {
            for (int[] row : layer)
                Arrays.fill(row, -1);
        }
        bfs();
        System.out.println(ans);
    }

    public static void bfs() {
        Queue<MoveState> q = new LinkedList<>();
        q.add(new MoveState(0, 0, 1, K, true));
        visited[0][0][1] = K;

        while (!q.isEmpty()) {
            MoveState cur = q.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {
                ans = cur.moveDist;
                return;
            }
            int nextTime = cur.day ? 0 : 1;
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (!inMap(nx, ny)) continue;

                if (map[nx].charAt(ny) == '0') {
                    if (visited[nx][ny][nextTime] < cur.canDestroyK) {
                        visited[nx][ny][nextTime] = cur.canDestroyK;
                        q.add(new MoveState(nx, ny, cur.moveDist + 1, cur.canDestroyK, !cur.day));
                    }
                } else if (cur.day && cur.canDestroyK > 0) {
                    int newK = cur.canDestroyK - 1;
                    if (visited[nx][ny][nextTime] < newK) {
                        visited[nx][ny][nextTime] = newK;
                        q.add(new MoveState(nx, ny, cur.moveDist + 1, newK, false));
                    }
                }
            }
            if (visited[cur.x][cur.y][nextTime] < cur.canDestroyK) {
                visited[cur.x][cur.y][nextTime] = cur.canDestroyK;
                q.add(new MoveState(cur.x, cur.y, cur.moveDist + 1, cur.canDestroyK, !cur.day));
            }
        }
    }

    private static boolean inMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}

class MoveState {
    int x, y;
    int moveDist;
    int canDestroyK;
    boolean day;

    MoveState(int x, int y, int moveDist, int canDestroyK, boolean day) {
        this.x = x;
        this.y = y;
        this.moveDist = moveDist;
        this.canDestroyK = canDestroyK;
        this.day = day;
    }
}
