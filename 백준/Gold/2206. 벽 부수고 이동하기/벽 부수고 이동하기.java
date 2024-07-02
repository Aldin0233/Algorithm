import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

class NowPoint implements Comparable<NowPoint> {
    int r, c, w;
    boolean isBrake;

    public NowPoint(int r, int c, int w, boolean isBrake) {
        this.r = r;
        this.c = c;
        this.w = w;
        this.isBrake = isBrake;
    }

    public int compareTo(NowPoint e) {
        return this.w - e.w;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static final int[] dr = {1, -1, 0, 0};
    static final int[] dc = {0, 0, 1, -1};

    static int result;

    public static void main(String[] args) throws IOException {
        test();
    }

    private static void test() throws IOException {
        testInput();
        testProcess();
        testOutput();
    }

    private static void testInput() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }
    }

    private static void testProcess() {
        visited = new boolean[N][M][2]; 
        result = -1;
        PriorityQueue<NowPoint> pq = new PriorityQueue<>();
        pq.add(new NowPoint(0, 0, 1, false));

        while (!pq.isEmpty()) {
            NowPoint p = pq.poll();
            int r = p.r;
            int c = p.c;
            int w = p.w;
            boolean isBrake = p.isBrake;
            int brakeState = isBrake ? 1 : 0;

            if (visited[r][c][brakeState]) continue;
            visited[r][c][brakeState] = true;

            if (r == N - 1 && c == M - 1) {
                result = w;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if (map[nr][nc] == 0 && !visited[nr][nc][brakeState]) {
                        pq.add(new NowPoint(nr, nc, w + 1, isBrake));
                    }
                    if (!isBrake && map[nr][nc] == 1 && !visited[nr][nc][1]) {
                        pq.add(new NowPoint(nr, nc, w + 1, true));
                    }
                }
            }
        }
    }

    private static void testOutput() {
        System.out.println(result);
    }
}