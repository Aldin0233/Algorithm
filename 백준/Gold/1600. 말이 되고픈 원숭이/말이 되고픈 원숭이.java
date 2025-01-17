import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int K, W, H;
    private static boolean[][] map;
    private static int answer;
    private static final int[] dr = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] dc = {0, 0, -1, 1, -2, -1, 1, 2, -2, -1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 0;
            }
        }
        bfs();
        System.out.println(answer);
    }

    private static void bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][][] visited = new boolean[H][W][K + 1];
        pq.add(new Point(0, 0, 0, K));
        visited[0][0][K] = true;

        while (!pq.isEmpty()) {
            Point p = pq.poll();

            if (p.x == H - 1 && p.y == W - 1) {
                answer = p.time;
                return;
            }

            for (int d = 0; d < 12; d++) {
                int nx = p.x + dr[d];
                int ny = p.y + dc[d];
                int nextK = (d >= 4) ? p.remainK - 1 : p.remainK;

                if (inMap(nx, ny) && map[nx][ny] && nextK >= 0 && !visited[nx][ny][nextK]) {
                    visited[nx][ny][nextK] = true;
                    pq.add(new Point(nx, ny, p.time + 1, nextK));
                }
            }
        }
        answer = -1;
    }

    private static boolean inMap(int nx, int ny) {
        return nx >= 0 && nx < H && ny >= 0 && ny < W;
    }

    static class Point implements Comparable<Point> {
        int x, y, time, remainK;

        Point(int x, int y, int time, int remainK) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.remainK = remainK;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.time, o.time);
        }
    }
}
