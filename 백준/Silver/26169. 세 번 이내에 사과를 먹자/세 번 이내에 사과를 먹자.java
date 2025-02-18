import java.io.*;
import java.util.*;

public class Main {

    public static int ans = 0;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static int[][] map;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[5][5];
        visited = new boolean[5][5];
        for(int i = 0 ; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 5 ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int r, c;
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dfs(r, c, 0, map[r][c] == 1 ? 1 : 0);
        System.out.println(ans);
    }

    private static void dfs(int r, int c, int depth, int apple) {
        if(depth >= 3) {
            if(apple >= 2) {
                ans = 1;
            }
            return;
        }
        visited[r][c] = true;
        for(int d = 0 ; d < 4 ; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(inMap(nr, nc)) {
                dfs(nr, nc, depth + 1, map[nr][nc] == 1 ? apple + 1 : apple);
            }
        }
        visited[r][c] = false;
    }

    private static boolean inMap(int r, int c) {
        return r>=0 && r<5 && c>=0 && c<5 && !visited[r][c] && map[r][c] != -1;
    }

}

