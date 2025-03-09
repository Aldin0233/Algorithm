import java.io.*;
import java.util.*;

public class Main {

    private static int N, M, H;
    private static int[] dx = {-1, 1, 0, 0, 0, 0};
    private static int[] dy = {0, 0, -1, 1, 0, 0};
    private static int[] dz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        int[][][] map = new int[H][N][M];
        Queue<int[]> qu = new LinkedList<>();
        int freshCnt = 0;
        for (int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if(map[i][j][k] == 1) {
                        qu.add(new int[]{i, j, k});
                    } else if(map[i][j][k] == 0) {
                        freshCnt++;
                    }
                }
            }
        }
        int day = -1;
        while(!qu.isEmpty()){
            int size = qu.size();
            day++;

            for(int s = 0; s < size; s++){
                int[] cur = qu.poll();
                for(int d=0; d<6; d++){
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];
                    int nz = cur[2] + dz[d];

                    if(inMap(nx, ny, nz) && map[nx][ny][nz] == 0) {
                        map[nx][ny][nz] = 1;
                        freshCnt--;
                        qu.add(new int[]{nx,ny,nz});
                    }
                }
            }
        }
        if(freshCnt > 0) System.out.println(-1);
        else System.out.println(day);

    }

    private static boolean inMap(int x, int y, int z) {
        return x >= 0 && x < H && y >= 0 && y < N && z >= 0 && z < M;
    }

}
