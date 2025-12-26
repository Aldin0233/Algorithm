import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] prefixMap;
    static int H, W, Sr, Sc, Fr, Fc;
    static boolean[][] visited;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static long ans;
//    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        prefixMap = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                prefixMap[i][j] = Integer.parseInt(st.nextToken());
                prefixMap[i][j] += (prefixMap[i - 1][j] + prefixMap[i][j - 1] - prefixMap[i - 1][j - 1]);
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Sr = Integer.parseInt(st.nextToken()) - 1;
        Sc = Integer.parseInt(st.nextToken()) - 1;
        Fr = Integer.parseInt(st.nextToken()) - 1;
        Fc = Integer.parseInt(st.nextToken()) - 1;
        visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{Sr, Sc, 0});
        visited[Sr][Sc] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == Fr && cur[1] == Fc) {
                System.out.println(cur[2]);
                return;
            }
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(isValid(nr, nc) && !visited[nr][nc] && canPlace(nr, nc)) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc, cur[2] + 1});
                }
            }

        }

        System.out.println(-1);
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r + H <= N && c >= 0 && c + W <= M;
    }

    private static boolean canPlace(int r, int c) {
        int sum = prefixMap[r + H][c + W] - prefixMap[r][c + W] - prefixMap[r + H][c] + prefixMap[r][c];
        return sum == 0;
    }




}



