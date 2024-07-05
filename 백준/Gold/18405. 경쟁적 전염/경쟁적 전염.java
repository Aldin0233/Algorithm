import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K, S, X, Y;
    static Queue<Point>[] virus;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

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
        K = Integer.parseInt(st.nextToken());
        testPreProcess();
        st = new StringTokenizer(br.readLine().trim());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;
    }

    private static void testPreProcess() throws IOException {
        visited = new boolean[N][N];
        makeVirus(K);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                int k = Integer.parseInt(st.nextToken());
                if (k != 0) {
                    virus[k].add(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }
    }

    private static void makeVirus(int k) {
        virus = new Queue[K + 1];
        for (int i = 1; i <= K; i++) {
            virus[i] = new LinkedList<>();
        }
    }

    private static void testProcess() {
        int time = 0;
        if(S == 0) {
            result = timeZeroCheck();
            return;
        }
        boolean isSpread = true;
        while (time != S && isSpread) {
            isSpread = false;
            for (int i = 1; i <= K; i++) {
                int size = virus[i].size();
                Queue<Point> q = virus[i];
                for (int j = 0; j < size; j++) {
                    Point p = q.poll();
                    int x = p.x;
                    int y = p.y;
                    if (x == X && y == Y) {
                        result = i;
                        return;
                    }
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dr[d];
                        int ny = y + dc[d];
                        if (nx == X && ny == Y) {
                            result = i;
                            return;
                        }
                        if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            q.add(new Point(nx, ny));
                            isSpread = true;
                        }
                    }
                }
            }
            time++;
        }
    }

    private static int timeZeroCheck() {
        for(int i = 1; i<=K; i++){
            while(!virus[i].isEmpty()){
                Point p = virus[i].poll();
                if(p.x == X && p.y == Y) {
                    return i;
                }
            }
        }
        return 0;
    }

    private static void testOutput() {
        System.out.println(result);
    }
}
