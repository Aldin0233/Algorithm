import java.io.*;
import java.util.*;

public class Main {

    static int N, M, ans = Integer.MAX_VALUE;
    static int[][] map = new int[N][M];
    static List<CCTV> cctvs;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][][] directions = {
            {{0}, {1}, {2}, {3}}, // 1번
            {{0, 2}, {1, 3}}, // 2번 반대로
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, //3번 붙어서
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, //4번 3방향
            {{0, 1, 2, 3}} //5번 4방향
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctvs = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] < 6) {
                    cctvs.add(new CCTV(i, j, map[i][j] - 1));
                }
            }
        }

        dfs(0, map);
        System.out.println(ans);

    }

    static void dfs(int depth, int[][] map) {
        if (depth == cctvs.size()) {
            ans = Math.min(ans, countBlind(map));
            return;
        }

        CCTV cctv = cctvs.get(depth);
        int[][][] dirOptions = directions;

        for (int[] dirs : dirOptions[cctv.type]) {
            int[][] copy = copyMap(map);
            watch(copy, cctv.x, cctv.y, dirs);
            dfs(depth + 1, copy);
        }
    }

    static void watch(int[][] map, int x, int y, int[] dirs) {
        for (int dir : dirs) {
            int nx = x;
            int ny = y;

            while (true) {
                nx += dx[dir];
                ny += dy[dir];

                if (!inMapAndNoWall(nx, ny, map)) break;
                if (map[nx][ny] == 0) map[nx][ny] = 7;
            }
        }
    }

    static boolean inMapAndNoWall(int nx, int ny, int[][] map) {
        return nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != 6;
    }

    static int countBlind(int[][] map) {
        int count = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    static int[][] copyMap(int[][] src) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++)
            copy[i] = Arrays.copyOf(src[i], M);
        return copy;
    }

}

class CCTV {
    int x, y;
    int type;

    public CCTV(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

}

