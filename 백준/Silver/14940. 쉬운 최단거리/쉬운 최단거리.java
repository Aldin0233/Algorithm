import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map, ansMap;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ansMap = new int[N][M];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            Arrays.fill(ansMap[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) q.offer(new int[]{i, j, 0});
                else if(map[i][j] == 0) ansMap[i][j] = 0;
            }
        }
        boolean[][] visited = new boolean[N][M];
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            ansMap[cur[0]][cur[1]] = cur[2];
            for(int d = 0; d < 4; d++) {
                int nx = cur[0] + dr[d];
                int ny = cur[1] + dc[d];
                if(isValid(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if(map[nx][ny] == 1) {
                        q.offer(new int[]{nx, ny, cur[2] + 1});
                    }
                }
            }

        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                ans.append(ansMap[i][j] + " ");
            }
            ans.append("\n");
        }


        System.out.println(ans);

    }

    private static boolean isValid(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M;
    }

}


