import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static int H, W, O, F, X1, Y1, X2, Y2;
    static int[][] map;
    static int[][] visited;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static final int MIN = -1;
    static final String SUCCESS = "잘했어!!\n";
    static final String FAIL = "인성 문제있어??\n";
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            setInput(new StringTokenizer(br.readLine()));
            map = new int[H][W];
            for(int o = 0; o < O; o++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int l = Integer.parseInt(st.nextToken());
                map[x][y] = l;
            }
            visited = new int[H][W];
            for(int[] row: visited) Arrays.fill(row, MIN);
            visited[X1][Y1] = F;
            PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[2]).reversed());
            q.add(new int[]{X1, Y1, F});
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                if(isEnd(cur[0], cur[1])) break;
                if(visited[cur[0]][cur[1]] > cur[2]) continue;
                int nP = cur[2] - 1; //next Remain power
                for(int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(inMap(nr, nc) && visited[nr][nc] < nP) {
                        if(canMove(cur[0], cur[1], nr, nc, cur[2])) {
                            visited[nr][nc] = nP;
                            q.add(new int[]{nr, nc, nP});
                        }
                    }
                }
            }
            ans.append(visited[X2][Y2] > MIN ? SUCCESS : FAIL);
        }
        System.out.println(ans);
    }

    private static boolean canMove(int r, int c, int nr, int nc, int power) {
        if(map[r][c] >= map[nr][nc]) return true;
        return map[nr][nc] - map[r][c] <= power;
    }

    private static boolean inMap(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }

    private static boolean isEnd(int r, int c) {
        return r == X2 && c == Y2;
    }

    private static void setInput(StringTokenizer st) {
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        O = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        X1 = Integer.parseInt(st.nextToken()) - 1;
        Y1 = Integer.parseInt(st.nextToken()) - 1;
        X2 = Integer.parseInt(st.nextToken()) - 1;
        Y2 = Integer.parseInt(st.nextToken()) - 1;
    }

}


