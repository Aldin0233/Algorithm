import java.io.*;
import java.util.*;

public class Main {

    private static int N, M, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        String[] gallery = new String[N];
        for(int i = 0 ; i < N; i++) {
            gallery[i] = br.readLine();
        }
        boolean[][] map = new boolean[N + 2][M + 2];
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M ; j++) {
                if(gallery[i].charAt(j) == '.') {
                    map[i + 1][j + 1] = true;
                }
            }
        }

        int[] dr = {-1, 0, -1, 1, 0, 1, 0, 1, 0, 0, 1, 0};
        int[] dc = {0, 1, 0, 0, 1, 0, -1, 0, -1, 1, 0, 1};

        boolean[][][] visitedMap = new boolean[N + 2][M + 2][4]; // 상 하 좌 우
        ans = 0;
        for(int i = 1 ; i <= N; i++) {
            for(int j = 1 ; j <= M ; j++) {
                if(map[i][j]) {
                    for(int d = 0 ; d < 4 ; d++) {
                        if(!visitedMap[i][j][d]) {
                            int beforeWallR = i + dr[d*3];
                            int beforeWallC = j + dc[d*3];
                            int nr = i + dr[d*3 + 1];
                            int nc = j + dc[d*3 + 1];
                            int wallR = nr + dr[d*3 + 2];
                            int wallC = nc + dc[d*3 + 2];
                            if(!map[beforeWallR][beforeWallC] && map[nr][nc] && !map[wallR][wallC]) {
                                visitedMap[i][j][d] = true;
                                visitedMap[nr][nc][d] = true;
                                ans++;
                            }
                        }
                    }
                }
            }
        }


        System.out.println(ans);
    }

}
