import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static Queue<int[]> q = new LinkedList<>();
    static int[][] campusMap;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        campusMap = new int[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                if(line.charAt(j) == 'P') {
                    campusMap[i][j] = 1;
                } else if(line.charAt(j) == 'X') {
                    campusMap[i][j] = -1;
                } else if(line.charAt(j) == 'I') {
                    q.offer(new int[]{i, j});
                }
            }
        }
        visited = new boolean[N][M];
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d = 0; d < 4; d++) {
                int nx = cur[0] + dr[d];
                int ny = cur[1] + dc[d];
                if(isValid(nx, ny) && !visited[nx][ny] && campusMap[nx][ny] >= 0) {
                    visited[nx][ny] = true;
                    if(campusMap[nx][ny] == 1) ans++;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        System.out.println(ans > 0 ? ans : "TT");
    }

    private static boolean isValid(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M;
    }


}
