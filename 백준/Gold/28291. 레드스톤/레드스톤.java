import java.io.*;
import java.util.*;

public class Main {

    static int W, H, N;
    static int[][] mapInfo;
    static int[][] redstoneInfo;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static final String[] redstones = {"redstone_dust", "redstone_block", "redstone_lamp"};
    static boolean ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken()); H = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        mapInfo = new int[H][W];
        for(int[] row: mapInfo) Arrays.fill(row, -1);
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for(int nameIdx = 0; nameIdx < redstones.length; nameIdx++) {
                if(name.equals(redstones[nameIdx])) {
                    mapInfo[y][x] = nameIdx;
                }
            }
        }
        redstoneInfo = new int[H][W];
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                if(mapInfo[i][j] == 1) { //각 레드스톤 블럭에서 출발
                    bfs(i, j);
                }
            }
        }
        ans = true;
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                if(mapInfo[i][j] == 2 && redstoneInfo[i][j] < 1) {
                    ans = false;
                    break;
                }
            }
        }
        System.out.println(ans ? "success" : "failed");
    }

    private static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        redstoneInfo[i][j] = 16;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int nowPower = redstoneInfo[cur[0]][cur[1]] - 1;
            for(int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(inValid(nr, nc) && canSendPower(nr, nc) && redstoneInfo[nr][nc] < nowPower) {
                    redstoneInfo[nr][nc] = nowPower;
                    if(mapInfo[nr][nc] == 0) q.add(new int[]{nr, nc});
                }
            }
        }

    }

    private static boolean inValid(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }

    private static boolean canSendPower(int r, int c) {
        return mapInfo[r][c] != -1 && mapInfo[r][c] != 1; //블럭에는 전해봤자 의미 없고, 회로 없는 곳에는 전파 불가
    }



}