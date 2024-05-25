import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, M;
    static int[][] box;
    static Queue<Point> qu = new LinkedList<>();
    
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
        testSettingInput();
        testDataInput();
    }

    private static void testSettingInput() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
    }

    private static void testDataInput() throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    qu.add(new Point(i, j));
                }
            }
        }
    }

    private static void testProcess() {
        result = -1;
        while (!qu.isEmpty()) {
            int size = qu.size();
            result++;
            for (int i = 0; i < size; i++) {
                Point it = qu.poll();
                int x = it.x;
                int y = it.y;
                for (int d = 0; d < 4; d++) {
                    int nx = x + dr[d];
                    int ny = y + dc[d];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && box[nx][ny] == 0) {
                        box[nx][ny] = 1;
                        qu.add(new Point(nx, ny));
                    }
                }
            }
        }

        if (!check()) {
            result = -1;
        }
    }

    private static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void testOutput() {
        System.out.println(result);
    }
}