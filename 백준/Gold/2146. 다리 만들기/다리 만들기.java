import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] island;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        island = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                island[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int idx = 2;
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(island[i][j] == 1) { //1이면 미 방문지
                    bfs(idx++, i, j); //끝나고 IDX 증가
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(island[i][j] >= 1) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(ans);
    }

    private static void bfs(int islandIdx, int stR, int stC) {
        Queue<int[]> q = new LinkedList<>();
        island[stR][stC] = islandIdx;
        q.add(new int[]{stR, stC});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(inValid(nr, nc) && island[nr][nc] == 1) {
                    island[nr][nc] = islandIdx;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }

    private static void bfs(int stR, int stC) {
        int startIsland = island[stR][stC];
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(new int[]{stR, stC, 0});
        visited[stR][stC] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int nDist = cur[2] + 1;
            if(nDist > ans) return;
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(inValid(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if(island[nr][nc] == 0) {
                        q.offer(new int[]{nr, nc, nDist});
                    } else if(island[nr][nc] != startIsland) {
                        ans = Math.min(ans, cur[2]);
                        return;
                    }
                }
            }
        }

    }

    private static boolean inValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}


