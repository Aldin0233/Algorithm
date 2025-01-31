import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static boolean[][] visitedNormal, visitedColorBlind;
    static String[] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        testInput();
    }

    private static void testInput() {
        N = Integer.parseInt(sc.nextLine());
        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextLine();
        }
        testProcess();
    }

    private static void testProcess() {
        visitedNormal = new boolean[N][N];
        visitedColorBlind = new boolean[N][N];

        int normalCount = 0;
        int colorBlindCount = 0;

        // 정상 시각 (R, G, B 모두 구분)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visitedNormal[i][j]) {
                    bfs(i, j, arr[i].charAt(j), true);
                    normalCount++;
                }
            }
        }

        // 색맹 시각 (R과 G를 같은 색으로 취급)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visitedColorBlind[i][j]) {
                    bfs(i, j, arr[i].charAt(j), false);
                    colorBlindCount++;
                }
            }
        }

        testOutput(normalCount, colorBlindCount);
    }

    // BFS 탐색 (isNormal이 true이면 일반 시각, false이면 색맹 시각)
    private static void bfs(int i, int j, char color, boolean isNormal) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        
        if (isNormal) visitedNormal[i][j] = true;
        else visitedColorBlind[i][j] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (isValid(nr, nc, color, isNormal)) {
                    queue.add(new int[]{nr, nc});
                    
                    if (isNormal) visitedNormal[nr][nc] = true;
                    else visitedColorBlind[nr][nc] = true;
                }
            }
        }
    }

    // 맵 경계 및 방문 가능 여부 체크
    private static boolean isValid(int r, int c, char color, boolean isNormal) {
        if (r < 0 || r >= N || c < 0 || c >= N) return false;

        if (isNormal) {
            if (visitedNormal[r][c]) return false;
            return arr[r].charAt(c) == color;
        } else {
            if (visitedColorBlind[r][c]) return false;
            char neighborColor = arr[r].charAt(c);
            return (color == 'R' || color == 'G') ? (neighborColor == 'R' || neighborColor == 'G') : (neighborColor == color);
        }
    }

    private static void testOutput(int normalCount, int colorBlindCount) {
        System.out.println(normalCount + " " + colorBlindCount);
    }
}