import java.io.*;
import java.util.*;

public class Main {

    public static int N, M, h, w, K, ans;
    public static int[][] map, prefixSumMap;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        K = Integer.parseInt(br.readLine());
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            q.add(new int[]{r, c});
        }
        boolean[][] visited = new boolean[N][M];
        while(!q.isEmpty()) {
            int[] arr = q.poll();
            if(visited[arr[0]][arr[1]]) continue;
            visited[arr[0]][arr[1]] = true;
            for(int d = 0; d < 4; d++) {
                int nr = arr[0] + dr[d];
                int nc = arr[1] + dc[d];
                if(inMap(nr, nc) && !visited[nr][nc] && map[nr][nc] >= map[arr[0]][arr[1]]) {
                    q.add(new int[]{nr, nc});
                }
            }
        }
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                if(visited[i][j]) map[i][j] = 0;
            }
        }

        prefixSumMap = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                prefixSumMap[i][j] = prefixSumMap[i - 1][j]
                        + prefixSumMap[i][j - 1]
                        - prefixSumMap[i - 1][j - 1]
                        + map[i - 1][j - 1];
            }
        }
        ans = 0;
        for(int i = 0; i <= N - h; i++){
            for(int j = 0; j <= M - w; j++){
                int r = i + h, c = j + w;

                int subSum = prefixSumMap[r][c]
                        - prefixSumMap[i][c]
                        - prefixSumMap[r][j]
                        + prefixSumMap[i][j];

                if(subSum == 0) ans++;
            }
        }

        System.out.println(ans);
    }

    private static boolean inMap(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}





