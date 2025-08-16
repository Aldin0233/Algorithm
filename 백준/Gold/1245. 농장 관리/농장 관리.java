
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static final int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static final int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int ans = 0;

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
            }
        }
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visited[i][j]) bfs(i, j);
            }
        }

        System.out.println(ans);
    }

    private static void bfs(int r, int c) {
        int curHeight = map[r][c];
        boolean isHighest = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d = 0; d < 8; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(isValid(nr, nc)) {
                    if(map[nr][nc] == curHeight && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    } else if(map[nr][nc] > curHeight) {
                        isHighest = false;
                    }
                }
            }
        }
        if(isHighest) ans++;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }


}



