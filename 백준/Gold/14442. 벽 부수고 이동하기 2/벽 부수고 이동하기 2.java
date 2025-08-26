import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static String[] map;
    static boolean[][][] visited;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static String ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new String[N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
        }
        visited = new boolean[N][M][K + 1];
        Queue<int[]> q = new LinkedList<>();
        visited[0][0][0] = true;
        q.add(new int[]{0, 0, 0, 1}); //r, c, k, dist
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if(isDestination(cur)) {
                System.out.println(cur[3]);
                return;
            }
            int k = cur[2];
            int cnt = cur[3];
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(inValid(nr, nc) && !visited[nr][nc][cur[2]]) {
                    visited[nr][nc][cur[2]] = true;
                    if(map[nr].charAt(nc) == '0') {
                        q.add(new int[]{nr, nc, k, cnt + 1});
                    } else if(k < K){
                        q.add(new int[]{nr, nc, k + 1, cnt + 1});
                    }
                }
            }
        }
        System.out.println(-1); //도착 실패
    }

    private static boolean isDestination(int[] cur) {
        return cur[0] == N - 1 && cur[1] == M - 1;
    }

    private static boolean inValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }


}



