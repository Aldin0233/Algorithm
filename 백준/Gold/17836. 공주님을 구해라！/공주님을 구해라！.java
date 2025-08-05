import java.io.*;
import java.util.*;

public class Main {

    static int N, M, T;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int t = 0;
        boolean[][][] visited = new boolean[N][M][2];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        do {
            int size = q.size();
            while (size --> 0) {
                int[] cur = q.poll();
                if(cur[0] == N - 1 && cur[1] == M - 1) {
                    System.out.println(t);
                    return;
                }
                boolean canBreak = cur[2] == 1;
                for(int d = 0; d < 4; d++) {
                    int nx = cur[0] + dr[d];
                    int ny = cur[1] + dc[d];
                    if(isValid(nx, ny)
                            && !visited[nx][ny][cur[2]] //방문 여부
                            && (canBreak || (map[nx][ny] == 0 || map[nx][ny] == 2))) { //벽 부술 수 있으면 맵 상관 없이
                        visited[nx][ny][cur[2]] = true;
                        if(map[nx][ny] == 2) {
                            q.add(new int[]{nx, ny, 1});
                        } else{
                            q.add(new int[]{nx, ny, cur[2]});
                        }

                    }
                }
            }
        } while(t++ <= T);
        System.out.print("Fail");
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }



}



